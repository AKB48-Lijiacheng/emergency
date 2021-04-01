package com.westcatr.emergency.business.pojo.dto.ExcelDto;

import com.alibaba.excel.annotation.ExcelProperty;
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
public class InfoDeliveryInfoExcelDto extends Model<InfoDeliveryInfoExcelDto> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    @ExcelProperty({"标题"})
    private String title;

    @ApiModelProperty(value = "状态")
    @TableField("state")
    @ExcelProperty({"状态"})
    private String state;

    @ApiModelProperty(value = "展示时段")
    @TableField("exhibi_period")
    @ExcelProperty({"展示时段"})
    private String exhibiPeriod;

    @ApiModelProperty(value = "内容")
    @TableField("content")
    @ExcelProperty({"内容"})
    private String content;

    @TableField("create_time")
    @ExcelProperty({"创建时间"})
    private Date createTime;

    @TableField("update_time")
    @ExcelProperty({"更新时间"})
    private Date updateTime;

    @ApiModelProperty(value = "事件唯一id")
    @TableField("event_id")
    private Long eventId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
