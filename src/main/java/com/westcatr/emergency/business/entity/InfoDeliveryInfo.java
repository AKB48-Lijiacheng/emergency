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
 * 信息发布表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_info_delivery_info")
@ApiModel(value="InfoDeliveryInfo对象", description="信息发布表")
public class InfoDeliveryInfo extends Model<InfoDeliveryInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "状态")
    @TableField("state")
    private String state;

    @ApiModelProperty(value = "展示时段")
    @TableField("exhibi_period")
    private String exhibiPeriod;

    @ApiModelProperty(value = "内容")
    @TableField("content")
    private String content;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "关联流程名")
    @TableField("related_monitor_name")
    private String  relatedMonitorName;

    @ApiModelProperty(value = "已完成流程唯一id")
    private Long monitorNextId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
