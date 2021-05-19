package com.westcatr.emergency.business.pojo.query;

import com.westcatr.rd.base.bmybatisplusbootstarter.dto.TimeDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.QueryCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 字典主信息
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DicMain查询对象", description="字典主信息查询对象")
public class DicMainQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "字典名称")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String dicCode;

    @ApiModelProperty(value = "说明")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String dicExplain;

    @ApiModelProperty(value = "字典类型，0系统字典，无法修改；1用户字典")
    @QueryCondition
    private Integer dicType;
}
