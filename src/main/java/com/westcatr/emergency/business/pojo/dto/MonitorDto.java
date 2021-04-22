package com.westcatr.emergency.business.pojo.dto;

import com.westcatr.emergency.business.entity.MonitorNext;
import lombok.Data;

import java.util.List;

/**
 * @author lijiacheng
 * @Date 2021/4/22
 */
@Data
public class MonitorDto {
    List<Long> ids;
    MonitorNext monitorNext;
}
