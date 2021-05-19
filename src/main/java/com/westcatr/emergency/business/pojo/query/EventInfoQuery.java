package com.westcatr.emergency.business.pojo.query;

import com.westcatr.rd.base.bmybatisplusbootstarter.dto.TimeDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.QueryCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 事件信息表
 * </p>
 *
 * @author ls
 * @since 2021-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EventInfo查询对象", description="事件信息表查询对象")
public class EventInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "事件名称")
    @QueryCondition
    private String eventName;

    @ApiModelProperty(value = "事件备注")
    @QueryCondition
    private String eventRemake;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;

    @ApiModelProperty(value = "事件发生时间")
    @QueryCondition
    private Date eventStartTime;

    @ApiModelProperty(value = "攻击来源")
    @QueryCondition
    private String attackSrc;

    @ApiModelProperty(value = "攻击目标")
    @QueryCondition
    private String attackTarget;

    @ApiModelProperty(value = "危害程度")
    @QueryCondition
    private String injuryExtent;

    @ApiModelProperty(value = "影响范围")
    @QueryCondition
    private String scopeInfluence;

    @ApiModelProperty(value = "事件发生的单位")
    @QueryCondition
    private String accidentUnit;

    @ApiModelProperty(value = "初步影响")
    @QueryCondition
    private String initialImpact;

    @ApiModelProperty(value = "事件等级(4,3,2,1)依次严重")
    @QueryCondition
    private String eventLevel;

    @ApiModelProperty(value = "事件的描述")
    @QueryCondition
    private String eventDescrible;

    @ApiModelProperty(value = "事件的原因")
    @QueryCondition
    private String eventCause;


    @ApiModelProperty("处置内容")
    @QueryCondition
    private String detailInfo;
    @ApiModelProperty("处置时间")
    @QueryCondition
    private Date disposalTime;
    @ApiModelProperty("负责人")
    @QueryCondition
    private String personCharge;
    @ApiModelProperty("处置方式")
    @QueryCondition
    private String disposalMethod;
    @ApiModelProperty("支撑机构")
    @QueryCondition
    private String supporMechan;

    @ApiModelProperty(value = "h3事件流程实例的id")
    @QueryCondition
    private String  h3EventInstanceId;

    @ApiModelProperty(value = "事件流程h3的附件ids")
    @QueryCondition
    private String  h3AttachFileIds;

    @ApiModelProperty(value = "h3审批意见s")
    @QueryCondition
    private String  h3CommentTests;

}
