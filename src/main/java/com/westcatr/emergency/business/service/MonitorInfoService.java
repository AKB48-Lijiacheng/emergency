package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.westcatr.emergency.business.entity.MonitorInfo;
import com.westcatr.emergency.business.pojo.Dto.ParamDto.DocDto;
import com.westcatr.emergency.business.pojo.query.MonitorInfoQuery;

import java.io.File;

/**
 * <p>
 * 监测信息表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-26
 */
public interface MonitorInfoService extends IService<MonitorInfo> {

    IPage<MonitorInfo> iPage(MonitorInfoQuery query);

    boolean iSave(MonitorInfo param);

    boolean iUpdate(MonitorInfo param);

    MonitorInfo iGetById(Long id);

    boolean iRemove(Long id);


    public File buildDoc(DocDto dto);
}
