package com.westcatr.emergency.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.westcatr.emergency.business.entity.MonitorInfo;
import com.westcatr.emergency.business.entity.MonitorNext;
import com.westcatr.emergency.business.mapper.MonitorInfoMapper;
import com.westcatr.emergency.business.pojo.dto.ExcelDto.MonitorExcelDto;
import com.westcatr.emergency.business.pojo.dto.MonitorDto;
import com.westcatr.emergency.business.pojo.query.MonitorInfoQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorInfoVO;
import com.westcatr.emergency.business.pojo.vo.MonitorSimilarDto;
import com.westcatr.emergency.business.service.MonitorInfoService;
import com.westcatr.emergency.business.service.MonitorNextService;
import com.westcatr.emergency.business.utils.FileUtil;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
    public String duplicatedMonitor(MonitorDto dto) {
        MonitorNext monitorNext = dto.getMonitorNext();

        monitorNext.setStatus(0);
        monitorNext.setEventInfoId(null);
        monitorNext.setSituMonitorSrcId(null);
        boolean save = monitorNextService.save(dto.getMonitorNext());
        if (save){
            throw new MyRuntimeException("生成新数据失败！");
        }
        List<Long> ids = dto.getIds();
       if ( !CollUtil.isEmpty(ids)){
           for (Long id : ids) {
               MonitorInfo monitorInfo = new MonitorInfo();
               monitorInfo.setId(id);
               monitorInfo.setIsDuplicated(1);
               monitorInfo.setStatus(1);
               monitorInfo.setMonitorNextId(monitorNext.getId());
               boolean b = this.updateById(monitorInfo);
               if (b){
                   throw new MyRuntimeException("监测信息更新数据失败！ id="+id);
               }
           }
       }


        return String.valueOf(dto.getMonitorNext().getId());
    }
}
