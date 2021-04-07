package com.westcatr.emergency.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.westcatr.emergency.business.entity.InfoDeliveryInfo;
import com.westcatr.emergency.business.mapper.InfoDeliveryInfoMapper;
import com.westcatr.emergency.business.pojo.dto.ExcelDto.InfoDeliveryInfoExcelDto;
import com.westcatr.emergency.business.pojo.query.InfoDeliveryInfoQuery;
import com.westcatr.emergency.business.pojo.vo.InfoDeliveryInfoVO;
import com.westcatr.emergency.business.service.InfoDeliveryInfoService;
import com.westcatr.emergency.business.utils.filUtils.FileUtil;
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
 * 信息发布表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class InfoDeliveryInfoServiceImpl extends ServiceImpl<InfoDeliveryInfoMapper, InfoDeliveryInfo> implements InfoDeliveryInfoService {
    @Value("${file.doc.path}")
    String downLoadFilePath;
    @Override
    public IPage<InfoDeliveryInfo> iPage(InfoDeliveryInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<InfoDeliveryInfo>().create(query));
    }

    @Override
    public boolean iSave(InfoDeliveryInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(InfoDeliveryInfo param) {
        return this.updateById(param);
    }

    @Override
    public InfoDeliveryInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }

    @Override
    public File buildDoc(String type, List<InfoDeliveryInfoVO> records) {
        String name = "信息发布表";
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
            List<InfoDeliveryInfoExcelDto> list = records.stream().map(m -> {
                InfoDeliveryInfoExcelDto dto = new InfoDeliveryInfoExcelDto();
                BeanUtil.copyProperties(m, dto);
                return dto;
            }).collect(Collectors.toList());
            EasyExcel.write(file, InfoDeliveryInfoExcelDto.class).sheet("信息发布表").doWrite(list);
        } catch (IOException e) {
            new RuntimeException("文件生成失败!");
        }
        return file;
    }


}
