package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.InfoDeliveryInfoQuery;
import com.westcatr.emergency.business.entity.InfoDeliveryInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 信息发布表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface InfoDeliveryInfoService extends IService<InfoDeliveryInfo> {

    IPage<InfoDeliveryInfo> iPage(InfoDeliveryInfoQuery query);

    boolean iSave(InfoDeliveryInfo param);

    boolean iUpdate(InfoDeliveryInfo param);

    InfoDeliveryInfo iGetById(Long id);

    boolean iRemove(Long id);
}
