package com.westcatr.emergency.business.docking.h3.vo;

import com.westcatr.emergency.business.docking.h3.dto.attachFileDto.H3AttachFileInfoDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 返回给前端预警流程表单数据项
 * @author lijiacheng
 * @Date 2021/4/14
 */
@Data
public class YjFormVo {
    //前端表单提交
    @ApiModelProperty("表单Id")
    private String bizObjectId;


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

    @ApiModelProperty("备注信息")
    private String CommentText;


    //H3返回表单信息
    @ApiModelProperty("H3返回备注信息")
    private List<H3CommentVo> CommentTexts;

    @ApiModelProperty("H3返回信息附件")
    private List<H3AttachFileInfoDto> attachFilesInfo;





}
