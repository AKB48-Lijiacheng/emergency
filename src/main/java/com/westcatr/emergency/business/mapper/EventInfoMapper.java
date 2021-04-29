package com.westcatr.emergency.business.mapper;

import com.westcatr.emergency.business.entity.EventInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 事件信息表 Mapper 接口
 * </p>
 *
 * @author ls
 * @since 2021-04-23
 */
public interface EventInfoMapper extends BaseMapper<EventInfo> {

    @Select("SELECT DATE_FORMAT(create_time,'%Y-%m') time,count(*) as count from bus_event_info  WHERE DATE_SUB(CURDATE(), INTERVAL 1 YEAR)  group by time; ")
    List<Map<Object, Object>> getEventsCount();
}
