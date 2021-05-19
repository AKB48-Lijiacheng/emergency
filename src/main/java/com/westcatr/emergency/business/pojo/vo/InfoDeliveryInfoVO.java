package com.westcatr.emergency.business.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.annotation.Select;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("bus_info_delivery_info")
@ApiModel(value="InfoDeliveryInfoVO对象", description="信息发布表VO对象")
public class InfoDeliveryInfoVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "展示时段")
    private String exhibiPeriod;

    @ApiModelProperty(value = "内容")
    private String content;


    @ApiModelProperty(value = "已完成流程唯一id")
    private Long monitorNextId;


    @ApiModelProperty(value = "关联流程名")
    private String  relatedMonitorName;


}
