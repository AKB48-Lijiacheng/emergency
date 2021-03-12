package com.westcatr.emergency.business.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.annotation.Select;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 监测信息表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_monitor_info")
@ApiModel(value="MonitorInfoVO对象", description="监测信息表VO对象")
public class MonitorInfoVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "目标资产名称")
    private String targetAssetName;

    @ApiModelProperty(value = "问题名称")
    private String problemName;

    @ApiModelProperty(value = "监测时间")
    private Date monitorTime;

    @ApiModelProperty(value = "问题类别")
    private String problemType;

    @ApiModelProperty(value = "问题描述")
    private String problemDescribe;

    @ApiModelProperty(value = "相关企业")
    private String enterpriseName;

    @ApiModelProperty(value = "处置措施")
    private String disposalMeasure;

    @ApiModelProperty(value = "是否重大活动")
    private String tfMajorEvents;

    @ApiModelProperty(value = "是否上级指示")
    private String tfSuperiorInstructions;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "事件唯一id")
    private Long eventId;


}
