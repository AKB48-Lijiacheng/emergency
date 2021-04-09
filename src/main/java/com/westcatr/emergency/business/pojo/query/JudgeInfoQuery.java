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
 * 研判信息表（1-4级，1：特别重大，红色；2：重大，橙色；3：较大，黄色；4：一般，蓝色;0：灰色 无 ）
 * </p>
 *
 * @author ls
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="JudgeInfo查询对象", description="研判信息表查询对象")
public class JudgeInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Integer id;

    @ApiModelProperty(value = "研判依据")
    @QueryCondition
    private String judgeBased;

    @ApiModelProperty(value = "研判等级(1-4级=1:特别重大,红色;2:重大,橙色;3:较大,黄色;4:一般,蓝色;0:无,灰色;)")
    @QueryCondition
    private Integer judgeDegree;
}
