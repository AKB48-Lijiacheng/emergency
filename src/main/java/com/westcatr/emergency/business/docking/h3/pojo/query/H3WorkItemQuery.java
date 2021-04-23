package com.westcatr.emergency.business.docking.h3.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lijiacheng
 * @Date 2021/4/17
 */

@Data
public class H3WorkItemQuery  {
    private String  systemCode;//系统编码

    private String  secret;//系统秘钥

    @NotNull(message = "用户id不能为null")
    private String  userId;//用户ID
    @NotNull(message = "开始时间不能为null")
    private String startTime="2000-01-01";//开始时间
    @NotNull(message = "结束时间不能为null")
    private String  endTime="2099-01-01";//结束时间
    private Integer  startIndex;//开始索引 >0 (或-1）
    private Integer  endIndex;//结束索引 >0 (或-1）

    @ApiModelProperty("流程模板编码")
    private String  workflowCode;
    @ApiModelProperty("流程实例名称")
    private String  instanceName;

    private Integer page=1;
    private Integer size=10;


}
