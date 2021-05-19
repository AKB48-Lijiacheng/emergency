package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.EmergencySuppliesInfoQuery;
import com.westcatr.emergency.business.entity.EmergencySuppliesInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 应急物资存储信息表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface EmergencySuppliesInfoService extends IService<EmergencySuppliesInfo> {

    IPage<EmergencySuppliesInfo> iPage(EmergencySuppliesInfoQuery query);

    boolean iSave(EmergencySuppliesInfo param);

    boolean iUpdate(EmergencySuppliesInfo param);

    EmergencySuppliesInfo iGetById(Long id);

    boolean iRemove(Long id);
}
