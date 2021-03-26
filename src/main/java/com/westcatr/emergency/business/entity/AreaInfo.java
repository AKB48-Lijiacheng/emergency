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
import java.math.BigDecimal;

/**
 * <p>
 * 区县基本信息表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_area_info")
@ApiModel(value="AreaInfo对象", description="区县基本信息表")
public class AreaInfo extends Model<AreaInfo> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "行政区名")
    @TableField("unit_name")
    private String unitName;

    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    private BigDecimal latitude;

    @ApiModelProperty(value = "经度")
    @TableField("longtitude")
    private BigDecimal longtitude;

    @ApiModelProperty(value = "排序值")
    @TableField("sort")
    private Integer sort;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
