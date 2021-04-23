package com.westcatr.emergency.business.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author lijiacheng
 * @Date 2021/4/23
 */
@Data
public class MonitorNextSrcDto {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;



    @ApiModelProperty(value = "告警技术分类")
    @TableField("event_technique_type")
    private String eventTechniqueType;

    @ApiModelProperty(value = "告警详细描述")
    @TableField("event_description")
    private String eventDescription;


    @ApiModelProperty(value = "目的地址")
    @TableField("dst_ip")
    private String dstIp;

    @ApiModelProperty(value = "来源IP地址")
    @TableField("src_ip")
    private String srcIp;

    @ApiModelProperty(value = "协议")
    @TableField("protocol")
    private String protocol;


    @ApiModelProperty(value = "类型")
    @TableField("intelligence_type")
    private String intelligenceType;

    @ApiModelProperty(value = "威胁类型")
    @TableField("event_category_label")
    private String eventCategoryLabel;

    @ApiModelProperty(value = "告警状态")
    @TableField("event_status")
    private String eventStatus;

    @ApiModelProperty(value = "等级")
    @TableField("severity")
    private String severity;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private String createTime;

    @ApiModelProperty(value = "策略分类名称")
    @TableField("strategy_category_name")
    private String strategyCategoryName;

    @ApiModelProperty(value = "规则名称")
    @TableField("rule_name")
    private String ruleName;

    @ApiModelProperty(value = "风险分值")
    @TableField("risk_score")
    private Integer riskScore;

    @ApiModelProperty(value = "数据中心")
    @TableField("data_center")
    private String dataCenter;

    @ApiModelProperty(value = "基线数据")
    @TableField("baseline")
    private String baseline;

    @ApiModelProperty(value = "告警设备类型")
    @TableField("event_device_type")
    private String eventDeviceType;

    @ApiModelProperty(value = "时效时间")
    @TableField("limit_time")
    private String limitTime;


    @ApiModelProperty(value = "rule_id|event_model_source|event_name  字段组合")
    @TableField("strategy_info_total")
    private String strategyInfoTotal;

    @ApiModelProperty(value = "策略类型")
    @TableField("policy_type")
    private String policyType;

    @ApiModelProperty(value = "异常对象")
    @TableField("abnormal")
    private String abnormal;

    @ApiModelProperty(value = "来源IP地址所属国家")
    @TableField("src_country")
    private String srcCountry;

    @ApiModelProperty(value = "策略分类字段名称")
    @TableField("strategy_category")
    private String strategyCategory;

    @ApiModelProperty(value = "内部告警定义")
    @TableField("internal_event")
    private String internalEvent;

    @ApiModelProperty(value = "时效单位")
    @TableField("limit_unit")
    private Integer limitUnit;

    @ApiModelProperty(value = "事件名")
    @TableField("event_name")
    private String eventName;

    @ApiModelProperty(value = "告警来源")
    @TableField("event_model_source")
    private String eventModelSource;

    @ApiModelProperty(value = "原始日志最后时间")
    @TableField("alarm_last_time")
    private String alarmLastTime;

    @ApiModelProperty(value = "来源IP地址所属省份")
    @TableField("src_province")
    private String srcProvince;

    @ApiModelProperty(value = "创建时间")
    @TableField("icreate_time")
    private Date icreateTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;
}
