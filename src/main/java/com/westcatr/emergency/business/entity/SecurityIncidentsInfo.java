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
 * 应急处置与安全事件管理表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_security_incidents_info")
@ApiModel(value="SecurityIncidentsInfo对象", description="应急处置与安全事件管理表")
public class SecurityIncidentsInfo extends Model<SecurityIncidentsInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "事件唯一id")
    @TableField("event_id")
    private Long eventId;

    @ApiModelProperty(value = "相关企业")
    @TableField("enterprise_name")
    private String enterpriseName;

    @ApiModelProperty(value = "事件等级")
    @TableField("event_level")
    private String eventLevel;

    @ApiModelProperty(value = "事件描述")
    @TableField("event_description")
    private String eventDescription;

    @ApiModelProperty(value = "事件原因")
    @TableField("event_incident")
    private String eventIncident;

    @ApiModelProperty(value = "应对措施")
    @TableField("countermeasures")
    private String countermeasures;

    @ApiModelProperty(value = "发生单位")
    @TableField("occurren_company")
    private String occurrenCompany;

    @ApiModelProperty(value = "发生时间")
    @TableField("occurren_time")
    private Date occurrenTime;

    @ApiModelProperty(value = "攻击来源")
    @TableField("source_attack")
    private String sourceAttack;

    @ApiModelProperty(value = "初步影响")
    @TableField("preliminary_impact")
    private String preliminaryImpact;

    @ApiModelProperty(value = "影响范围")
    @TableField("scope_influen")
    private String scopeInfluen;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
