
package com.westcatr.emergency.business.docking.h3.dto.flowDto;

import lombok.Data;

/**
 * 我们系统调用H3提交接口时候所需要传入的实体
 */
@Data
public class H3FlowSubmitDTO {

    private String systemCode;

    private String secret;

    private String userId;

    private String workItemId;

    private String commentText;
}