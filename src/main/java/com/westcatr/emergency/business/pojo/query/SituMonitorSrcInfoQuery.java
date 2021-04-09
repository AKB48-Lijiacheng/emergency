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
 * 
 * </p>
 *
 * @author ls
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SituMonitorSrcInfo查询对象", description="查询对象")
public class SituMonitorSrcInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private String id;

    @ApiModelProperty(value = "态势平台数据源唯一id")
    @QueryCondition
    private String srcId;

    @ApiModelProperty(value = "原始日志的ID")
    @QueryCondition
    private String orgLogId;

    @ApiModelProperty(value = "告警类型id")
    @QueryCondition
    private Integer eventCategoryId;

    @ApiModelProperty(value = "告警技术分类")
    @QueryCondition
    private String eventTechniqueType;

    @ApiModelProperty(value = "告警详细描述")
    @QueryCondition
    private String eventDescription;

    @ApiModelProperty(value = "源ip地址long")
    @QueryCondition
    private String srcIpLong;

    @ApiModelProperty(value = "原始日志首次时间")
    @QueryCondition
    private String alarmFirstTime;

    @ApiModelProperty(value = "目的地址")
    @QueryCondition
    private String dstIp;

    @ApiModelProperty(value = "来源IP地址")
    @QueryCondition
    private String srcIp;

    @ApiModelProperty(value = "协议")
    @QueryCondition
    private String protocol;

    @ApiModelProperty(value = "事件类型")
    @QueryCondition
    private Integer eventType;

    @ApiModelProperty(value = "情报ID")
    @QueryCondition
    private String intelligenceId;

    @ApiModelProperty(value = "策略分类id")
    @QueryCondition
    private String strategyCategoryId;

    @ApiModelProperty(value = "类型")
    @QueryCondition
    private String intelligenceType;

    @ApiModelProperty(value = "原始日志条数")
    @QueryCondition
    private Integer eventNums;

    @ApiModelProperty(value = "威胁类型")
    @QueryCondition
    private String eventCategoryLabel;

    @ApiModelProperty(value = "告警状态")
    @QueryCondition
    private String eventStatus;

    @ApiModelProperty(value = "等级")
    @QueryCondition
    private String severity;

    @ApiModelProperty(value = "创建时间")
    @QueryCondition
    private String createTime;

    @ApiModelProperty(value = "策略分类名称")
    @QueryCondition
    private String strategyCategoryName;

    @ApiModelProperty(value = "规则名称")
    @QueryCondition
    private String ruleName;

    @ApiModelProperty(value = "风险分值")
    @QueryCondition
    private Integer riskScore;

    @ApiModelProperty(value = "数据中心")
    @QueryCondition
    private String dataCenter;

    @ApiModelProperty(value = "基线数据")
    @QueryCondition
    private String baseline;

    @ApiModelProperty(value = "SOP")
    @QueryCondition
    private String sopId;

    @ApiModelProperty(value = "告警设备类型")
    @QueryCondition
    private String eventDeviceType;

    @ApiModelProperty(value = "时效时间")
    @QueryCondition
    private String limitTime;

    @ApiModelProperty(value = "规则ID")
    @QueryCondition
    private Integer ruleId;

    @ApiModelProperty(value = "rule_id|event_model_source|event_name  字段组合")
    @QueryCondition
    private String strategyInfoTotal;

    @ApiModelProperty(value = "策略类型")
    @QueryCondition
    private String policyType;

    @ApiModelProperty(value = "异常对象")
    @QueryCondition
    private String abnormal;

    @ApiModelProperty(value = "来源IP地址所属国家")
    @QueryCondition
    private String srcCountry;

    @ApiModelProperty(value = "策略分类字段名称")
    @QueryCondition
    private String strategyCategory;

    @ApiModelProperty(value = "内部告警定义")
    @QueryCondition
    private String internalEvent;

    @ApiModelProperty(value = "时效单位")
    @QueryCondition
    private Integer limitUnit;

    @ApiModelProperty(value = "事件名")
    @QueryCondition
    private String eventName;

    @ApiModelProperty(value = "目的地址long")
    @QueryCondition
    private String dstIpLong;

    @ApiModelProperty(value = "告警来源")
    @QueryCondition
    private String eventModelSource;

    @ApiModelProperty(value = "原始日志最后时间")
    @QueryCondition
    private String alarmLastTime;

    @ApiModelProperty(value = "来源IP地址所属省份")
    @QueryCondition
    private String srcProvince;

    @ApiModelProperty(value = "创建时间")
    @QueryCondition
    private Date icreateTime;

    @ApiModelProperty(value = "更新时间")
    @QueryCondition
    private Date updateTime;
}
