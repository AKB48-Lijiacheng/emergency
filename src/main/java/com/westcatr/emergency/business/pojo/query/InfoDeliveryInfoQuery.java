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
 * 信息发布表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="InfoDeliveryInfo查询对象", description="信息发布表查询对象")
public class InfoDeliveryInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "标题")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String title;

    @ApiModelProperty(value = "状态")
    @QueryCondition
    private String state;

    @ApiModelProperty(value = "展示时段")
    @QueryCondition
    private String exhibiPeriod;

    @ApiModelProperty(value = "内容")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String content;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;

    @ApiModelProperty(value = "事件唯一id")
    @QueryCondition
    private Long eventId;
}
