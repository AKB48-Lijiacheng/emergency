package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.MonitorInfoQuery;
import com.westcatr.emergency.business.entity.MonitorInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 监测信息表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface MonitorInfoService extends IService<MonitorInfo> {

    IPage<MonitorInfo> iPage(MonitorInfoQuery query);

    boolean iSave(MonitorInfo param);

    boolean iUpdate(MonitorInfo param);

    MonitorInfo iGetById(Long id);

    boolean iRemove(Long id);
}
