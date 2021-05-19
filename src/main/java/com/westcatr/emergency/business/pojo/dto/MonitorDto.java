package com.westcatr.emergency.business.pojo.dto;

import com.westcatr.emergency.business.entity.MonitorNext;
import com.westcatr.emergency.business.entity.MonitorNextSrcInfo;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author lijiacheng
 * @Date 2021/4/22
 */
@Data
public class MonitorDto {
    List<Long> ids;
    @NotNull(message = "请传入新生成的检测数据")
    MonitorNext monitorNext;
    @NotNull(message = "请传入新生成的详情数据")
    MonitorNextSrcInfo monitorNextSrcInfo;
}
