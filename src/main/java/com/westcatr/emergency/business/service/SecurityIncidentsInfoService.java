package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.SecurityIncidentsInfoQuery;
import com.westcatr.emergency.business.entity.SecurityIncidentsInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 应急处置与安全事件管理表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface SecurityIncidentsInfoService extends IService<SecurityIncidentsInfo> {

    IPage<SecurityIncidentsInfo> iPage(SecurityIncidentsInfoQuery query);

    boolean iSave(SecurityIncidentsInfo param);

    boolean iUpdate(SecurityIncidentsInfo param);

    SecurityIncidentsInfo iGetById(Long id);

    boolean iRemove(Long id);
}
