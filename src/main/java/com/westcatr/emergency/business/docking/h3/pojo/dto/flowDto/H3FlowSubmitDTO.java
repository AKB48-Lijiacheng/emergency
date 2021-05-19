
package com.westcatr.emergency.business.docking.h3.pojo.dto.flowDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 我们系统调用H3提交接口时候所需要传入的实体
 */
@Data
public class H3FlowSubmitDTO {

    private String systemCode;

    private String secret;
    @ApiModelProperty("H3UserId")
    private String userId;
    @ApiModelProperty("待办流程id")
    private String workItemId;
    private String commentText;
}