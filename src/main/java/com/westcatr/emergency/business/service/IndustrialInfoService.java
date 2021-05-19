package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.IndustrialInfoQuery;
import com.westcatr.emergency.business.entity.IndustrialInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 产业库大全 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface IndustrialInfoService extends IService<IndustrialInfo> {

    IPage<IndustrialInfo> iPage(IndustrialInfoQuery query);

    boolean iSave(IndustrialInfo param);

    boolean iUpdate(IndustrialInfo param);

    IndustrialInfo iGetById(Long id);

    boolean iRemove(Long id);
}
