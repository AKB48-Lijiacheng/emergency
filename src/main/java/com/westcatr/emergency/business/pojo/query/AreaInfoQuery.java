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
 * 区县基本信息表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AreaInfo查询对象", description="区县基本信息表查询对象")
public class AreaInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "行政区名")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String unitName;

    @ApiModelProperty(value = "纬度")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private BigDecimal latitude;

    @ApiModelProperty(value = "经度")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private BigDecimal longtitude;

    @ApiModelProperty(value = "排序值")
    @QueryCondition
    private Integer sort;
}
