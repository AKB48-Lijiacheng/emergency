package com.westcatr.emergency.business.mapper;

import com.westcatr.emergency.business.entity.MonitorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 监测信息表 Mapper 接口
 * </p>
 *
 * @author ls
 * @since 2021-04-09
 */
public interface MonitorInfoMapper extends BaseMapper<MonitorInfo> {


    List<MonitorInfo> getSimiliar(MonitorInfo enty);
}
