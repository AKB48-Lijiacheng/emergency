package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.SafeProductServiceInfoQuery;
import com.westcatr.emergency.business.entity.SafeProductServiceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 安全产品及服务表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface SafeProductServiceInfoService extends IService<SafeProductServiceInfo> {

    IPage<SafeProductServiceInfo> iPage(SafeProductServiceInfoQuery query);

    boolean iSave(SafeProductServiceInfo param);

    boolean iUpdate(SafeProductServiceInfo param);

    SafeProductServiceInfo iGetById(Long id);

    boolean iRemove(Long id);
}
