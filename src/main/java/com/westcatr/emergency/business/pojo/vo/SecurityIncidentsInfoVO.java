package com.westcatr.emergency.business.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.annotation.Select;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="SecurityIncidentsInfoVO对象", description="应急处置与安全事件管理表VO对象")
public class SecurityIncidentsInfoVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "事件唯一id")
    private Long eventId;

    @ApiModelProperty(value = "相关企业")
    private String enterpriseName;

    @ApiModelProperty(value = "事件等级")
    private String eventLevel;

    @ApiModelProperty(value = "事件描述")
    private String eventDescription;

    @ApiModelProperty(value = "事件原因")
    private String eventIncident;

    @ApiModelProperty(value = "应对措施")
    private String countermeasures;

    @ApiModelProperty(value = "发生单位")
    private String occurrenCompany;

    @ApiModelProperty(value = "发生时间")
    private Date occurrenTime;

    @ApiModelProperty(value = "攻击来源")
    private String sourceAttack;

    @ApiModelProperty(value = "初步影响")
    private String preliminaryImpact;

    @ApiModelProperty(value = "影响范围")
    private String scopeInfluen;


}
