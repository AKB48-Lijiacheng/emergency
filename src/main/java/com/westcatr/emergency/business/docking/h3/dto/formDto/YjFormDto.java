package com.westcatr.emergency.business.docking.h3.dto.formDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 预警流程前端表单数据项
 * @author lijiacheng
 * @Date 2021/4/14
 */
@Data
public class YjFormDto {
//前端表单提交
    @ApiModelProperty("发起人")
    private String startUserName;

    @ApiModelProperty("发起时间")
    private Date startTime;

    @ApiModelProperty("所属组织")
    private String startUserOrgan;

    @ApiModelProperty("流水号")
    private String sequenceNo;

    @ApiModelProperty("是否发展中心研判")
    private String TfDevCenter;

    @ApiModelProperty("预警等级")
    private String EarlyWarnLevel;

    @ApiModelProperty("审批人")
    private String approved;
    @ApiModelProperty("表单上传的附件ids")
    private List<String> attachIds;

    @ApiModelProperty("备注信息")
    private List<String> RemakeInfo;







}
