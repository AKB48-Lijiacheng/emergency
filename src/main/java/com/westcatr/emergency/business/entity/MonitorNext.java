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
 * 监测信息表---去重后 等待开启流程的检测信息表
 * </p>
 *
 * @author ls
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_monitor_next")
@ApiModel(value="MonitorNext对象", description="监测信息表---去重后 等待开启流程的检测信息表")
public class MonitorNext extends Model<MonitorNext> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "目标资产名称")
    @TableField("target_asset_name")
    private String targetAssetName;

    @ApiModelProperty(value = "问题名称")
    @TableField("problem_name")
    private String problemName;

    @ApiModelProperty(value = "监测时间")
    @TableField("monitor_time")
    private Date monitorTime;

    @ApiModelProperty(value = "问题类别")
    @TableField("problem_type")
    private String problemType;

    @ApiModelProperty(value = "问题描述")
    @TableField("problem_describe")
    private String problemDescribe;

    @ApiModelProperty(value = "相关企业")
    @TableField("enterprise_name")
    private String enterpriseName;

    @ApiModelProperty(value = "处置措施")
    @TableField("disposal_measure")
    private String disposalMeasure;

    @ApiModelProperty(value = "是否重大活动")
    @TableField("tf_major_events")
    private Integer tfMajorEvents;

    @ApiModelProperty(value = "是否上级指示")
    @TableField("tf_superior_instructions")
    private Integer tfSuperiorInstructions;

    @ApiModelProperty(value = "预警等级")
    @TableField("warning_level")
    private Integer warningLevel;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "态势平台事件唯一id")
    @TableField("situ_event_id")
    private String situEventId;

    @ApiModelProperty(value = "信息状态(0,未处理;1处理中;2,处理完成)")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "产业分类表id")
    @TableField("industrial_id")
    private Integer industrialId;

    @ApiModelProperty(value = "研判信息表id")
    @TableField("judge_info_id")
    private Integer judgeInfoId;

    @ApiModelProperty(value = "事件信息表id")
    @TableField("event_info_id")
    private Long eventInfoId;

    @ApiModelProperty(value = "态势监测信息源表id")
    @TableField("situ_monitor_src_id")
    private String situMonitorSrcId;

    @ApiModelProperty(value = "h3预警流程实例id")
    @TableField("h3_instance_id")
    private String h3InstanceId;

    @ApiModelProperty(value = "h3预警流程实例id")
    @TableField("h3_event_instance_id")
    private String h3EventInstanceId;

    @ApiModelProperty(value = "h3附件ids")
    @TableField("h3_attach_file_ids")
    private String h3AttachFileIds;

    @ApiModelProperty(value = "h3备注信息")
    @TableField("h3_comment_text")
    private String h3CommentText;

    @ApiModelProperty(value = "信息发布表id")
    @TableField("info_delivery_id")
    private Long infoDeliveryId;

    @ApiModelProperty(value = "相关企业id")
    @TableField("ent_info_id")
    private Long entInfoId;




    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public void setCreateTime(Date createTime) {
    }

    public void setUpdateTime(Date updateTime) {
    }
}
