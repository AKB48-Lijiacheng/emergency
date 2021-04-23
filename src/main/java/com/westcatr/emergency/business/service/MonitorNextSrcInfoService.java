package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.MonitorNextSrcInfoQuery;
import com.westcatr.emergency.business.entity.MonitorNextSrcInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.westcatr.emergency.business.pojo.vo.MonitorNextVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2021-04-23
 */
public interface MonitorNextSrcInfoService extends IService<MonitorNextSrcInfo> {

    IPage<MonitorNextSrcInfo> iPage(MonitorNextSrcInfoQuery query);

    boolean iSave(MonitorNextSrcInfo param);

    boolean iUpdate(MonitorNextSrcInfo param);

    MonitorNextSrcInfo iGetById(Long id);

    boolean iRemove(Long id);

}
