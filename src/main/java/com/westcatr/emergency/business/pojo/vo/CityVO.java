package com.westcatr.emergency.business.pojo.vo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.annotation.Select;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="CityVO对象", description="市级架构VO对象")
public class CityVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "市级名称")
    private String unitName;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "经度")
    private BigDecimal longtitude;

    @ApiModelProperty(value = "H3ObjectId")
    private String h3Id;


}
