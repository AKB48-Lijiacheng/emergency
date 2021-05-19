package com.westcatr.emergency.business.pojo.query;

import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.QueryCondition;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.TimeDTO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="SecurityIncidentsInfo查询对象", description="应急处置与安全事件管理表查询对象")
public class SecurityIncidentsInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;

    @ApiModelProperty(value = "事件唯一id")
    @QueryCondition
    private Long eventId;

    @ApiModelProperty(value = "相关企业")
    @QueryCondition
    private String enterpriseName;

    @ApiModelProperty(value = "事件等级")
    @QueryCondition
    private String eventLevel;

    @ApiModelProperty(value = "事件描述")
    @QueryCondition
    private String eventDescription;

    @ApiModelProperty(value = "事件原因")
    @QueryCondition
    private String eventIncident;

    @ApiModelProperty(value = "应对措施")
    @QueryCondition
    private String countermeasures;

    @ApiModelProperty(value = "发生单位")
    @QueryCondition
    private String occurrenCompany;

    @ApiModelProperty(value = "发生时间")
    @QueryCondition
    private Date occurrenTime;

    @ApiModelProperty(value = "攻击来源")
    @QueryCondition
    private String sourceAttack;

    @ApiModelProperty(value = "初步影响")
    @QueryCondition
    private String preliminaryImpact;

    @ApiModelProperty(value = "影响范围")
    @QueryCondition
    private String scopeInfluen;
}
