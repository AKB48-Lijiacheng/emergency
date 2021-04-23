package com.westcatr.emergency.business.mapper;

import com.westcatr.emergency.business.entity.MonitorNext;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 监测信息表---去重后 等待开启流程的检测信息表 Mapper 接口
 * </p>
 *
 * @author ls
 * @since 2021-04-21
 */
public interface MonitorNextMapper extends BaseMapper<MonitorNext> {

    @Update("update bus_monitor_next b set status=1 where b.h3_instance_id=#{instanceId} ")
    Boolean setMonitorNextStatuByInstanceId(String instanceId);
}
