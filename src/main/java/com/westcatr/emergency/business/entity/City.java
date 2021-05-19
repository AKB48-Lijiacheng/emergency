package com.westcatr.emergency.business.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * 市级架构
 * </p>
 *
 * @author ls
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_city")
@ApiModel(value="City对象", description="市级架构")
public class City extends Model<City> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "市级名称")
    @TableField("unit_name")
    private String unitName;

    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    private BigDecimal latitude;

    @ApiModelProperty(value = "经度")
    @TableField("longtitude")
    private BigDecimal longtitude;

    @ApiModelProperty(value = "H3ObjectId")
    @TableField("h3_id")
    private String h3Id;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
