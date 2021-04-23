package com.westcatr.emergency.business.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.annotation.Select;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 监测信息表---去重后 等待开启流程的检测信息表
 * </p>
 *
 * @author ls
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_monitor_next")
@ApiModel(value="MonitorNextVO对象", description="监测信息表---去重后 等待开启流程的检测信息表VO对象")
public class MonitorNextVO implements Serializable {

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
    private Integer tfMajorEvents;

    @ApiModelProperty(value = "是否上级指示")
    private Integer tfSuperiorInstructions;


    @ApiModelProperty(value = "预警等级")
    private Integer warningLevel;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "态势平台事件唯一id")
    private String situEventId;

    @ApiModelProperty(value = "信息状态(0,处置中;1,处置完成;2,关闭)")
    private Integer status;

    @ApiModelProperty(value = "产业分类表id")
    private Integer industrialId;

    @ApiModelProperty(value = "研判信息表id")
    private Integer judgeInfoId;

    @ApiModelProperty(value = "事件信息表id")
    private Long eventInfoId;

    @ApiModelProperty(value = "态势监测信息源表id")
    private String situMonitorSrcId;

    @ApiModelProperty(value = "h3流程实例id")
    private String h3InstanceId;



}
