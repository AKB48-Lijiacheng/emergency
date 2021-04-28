package com.westcatr.emergency.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@ApiModel(value="EventInfo对象", description="事件信息表")
public class EventInfo extends Model<EventInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "事件名称")
    @TableField("event_name")
    private String eventName;

    @ApiModelProperty(value = "事件备注")
    @TableField("event_remake")
    private String eventRemake;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "事件发生时间")
    @TableField("event_start_time")
    private Date eventStartTime;

    @ApiModelProperty(value = "攻击来源")
    @TableField("attack_src")
    private String attackSrc;

    @ApiModelProperty(value = "攻击目标")
    @TableField("attack_target")
    private String attackTarget;

    @ApiModelProperty(value = "危害程度")
    @TableField("injury_extent")
    private String injuryExtent;

    @ApiModelProperty(value = "影响范围")
    @TableField("scope_influence")
    private String scopeInfluence;

    @ApiModelProperty(value = "事件发生的单位")
    @TableField("accident_unit")
    private String accidentUnit;

    @ApiModelProperty(value = "初步影响")
    @TableField("Initial_impact")
    private String initialImpact;

    @ApiModelProperty(value = "事件等级(4,3,2,1)依次严重")
    @TableField("event_level")
    private String eventLevel;

    @ApiModelProperty(value = "事件的描述")
    @TableField("event_describle")
    private String eventDescrible;

    @ApiModelProperty(value = "事件的原因")
    @TableField("event_cause")
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
    @TableField(" h3_event_instance_id")
    private String  h3EventInstanceId;

    @ApiModelProperty(value = "事件流程h3的附件ids")
    @TableField(" h3_attach_file_ids")
    private String  h3AttachFileIds;

    @ApiModelProperty(value = "h3审批意见s")
    @TableField(" h3_comment_tests")
    private String  h3CommentTests;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
