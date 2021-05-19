package com.westcatr.emergency.business.docking.h3.pojo.dto.formDto;

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
public class EventFormDto {

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



    @ApiModelProperty("相关附件")
    private List<String> relevantAttachmen;
    @ApiModelProperty("工信部协助处理附件")
    private List<String> miitAttachment;

}
