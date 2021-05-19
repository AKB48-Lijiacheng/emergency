package com.westcatr.emergency.business.pojo.query;

import com.westcatr.rd.base.bmybatisplusbootstarter.dto.TimeDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.QueryCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 应急物资存储信息表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EmergencySuppliesInfo查询对象", description="应急物资存储信息表查询对象")
public class EmergencySuppliesInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "物资类型")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String materialType;

    @ApiModelProperty(value = "名称")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String name;

    @ApiModelProperty(value = "存储位置")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String storageLocation;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;
}
