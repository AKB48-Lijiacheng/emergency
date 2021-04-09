package com.westcatr.emergency.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.westcatr.emergency.business.entity.MonitorInfo;
import com.westcatr.emergency.business.mapper.MonitorInfoMapper;
import com.westcatr.emergency.business.pojo.dto.ExcelDto.MonitorExcelDto;
import com.westcatr.emergency.business.pojo.query.MonitorInfoQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorInfoVO;
import com.westcatr.emergency.business.service.MonitorInfoService;
import com.westcatr.emergency.business.utils.FileUtil;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    String  downLoadFilePath;


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
}
