package com.westcatr.emergency.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.westcatr.emergency.business.entity.SafeWarnInfo;
import com.westcatr.emergency.business.mapper.SafeWarnInfoMapper;
import com.westcatr.emergency.business.pojo.dto.ExcelDto.MonitorExcelDto;
import com.westcatr.emergency.business.pojo.dto.ExcelDto.SafeWarnInfoExcelDto;
import com.westcatr.emergency.business.pojo.query.SafeWarnInfoQuery;
import com.westcatr.emergency.business.pojo.vo.SafeWarnInfoVO;
import com.westcatr.emergency.business.service.SafeWarnInfoService;
import com.westcatr.emergency.business.util.FileUtil;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 安全预警表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-29
 */
@Service
public class SafeWarnInfoServiceImpl extends ServiceImpl<SafeWarnInfoMapper, SafeWarnInfo> implements SafeWarnInfoService {
    @Value("${file.doc.path}")
    String downLoadFilePath;
    @Override
    public IPage<SafeWarnInfo> iPage(SafeWarnInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<SafeWarnInfo>().create(query));
    }

    @Override
    public boolean iSave(SafeWarnInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(SafeWarnInfo param) {
        return this.updateById(param);
    }

    @Override
    public SafeWarnInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }

    @Override
    public File buildDoc(String type, List<SafeWarnInfoVO> records) {
        String name = "预警信息表";
        String fileSuffix = ".xlsx";
        long time = System.currentTimeMillis();
        String filePath = downLoadFilePath + time + name;
        File file = null;
        fileSuffix = FileUtil.getFileSuffix(type);
        try {
            filePath += fileSuffix;
            file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            } else if (!file.exists()) {
                file.createNewFile();
            }
            List<SafeWarnInfoExcelDto> list = records.stream().map(m -> {
                SafeWarnInfoExcelDto dto = new SafeWarnInfoExcelDto();
                BeanUtil.copyProperties(m, dto);
                return dto;
            }).collect(Collectors.toList());
            EasyExcel.write(file, MonitorExcelDto.class).sheet(name).doWrite(list);
        } catch (IOException e) {
            new RuntimeException("文件生成失败!");
        }
        return file;
    }
}
