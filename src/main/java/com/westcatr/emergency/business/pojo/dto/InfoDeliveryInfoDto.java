package com.westcatr.emergency.business.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableName;
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
public class InfoDeliveryInfoDto implements Serializable {


    @ApiModelProperty(value = "标题")
    private String title;

 /*   @ApiModelProperty(value = "状态")
    private String state;*/

   /* @ApiModelProperty(value = "展示时段")
    private String exhibiPeriod;
*/
    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "检测信息去重后一id")
    private Long monitorNextId;



}
