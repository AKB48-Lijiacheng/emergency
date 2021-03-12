package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.EventInfoQuery;
import com.westcatr.emergency.business.entity.EventInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 事件信息表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface EventInfoService extends IService<EventInfo> {

    IPage<EventInfo> iPage(EventInfoQuery query);

    boolean iSave(EventInfo param);

    boolean iUpdate(EventInfo param);

    EventInfo iGetById(Long id);

    boolean iRemove(Long id);
}
