/*
 * @Author: your name
 * @Date: 2019-11-11 11:58:13
 * @LastEditTime: 2019-12-12 14:54:24
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /WitPark-Spring-Cloud/wp-common-util/src/main/java/com/westcatr/rd/wp/base/dto/H3/H3PushInfoDTO.java
 */
package com.westcatr.emergency.business.docking.h3.dto.formDto;

import com.westcatr.emergency.business.docking.h3.dto.DataItemParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 吧表单数据填写传到H3的实体类-----设置批量流程数据项的值
 */
@Data
public class H3PushFormDataDto {

    private String systemCode;
    private String secret;
    @ApiModelProperty("流程模板code")
    private String bizObjectSchemaCode;

    @NotBlank(message = "流程用户id不能为空")
    private String userId;
    @NotBlank(message = "流程表单id不能为空")
    private String bizObjectId;
    @NotBlank(message = "待办任务id不能为空")
    private String workItemId;
    private List<DataItemParam> keyValues;
    private YjFormDto formDto; //前端预警表单提交的数据
    private EventFormDto eventFormDto;//前端事件表单提交数据
}