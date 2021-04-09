package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.SituMonitorSrcInfoQuery;
import com.westcatr.emergency.business.entity.SituMonitorSrcInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2021-04-08
 */
public interface SituMonitorSrcInfoService extends IService<SituMonitorSrcInfo> {

    IPage<SituMonitorSrcInfo> iPage(SituMonitorSrcInfoQuery query);

    boolean iSave(SituMonitorSrcInfo param);

    boolean iUpdate(SituMonitorSrcInfo param);

    SituMonitorSrcInfo iGetById(Long id);

    boolean iRemove(Long id);
}
