package com.westcatr.emergency.business.mapper;

import com.westcatr.emergency.business.entity.MonitorNext;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 监测信息表---去重后 等待开启流程的检测信息表 Mapper 接口
 * </p>
 *
 * @author ls
 * @since 2021-04-21
 */
public interface MonitorNextMapper extends BaseMapper<MonitorNext> {

    @Update("update bus_monitor_next b set status=#{statuNum} where b.h3_instance_id=#{instanceId} ")
    Boolean setMonitorNextStatuByInstanceId(String instanceId,Integer statuNum);

    @Select("SELECT DATE_FORMAT(create_time,'%Y-%m') time,count(*) as count from bus_monitor_next  WHERE DATE_SUB(CURDATE(), INTERVAL 1 YEAR)  group by time; ")
    List<Map<Object, Object>> getMonitorNextCount();
}
