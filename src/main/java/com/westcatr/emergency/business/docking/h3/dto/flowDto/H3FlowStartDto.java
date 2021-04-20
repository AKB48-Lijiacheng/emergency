package com.westcatr.emergency.business.docking.h3.dto.flowDto;

import com.westcatr.emergency.business.docking.h3.dto.DataItemParam;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 开启流程的参数实体
 * @author lijiacheng
 * @since  2021/4/13
 **/
@Data
public class H3FlowStartDto {
    private String systemCode;
    private String secret;
    private String workflowCode;
    @NotNull(message = "启动流程的用户编码不能为null")
    private String userCode;
    @NotNull(message = "是否结束第一个活动不能为null")
    private boolean finishStart;
    private List<DataItemParam> paramValues;


}