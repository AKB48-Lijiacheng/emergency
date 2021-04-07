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
 * 监测信息表
 * </p>
 *
 * @author ls
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_monitor_info")
@ApiModel(value="MonitorInfo对象", description="监测信息表")
public class MonitorInfo extends Model<MonitorInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
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

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "事件唯一id")
    @TableField("event_id")
    private Long eventId;

    @ApiModelProperty(value = "信息状态(0,处置中;1,处置完成;2,关闭)")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "产业分类表唯一id")
    @TableField("industrial_id")
    private Integer industrialId;

    @ApiModelProperty(value = "该监测信息是否重复")
    @TableField("is_duplicated")
    private Integer isDuplicated;

    @ApiModelProperty(value = "研判信息表唯一id")
    @TableField("judge_info_id")
    private Integer judgeInfoId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
