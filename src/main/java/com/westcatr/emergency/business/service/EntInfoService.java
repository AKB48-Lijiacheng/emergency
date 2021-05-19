package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.EntInfoQuery;
import com.westcatr.emergency.business.entity.EntInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 企业信息表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-04-25
 */
public interface EntInfoService extends IService<EntInfo> {

    IPage<EntInfo> iPage(EntInfoQuery query);

    boolean iSave(EntInfo param);

    boolean iUpdate(EntInfo param);

    EntInfo iGetById(Long id);

    boolean iRemove(Long id);
}
