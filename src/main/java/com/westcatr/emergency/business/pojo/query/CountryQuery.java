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
 * 二级行政区架构
 * </p>
 *
 * @author ls
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Country查询对象", description="二级行政区架构查询对象")
public class CountryQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "区县名")
    @QueryCondition
    private String unitName;

    @ApiModelProperty(value = "上级单位id")
    @QueryCondition
    private Long pid;

    @ApiModelProperty(value = "纬度")
    @QueryCondition
    private BigDecimal latitude;

    @ApiModelProperty(value = "经度")
    @QueryCondition
    private BigDecimal longtitude;

    @ApiModelProperty(value = "部门id")
    @QueryCondition
    private Long did;

    @ApiModelProperty(value = "下设单位")
    @QueryCondition
    private String setDown;

    @QueryCondition
    private String h3OuId;

    @ApiModelProperty(value = "排序值")
    @QueryCondition
    private Integer sort;

    @ApiModelProperty(value = "区划代码")
    @QueryCondition
    private String zoningCode;

    @ApiModelProperty(value = "告警次数")
    @QueryCondition
    private Integer warningCount;
}
