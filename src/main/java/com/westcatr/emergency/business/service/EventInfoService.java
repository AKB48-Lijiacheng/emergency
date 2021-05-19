package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.EventInfoQuery;
import com.westcatr.emergency.business.entity.EventInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 事件信息表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-04-23
 */
public interface EventInfoService extends IService<EventInfo> {

    IPage<EventInfo> iPage(EventInfoQuery query);

    boolean iSave(EventInfo param);

    boolean iUpdate(EventInfo param);

    EventInfo iGetById(Long id);

    boolean iRemove(Long id);

    List<Map<Object, Object>> getEventsCount();
}
