package com.westcatr.emergency.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.westcatr.emergency.business.Util.FileUtil;
import com.westcatr.emergency.business.entity.MonitorInfo;
import com.westcatr.emergency.business.mapper.MonitorInfoMapper;
import com.westcatr.emergency.business.pojo.Dto.ExcelDto.MonitorExcelDto;
import com.westcatr.emergency.business.pojo.Dto.ParamDto.DocDto;
import com.westcatr.emergency.business.pojo.query.MonitorInfoQuery;
import com.westcatr.emergency.business.service.MonitorInfoService;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 监测信息表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-26
 */
@Service
public class MonitorInfoServiceImpl extends ServiceImpl<MonitorInfoMapper, MonitorInfo> implements MonitorInfoService {
    @Value("${file.doc.path}")
    String downLoadFilePath;

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
//todo

    /**
     * 创建导出文档文件
     *
     * @param dto
     * @return
     */
    @Override
    public File buildDoc(DocDto dto) {
        String type = dto.getType();
        String name ="监测信息表";
        String fileSuffix = ".xlsx";
        String time = DateUtil.format(new Date(), "yyyyMMdd");
        String filePath = downLoadFilePath + time + name;
        File file = null;
        fileSuffix = FileUtil.getFileSuffix(type);
        try {
            filePath += fileSuffix;
            file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }else if (!file.exists()){
                file.createNewFile();
            }
            QueryWrapper<MonitorInfo> queryWrapper = new QueryWrapper<>();//条件构造
            List<MonitorInfo> list  = this.list(queryWrapper);
            List<MonitorExcelDto> monitorExcelLists = list.stream().map(m -> {
                MonitorExcelDto monitorExcelDto = new MonitorExcelDto();
                BeanUtil.copyProperties(m, monitorExcelDto);
                return monitorExcelDto;
            }).collect(Collectors.toList());
            EasyExcel.write(file, MonitorExcelDto.class).sheet("监测信息表").doWrite(monitorExcelLists);
        } catch (IOException e) {
           new RuntimeException("文件生成失败!");
        }
        return file;
    }


}
