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
 * 二级行政区架构
 * </p>
 *
 * @author ls
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_country")
@ApiModel(value="Country对象", description="二级行政区架构")
public class Country extends Model<Country> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "区县名")
    @TableField("unit_name")
    private String unitName;

    @ApiModelProperty(value = "上级单位id")
    @TableField("pid")
    private Long pid;

    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    private BigDecimal latitude;

    @ApiModelProperty(value = "经度")
    @TableField("longtitude")
    private BigDecimal longtitude;

    @ApiModelProperty(value = "部门id")
    @TableField("did")
    private Long did;

    @ApiModelProperty(value = "下设单位")
    @TableField("set_down")
    private String setDown;

    @TableField("h3_ou_id")
    private String h3OuId;

    @ApiModelProperty(value = "排序值")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "区划代码")
    @TableField("zoning_code")
    private String zoningCode;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
