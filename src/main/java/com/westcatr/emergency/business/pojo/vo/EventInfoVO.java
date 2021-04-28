package com.westcatr.emergency.business.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.annotation.Select;
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
@TableName("bus_event_info")
@ApiModel(value="EventInfoVO对象", description="事件信息表VO对象")
public class EventInfoVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "事件名称")
    private String eventName;

    @ApiModelProperty(value = "事件备注")
    private String eventRemake;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "事件发生时间")
    private Date eventStartTime;

    @ApiModelProperty(value = "攻击来源")
    private String attackSrc;

    @ApiModelProperty(value = "攻击目标")
    private String attackTarget;

    @ApiModelProperty(value = "危害程度")
    private String injuryExtent;

    @ApiModelProperty(value = "影响范围")
    private String scopeInfluence;

    @ApiModelProperty(value = "事件发生的单位")
    private String accidentUnit;

    @ApiModelProperty(value = "初步影响")
    private String initialImpact;

    @ApiModelProperty(value = "事件等级(4,3,2,1)依次严重")
    private String eventLevel;

    @ApiModelProperty(value = "事件的描述")
    private String eventDescrible;

    @ApiModelProperty(value = "事件的原因")
    private String eventCause;

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
    @ApiModelProperty(value = "h3事件流程实例的id")
    private String  h3EventInstanceId;

    @ApiModelProperty(value = "事件流程h3的附件ids")
    private String  h3AttachFileIds;

    @ApiModelProperty(value = "h3审批意见s")
    private String  h3CommentTests;


}
