package com.westcatr.emergency.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author ls
 * @since 2021-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_monitor_next_src_info")
@ApiModel(value="MonitorNextSrcInfo对象", description="")
public class MonitorNextSrcInfo extends Model<MonitorNextSrcInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "态势平台数据源唯一id")
    @TableField("src_id")
    private String srcId;

    @ApiModelProperty(value = "原始日志的ID")
    @TableField("org_log_id")
    private String orgLogId;

    @ApiModelProperty(value = "告警类型id")
    @TableField("event_category_id")
    private Integer eventCategoryId;

    @ApiModelProperty(value = "告警技术分类")
    @TableField("event_technique_type")
    private String eventTechniqueType;

    @ApiModelProperty(value = "告警详细描述")
    @TableField("event_description")
    private String eventDescription;

    @ApiModelProperty(value = "源ip地址long")
    @TableField("src_ip_long")
    private String srcIpLong;

    @ApiModelProperty(value = "原始日志首次时间")
    @TableField("alarm_first_time")
    private String alarmFirstTime;

    @ApiModelProperty(value = "目的地址")
    @TableField("dst_ip")
    private String dstIp;

    @ApiModelProperty(value = "来源IP地址")
    @TableField("src_ip")
    private String srcIp;

    @ApiModelProperty(value = "协议")
    @TableField("protocol")
    private String protocol;

    @ApiModelProperty(value = "事件类型")
    @TableField("event_type")
    private Integer eventType;

    @ApiModelProperty(value = "情报ID")
    @TableField("intelligence_id")
    private String intelligenceId;

    @ApiModelProperty(value = "策略分类id")
    @TableField("strategy_category_id")
    private String strategyCategoryId;

    @ApiModelProperty(value = "类型")
    @TableField("intelligence_type")
    private String intelligenceType;

    @ApiModelProperty(value = "原始日志条数")
    @TableField("event_nums")
    private Integer eventNums;

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

    @ApiModelProperty(value = "SOP")
    @TableField("sop_id")
    private String sopId;

    @ApiModelProperty(value = "告警设备类型")
    @TableField("event_device_type")
    private String eventDeviceType;

    @ApiModelProperty(value = "时效时间")
    @TableField("limit_time")
    private String limitTime;

    @ApiModelProperty(value = "规则ID")
    @TableField("rule_id")
    private Integer ruleId;

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

    @ApiModelProperty(value = "目的地址long")
    @TableField("dst_ip_long")
    private String dstIpLong;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
