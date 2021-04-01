package com.westcatr.emergency.business.component.schedule;

import cn.hutool.core.bean.BeanUtil;
import com.westcatr.emergency.business.pojo.dto.ElasticSearchDto.SecurityEventDto;
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
public class getMonitorDataScheduleTask {
    private final   String  indexs ="security_event_2021.03";

    @Autowired
    RestHighLevelClient client;
//todo
    @Scheduled(cron = "0 0 24 * * ?")
    public void  getMonitorData(){
        SearchRequest request = new SearchRequest();
        SearchResponse search = null;
        log.info("定时器启动，开始向  "+indexs+"   请求数据!");
        try {
            search = client.search(request);
        SearchHit[] hits = search.getHits().getHits();
        List<SecurityEventDto> lis = new ArrayList<>();

        for (SearchHit hit : hits) {
            Map<String, Object> sourceMap = hit.getSourceAsMap();
            SecurityEventDto securityEventDto = BeanUtil.copyProperties(sourceMap, SecurityEventDto.class);
            lis.add(securityEventDto);
        }
            System.out.println(lis);
            log.info("  "+indexs+"   态势平台请求数据完成!  共 "+hits.length+"条数据！！");
        } catch (IOException e) {
            new RuntimeException("定时器态势请求数据失败！！！！");
        }
    }

}
