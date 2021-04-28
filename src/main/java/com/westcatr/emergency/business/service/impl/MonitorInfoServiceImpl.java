package com.westcatr.emergency.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.westcatr.emergency.business.entity.*;
import com.westcatr.emergency.business.mapper.MonitorInfoMapper;
import com.westcatr.emergency.business.pojo.dto.ExcelDto.MonitorExcelDto;
import com.westcatr.emergency.business.pojo.dto.MonitorDto;
import com.westcatr.emergency.business.pojo.query.MonitorInfoQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorInfoVO;
import com.westcatr.emergency.business.pojo.vo.MonitorSimilarDto;
import com.westcatr.emergency.business.service.*;
import com.westcatr.emergency.business.utils.FileUtil;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 监测信息表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-04-07
 */
@Service
public class MonitorInfoServiceImpl extends ServiceImpl<MonitorInfoMapper, MonitorInfo> implements MonitorInfoService {

    @Value("${file.doc.path}")
    String downLoadFilePath;
@Autowired
MonitorNextService monitorNextService;
@Autowired
    MonitorNextSrcInfoService monitorNextSrcInfoService;
    @Autowired
    SituMonitorSrcInfoService situMonitorSrcInfoService;
    @Autowired
    private EntInfoService entInfoService;
    @Autowired
    private CountryService countryService;
    @Override
    public IPage<MonitorInfo> iPage(MonitorInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<MonitorInfo>().create(query));
    }

    @Override
    public boolean iSave(MonitorInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(MonitorInfo param) {
        return this.updateById(param);
    }

    @Override
    public MonitorInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }

    @Override
    public File buildDoc(String type, List<MonitorInfoVO> records) {
        String name = "监测信息表";
        String fileSuffix = ".xlsx";
        long time = System.currentTimeMillis();
        String filePath = downLoadFilePath + time + name;
        File file = null;
        fileSuffix = FileUtil.getFileSuffix(type);
        try {
            filePath += fileSuffix;//文件路径
            cn.hutool.core.io.FileUtil.touch(filePath);//创建目录和文件
            List<MonitorExcelDto> list = records.stream().map(m -> {
                MonitorExcelDto dto = new MonitorExcelDto();
                BeanUtil.copyProperties(m, dto);
                return dto;
            }).collect(Collectors.toList());
            EasyExcel.write(file, MonitorExcelDto.class).sheet(name).doWrite(list);
        } catch (Exception e) {
            new RuntimeException("文件生成失败!");
        }
        return file;
    }

    @Override
    public MonitorSimilarDto getSimiliar(String id) {
        MonitorInfo enty = this.getById(id);
        if (null == enty) {
            throw new MyRuntimeException("此检测信息不存在！");
        }
        QueryWrapper<MonitorInfo> likeQw = new QueryWrapper<MonitorInfo>()
                .eq(enty.getIsDuplicated()!=null,"is_duplicated",0)
                .eq(enty.getStatus()!=null,"status",0)
                .like(enty.getTargetAssetName() != null, "target_asset_name", enty.getTargetAssetName())
                .or(enty.getProblemName() != null).like("problem_name", enty.getProblemName())
                .or(enty.getProblemType() != null).like("problem_type", enty.getProblemType())
                .or(enty.getProblemDescribe() != null).like("problem_describe", enty.getProblemDescribe())
                .or(enty.getEnterpriseName() != null).like("enterprise_name", enty.getEnterpriseName()).orderByAsc("create_time");
        List<MonitorInfo> similiarList = this.list(likeQw);

        List<Object> ids = similiarList.stream().map(e -> e.getId()).collect(Collectors.toList());
        QueryWrapper<MonitorInfo> qw = new QueryWrapper<MonitorInfo>()
                .eq(enty.getIsDuplicated()!=null,"is_duplicated",0)
                .notIn("id", ids)
                .orderByAsc("create_time");
        List<MonitorInfo> notSimiliar = this.list(qw);

//去除查找的本数据项
        for (int i = 0; i < similiarList.size(); i++) {
            if (id.equals(similiarList.get(i).getId().toString())) {
                similiarList.remove(i);
                break;
            }
        }

        MonitorSimilarDto dto = new MonitorSimilarDto();
        dto.setMonitor(enty);
        dto.setSimiliars(similiarList);
        dto.setNotSimiliars(notSimiliar);
        dto.setSimiliarsTotal(similiarList.size());
        dto.setNotSimiliarsTotal(notSimiliar.size());
        return dto;
    }

    @Transactional
    @Override
    public Boolean addEntName(Long monitInfoId, Long entId) {
        QueryWrapper<EntInfo> qw = new QueryWrapper<EntInfo>().eq("id", entId).select("id", "ent_name", "country_id");
        EntInfo entInfoQuery = entInfoService.getOne(qw);
        if (null==entInfoQuery) {
            throw new MyRuntimeException("没有此企业，请检查传入的企业是否正确！");
        }
        //吧相关企业添加到监测信息
        MonitorInfo monitorInfoSave = new MonitorInfo();
        monitorInfoSave.setId(monitInfoId);
        monitorInfoSave.setEnterpriseName(entInfoQuery.getEntName());
        boolean b = this.updateById(monitorInfoSave);
        if (!b) {
            throw new MyRuntimeException("相关企业添加失败，请联系管理员");
        }
        //去给县区增加攻击次数
        Long countryId = entInfoQuery.getCountryId();
        QueryWrapper<Country> countryQw = new QueryWrapper<Country>().eq("id", countryId).select("id", "warning_count");
        Country countryQuery = countryService.getOne(countryQw);
        if (null == countryQuery) {
            throw new MyRuntimeException("该企业没有绑定对应的区县");
        }
        countryQuery.setWarningCount(countryQuery.getWarningCount()+1);
        boolean update = countryService.updateById(countryQuery);
        if (!update) {
            throw new MyRuntimeException("区县增加告警次数失败！");
        }
        return true;
    }

    @Transactional
    @Override
    public String duplicatedMonitor(MonitorDto dto) {
        MonitorNextSrcInfo monitorNextSrcInfo = dto.getMonitorNextSrcInfo();
        monitorNextSrcInfo.setId(null);
        boolean bol = monitorNextSrcInfoService.save(monitorNextSrcInfo);
        if (!bol){
            throw new MyRuntimeException("生成去重后数据源失败！");
        }
        MonitorNext monitorNext = dto.getMonitorNext();
        monitorNext.setId(null);
        monitorNext.setStatus(0);
        monitorNext.setEventInfoId(null);
        monitorNext.setSituMonitorSrcId(monitorNextSrcInfo.getId());
        boolean save = monitorNextService.save(monitorNext);
        if (!save){
            throw new MyRuntimeException("生成新监测信息数据失败！");
        }

        List<Long> ids = dto.getIds();//monitor的ids
        LinkedList<String> srcIds = new LinkedList<>();//元数据的ids
        if ( !CollUtil.isEmpty(ids)){
           //更新监测信息
           for (Long id : ids) {
               MonitorInfo monitorInfo = new MonitorInfo();
               monitorInfo.setId(id);
               monitorInfo.setIsDuplicated(1);
               monitorInfo.setStatus(1);
               monitorInfo.setMonitorNextId(monitorNext.getId());
               boolean b = this.updateById(monitorInfo);
               if (!b){
                   throw new MyRuntimeException("去重前监测信息更新数据失败！ id="+id);
               }
               //获取元数据的id加入list
               QueryWrapper<MonitorInfo> qw = new QueryWrapper<MonitorInfo>().eq("id", id).select("id", "situ_monitor_src_id");
               MonitorInfo monit = this.getOne(qw);
               String situMonitorSrcId = monit.getSituMonitorSrcId();
               if (null!=situMonitorSrcId){
                   srcIds.add(monit.getSituMonitorSrcId());
               }
           }
       }

        if ( !CollUtil.isEmpty(srcIds)){
            for (String srcId : srcIds) {
                SituMonitorSrcInfo situMonitorSrcInfo = new SituMonitorSrcInfo();
                situMonitorSrcInfo.setId(srcId);
                situMonitorSrcInfo.setMonitorNextSrcId(monitorNextSrcInfo.getId());
                boolean b = situMonitorSrcInfoService.updateById(situMonitorSrcInfo);
                if (!b){
                    throw new MyRuntimeException("去重前检测数据源信息更新失败！ id="+srcId);
                }
            }
        }

        return String.valueOf(dto.getMonitorNext().getId());
    }
}
