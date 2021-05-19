
package com.westcatr.emergency.business.docking.h3.pojo.dto.flowDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 我们系统调用H3结束接口时候所需要传入的实体
 */
@Data
public class H3FlowEndDTO {

    private String systemCode;

    private String secret;

    @ApiModelProperty("流程实例ID")
    private String instanceId;
}