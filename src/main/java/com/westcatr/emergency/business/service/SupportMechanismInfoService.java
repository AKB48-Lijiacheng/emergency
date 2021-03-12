package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.SupportMechanismInfoQuery;
import com.westcatr.emergency.business.entity.SupportMechanismInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 技术支撑机构 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface SupportMechanismInfoService extends IService<SupportMechanismInfo> {

    IPage<SupportMechanismInfo> iPage(SupportMechanismInfoQuery query);

    boolean iSave(SupportMechanismInfo param);

    boolean iUpdate(SupportMechanismInfo param);

    SupportMechanismInfo iGetById(Long id);

    boolean iRemove(Long id);
}
