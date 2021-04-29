package com.westcatr.emergency.business.docking.h3.pojo.vo;

import com.westcatr.emergency.business.docking.h3.pojo.dto.attachFileDto.H3AttachFileInfoDto;
import com.westcatr.emergency.business.entity.MonitorNext;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 事件流程前端表单数据项
 *
 * @author lijiacheng
 * @Date 2021/4/14
 */
@Data
public class EventFormVo {
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

    @ApiModelProperty("处置内容")
    private String detailInfo;
    @ApiModelProperty("处置时间")
    private Date disposalTime;
    @ApiModelProperty("负责人")
    private String personCharge;
    @ApiModelProperty("处置方式")
    private String disposalMethod;
    @ApiModelProperty("支撑机构")
    private String supporMechan;

    @ApiModelProperty("标题")
    private String Title;

    @ApiModelProperty("预警等级")
    private String EarlyWarnLevel;
//    @ApiModelProperty("相关附件")
//    private String relevantAttachmen;
//    @ApiModelProperty("工信部协助处理附件")
//    private String miitAttachment;

    //H3返回表单信息
    @ApiModelProperty("H3返回审批意见")
    private List<H3CommentVo> CommentTexts;

    @ApiModelProperty("H3返回信息附件")
    private List<H3AttachFileInfoDto> attachFilesInfo;

    //对应数据项信息
    MonitorNext monitorNext;



}
