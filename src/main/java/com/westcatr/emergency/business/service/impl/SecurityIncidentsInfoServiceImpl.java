package com.westcatr.emergency.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.westcatr.emergency.business.entity.SecurityIncidentsInfo;
import com.westcatr.emergency.business.mapper.SecurityIncidentsInfoMapper;
import com.westcatr.emergency.business.pojo.dto.ExcelDto.SecurityIncidentsInfoExcelDto;
import com.westcatr.emergency.business.pojo.query.SecurityIncidentsInfoQuery;
import com.westcatr.emergency.business.pojo.vo.SecurityIncidentsInfoVO;
import com.westcatr.emergency.business.service.SecurityIncidentsInfoService;
import com.westcatr.emergency.business.util.FileUtil;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 应急处置与安全事件管理表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class SecurityIncidentsInfoServiceImpl extends ServiceImpl<SecurityIncidentsInfoMapper, SecurityIncidentsInfo> implements SecurityIncidentsInfoService {
    @Value("${file.doc.path}")
    static String  downLoadFilePath;
    @Override
    public IPage<SecurityIncidentsInfo> iPage(SecurityIncidentsInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<SecurityIncidentsInfo>().create(query));
    }

    @Override
    public boolean iSave(SecurityIncidentsInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(SecurityIncidentsInfo param) {
        return this.updateById(param);
    }

    @Override
    public SecurityIncidentsInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }

    @Override
    public File buildDoc(String type, List<SecurityIncidentsInfoVO> records) {
        String name = "监测信息表";
        String fileSuffix = ".xlsx";
        long time = System.currentTimeMillis();
        String filePath =downLoadFilePath  + time + name;
        File file = null;
        fileSuffix = FileUtil.getFileSuffix(type);
        try {
            filePath += fileSuffix;//文件路径
            cn.hutool.core.io.FileUtil.touch(filePath);//创建目录和文件
            List<SecurityIncidentsInfoExcelDto> list = records.stream().map(m -> {
                SecurityIncidentsInfoExcelDto dto = new SecurityIncidentsInfoExcelDto();
                BeanUtil.copyProperties(m, dto);
                return dto;
            }).collect(Collectors.toList());
            EasyExcel.write(file, SecurityIncidentsInfoExcelDto.class).sheet(name).doWrite(list);
        } catch (Exception e) {
            new RuntimeException("文件生成失败!");
        }
        return file;
    }
}
