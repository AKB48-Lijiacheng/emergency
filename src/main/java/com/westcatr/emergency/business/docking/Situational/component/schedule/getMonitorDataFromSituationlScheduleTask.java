package com.westcatr.emergency.business.docking.Situational.component.schedule;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.westcatr.emergency.business.docking.Situational.dto.SecurityEventDto;
import com.westcatr.emergency.business.entity.MonitorInfo;
import com.westcatr.emergency.business.entity.SituMonitorSrcInfo;
import com.westcatr.emergency.business.service.MonitorInfoService;
import com.westcatr.emergency.business.service.SituMonitorSrcInfoService;
import com.westcatr.emergency.business.utils.CamelConvertUtil;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 去态势主平台获取数据
 * @author lijiacheng
 * @since  2021/3/29
 **/
@Slf4j
@Configurable
@Transactional(rollbackForClassName="RuntimeException")
public class getMonitorDataFromSituationlScheduleTask {
    private final   String  indexs ="security_event_2021.03";
    @Autowired
    MonitorInfoService monitorInfoService;
    @Autowired
    SituMonitorSrcInfoService situMonitorSrcInfoService;

    @Autowired
    RestHighLevelClient client;
//todo
    @Scheduled(cron = "0 0 0 * * ?")
    public void  getMonitorData(){
        int  situSaveCount=0;
        int situUpdateCount=0;
        int monitSaveCount=0;
        int monitUpdateCount=0;
        int year = DateUtil.thisYear();
        int month = DateUtil.thisMonth();
        String monthStr=month<10 ?  "0"+month:""+month;
        String indexs="security_event_"+ year +"."+ monthStr;//索引库

        SearchRequest request = new SearchRequest(indexs);//构建索引请求
        SearchResponse search = null;
        try {
            log.info("定时器启动，开始向  "+indexs+"   请求数据!");
            search = client.search(request);
            SearchHit[] hits = search.getHits().getHits();
            List<SecurityEventDto> lis = new ArrayList<>();

            for (SearchHit hit : hits) {
                Map<String, Object> sourceMap = hit.getSourceAsMap();
                SecurityEventDto securityEventDto = BeanUtil.copyProperties(sourceMap, SecurityEventDto.class);
                lis.add(securityEventDto);
            }
            log.info("  "+indexs+"   态势平台请求数据完成!  共 "+hits.length+"条数据！！");
            for (SecurityEventDto dto : lis) {
//                //去数据库更新或存储源数据，由于数据源不是驼峰命名，所以我们要进行处理
                SituMonitorSrcInfo situMonitorSrcInfo = new SituMonitorSrcInfo();
                Field[] fields = situMonitorSrcInfo.getClass().getDeclaredFields();//获取实体的成员变量
                for (Field field : fields) {
                    String s = CamelConvertUtil.underscoreName(field.getName());//拿到驼峰转下划线的str
                    Object value = ReflectUtil.getFieldValue(dto, s);
                    if (null!=value) {
                        ReflectUtil.setFieldValue(situMonitorSrcInfo, field, value);
                    }
                }
                situMonitorSrcInfo.setId(null);//因为上面转换会把id字段也写进来，写到主键去了
                situMonitorSrcInfo.setSrcId(dto.getId());//id单独拿出来设置

                int count = situMonitorSrcInfoService.count(new QueryWrapper<SituMonitorSrcInfo>().eq("src_id", situMonitorSrcInfo.getSrcId()));
                if (count<1){
                    situMonitorSrcInfoService.save(situMonitorSrcInfo);
                    log.info("situ_monitor_src_info表插入数据： id= "+situMonitorSrcInfo.getId());
                    situSaveCount++;
                }else {
                    situMonitorSrcInfoService.update(situMonitorSrcInfo,new QueryWrapper<SituMonitorSrcInfo>().eq("src_id", situMonitorSrcInfo.getSrcId()));
                    log.info("situ_monitor_src_info表更新数据： src_id= "+situMonitorSrcInfo.getSrcId());
                    situUpdateCount++;
                }

                //更新或插入监测信息表，并绑定event_id和situ_monitor_src_id
                MonitorInfo monitorInfo = getMonitorInfo(situMonitorSrcInfo);
                int monitorCount = monitorInfoService.count(new QueryWrapper<MonitorInfo>().eq("situ_event_id", monitorInfo.getSituEventId()));
                if (monitorCount<1){
                    monitorInfo.setIsDuplicated(0);//先设置为不重复
                    monitorInfoService.save(monitorInfo);
                    log.info("bus_monitor_info表插入数据： id= "+monitorInfo.getId());
                    monitSaveCount++;
                }else {
                    monitorInfoService.update(monitorInfo,new QueryWrapper<MonitorInfo>().eq("situ_event_id", monitorInfo.getSituEventId()));
                    log.info("bus_monitor_info表更新数据： situEventId= "+monitorInfo.getSituEventId());
                    monitUpdateCount++;
                }
            }
            log.info("situ_monitor_src_info共插入数据  ："+situSaveCount+",  共更新数据"+situUpdateCount);
            log.info("bus_monitor_info共插入数据  ："+monitSaveCount+",  共更新数据"+monitUpdateCount);
        } catch (IOException e) {
            new MyRuntimeException("定时器态势请求数据失败！！！！");
        }
    }






    private MonitorInfo getMonitorInfo(SituMonitorSrcInfo situMonitorSrcInfo) {
        MonitorInfo monitorInfo = new MonitorInfo();
        monitorInfo.setSituMonitorSrcId(situMonitorSrcInfo.getId());
        monitorInfo.setTargetAssetName(null);//目标资产名称
        monitorInfo.setProblemName(situMonitorSrcInfo.getEventName());
        monitorInfo.setMonitorTime(DateUtil.date((Long.parseLong(situMonitorSrcInfo.getCreateTime()))));//监测时间
        monitorInfo.setProblemType(String.valueOf(situMonitorSrcInfo.getEventType()));
        monitorInfo.setProblemDescribe(situMonitorSrcInfo.getEventDescription());
        monitorInfo.setEnterpriseName(null);//相关企业
        monitorInfo.setDisposalMeasure(null);//处置措施
        monitorInfo.setTfMajorEvents(null);//是否重大活动
        monitorInfo.setTfSuperiorInstructions(null);//是否上级指示
        monitorInfo.setSituEventId(situMonitorSrcInfo.getSrcId());
        monitorInfo.setStatus(0);
        monitorInfo.setSituMonitorSrcId(situMonitorSrcInfo.getId());
        return monitorInfo;
    }

}
