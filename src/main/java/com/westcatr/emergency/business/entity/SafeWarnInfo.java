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
 * 安全预警表
 * </p>
 *
 * @author ls
 * @since 2021-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_safe_warn_info")
@ApiModel(value="SafeWarnInfo对象", description="安全预警表")
public class SafeWarnInfo extends Model<SafeWarnInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "目标资产名称")
    @TableField("target_asset_name")
    private String targetAssetName;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "事件唯一id")
    @TableField("event_id")
    private Long eventId;

    @ApiModelProperty(value = "处置措施")
    @TableField("disposal_measure")
    private String disposalMeasure;

    @ApiModelProperty(value = "发布机关")
    @TableField("issu_authority")
    private String issuAuthority;

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "预警级别")
    @TableField("alert_level")
    private String alertLevel;

    @ApiModelProperty(value = "预警起始时间")
    @TableField("alert_start_time")
    private Date alertStartTime;

    @ApiModelProperty(value = "可能影响范围")
    @TableField("possible_scope_influence")
    private String possibleScopeInfluence;

    @ApiModelProperty(value = "发展趋势")
    @TableField("development_tren")
    private String developmentTren;

    @ApiModelProperty(value = "相关企业")
    @TableField("enterprise_name")
    private String enterpriseName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
