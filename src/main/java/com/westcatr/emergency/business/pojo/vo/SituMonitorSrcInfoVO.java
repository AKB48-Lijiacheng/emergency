package com.westcatr.emergency.business.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.annotation.Select;
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
@TableName("situ_monitor_src_info")
@ApiModel(value="SituMonitorSrcInfoVO对象", description="VO对象")
public class SituMonitorSrcInfoVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private String id;

    @ApiModelProperty(value = "态势平台数据源唯一id")
    private String srcId;

    @ApiModelProperty(value = "原始日志的ID")
    private String orgLogId;

    @ApiModelProperty(value = "告警类型id")
    private Integer eventCategoryId;

    @ApiModelProperty(value = "告警技术分类")
    private String eventTechniqueType;

    @ApiModelProperty(value = "告警详细描述")
    private String eventDescription;

    @ApiModelProperty(value = "源ip地址long")
    private String srcIpLong;

    @ApiModelProperty(value = "原始日志首次时间")
    private String alarmFirstTime;

    @ApiModelProperty(value = "目的地址")
    private String dstIp;

    @ApiModelProperty(value = "来源IP地址")
    private String srcIp;

    @ApiModelProperty(value = "协议")
    private String protocol;

    @ApiModelProperty(value = "事件类型")
    private Integer eventType;

    @ApiModelProperty(value = "情报ID")
    private String intelligenceId;

    @ApiModelProperty(value = "策略分类id")
    private String strategyCategoryId;

    @ApiModelProperty(value = "类型")
    private String intelligenceType;

    @ApiModelProperty(value = "原始日志条数")
    private Integer eventNums;

    @ApiModelProperty(value = "威胁类型")
    private String eventCategoryLabel;

    @ApiModelProperty(value = "告警状态")
    private String eventStatus;

    @ApiModelProperty(value = "等级")
    private String severity;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "策略分类名称")
    private String strategyCategoryName;

    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @ApiModelProperty(value = "风险分值")
    private Integer riskScore;

    @ApiModelProperty(value = "数据中心")
    private String dataCenter;

    @ApiModelProperty(value = "基线数据")
    private String baseline;

    @ApiModelProperty(value = "SOP")
    private String sopId;

    @ApiModelProperty(value = "告警设备类型")
    private String eventDeviceType;

    @ApiModelProperty(value = "时效时间")
    private String limitTime;

    @ApiModelProperty(value = "规则ID")
    private Integer ruleId;

    @ApiModelProperty(value = "rule_id|event_model_source|event_name  字段组合")
    private String strategyInfoTotal;

    @ApiModelProperty(value = "策略类型")
    private String policyType;

    @ApiModelProperty(value = "异常对象")
    private String abnormal;

    @ApiModelProperty(value = "来源IP地址所属国家")
    private String srcCountry;

    @ApiModelProperty(value = "策略分类字段名称")
    private String strategyCategory;

    @ApiModelProperty(value = "内部告警定义")
    private String internalEvent;

    @ApiModelProperty(value = "时效单位")
    private Integer limitUnit;

    @ApiModelProperty(value = "事件名")
    private String eventName;

    @ApiModelProperty(value = "目的地址long")
    private String dstIpLong;

    @ApiModelProperty(value = "告警来源")
    private String eventModelSource;

    @ApiModelProperty(value = "原始日志最后时间")
    private String alarmLastTime;

    @ApiModelProperty(value = "来源IP地址所属省份")
    private String srcProvince;

    @ApiModelProperty(value = "创建时间")
    private Date icreateTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "去重后数据源表id")
    private Date monitorNextSrcId;

}
