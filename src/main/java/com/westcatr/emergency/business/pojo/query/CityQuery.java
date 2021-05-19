package com.westcatr.emergency.business.pojo.query;

import com.westcatr.rd.base.bmybatisplusbootstarter.dto.TimeDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.QueryCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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
@ApiModel(value="City查询对象", description="市级架构查询对象")
public class CityQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "市级名称")
    @QueryCondition
    private String unitName;

    @ApiModelProperty(value = "纬度")
    @QueryCondition
    private BigDecimal latitude;

    @ApiModelProperty(value = "经度")
    @QueryCondition
    private BigDecimal longtitude;

    @ApiModelProperty(value = "H3ObjectId")
    @QueryCondition
    private String h3Id;
}
