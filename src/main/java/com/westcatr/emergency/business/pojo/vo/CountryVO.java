package com.westcatr.emergency.business.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.annotation.Select;
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
@TableName("bus_country")
@ApiModel(value="CountryVO对象", description="二级行政区架构VO对象")
public class CountryVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "区县名")
    private String unitName;

    @ApiModelProperty(value = "上级单位id")
    private Long pid;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "经度")
    private BigDecimal longtitude;

    @ApiModelProperty(value = "部门id")
    private Long did;

    @ApiModelProperty(value = "下设单位")
    private String setDown;

    private String h3OuId;

    @ApiModelProperty(value = "排序值")
    private Integer sort;

    @ApiModelProperty(value = "区划代码")
    private String zoningCode;

    @ApiModelProperty(value = "告警次数")
    private Integer warningCount;


}
