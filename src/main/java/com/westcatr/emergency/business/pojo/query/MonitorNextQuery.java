package com.westcatr.emergency.business.pojo.query;

import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.QueryCondition;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.TimeDTO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="MonitorNext查询对象", description="监测信息表---去重后 等待开启流程的检测信息表查询对象")
public class MonitorNextQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "目标资产名称")
    @QueryCondition
    private String targetAssetName;

    @ApiModelProperty(value = "问题名称")
    @QueryCondition
    private String problemName;

    @ApiModelProperty(value = "监测时间")
    @QueryCondition
    private Date monitorTime;

    @ApiModelProperty(value = "问题类别")
    @QueryCondition
    private String problemType;

    @ApiModelProperty(value = "问题描述")
    @QueryCondition
    private String problemDescribe;

    @ApiModelProperty(value = "相关企业")
    @QueryCondition
    private String enterpriseName;

    @ApiModelProperty(value = "处置措施")
    @QueryCondition
    private String disposalMeasure;

    @ApiModelProperty(value = "是否重大活动")
    @QueryCondition
    private Integer tfMajorEvents;

    @ApiModelProperty(value = "是否上级指示")
    @QueryCondition
    private Integer tfSuperiorInstructions;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;

    @ApiModelProperty(value = "态势平台事件唯一id")
    @QueryCondition
    private String situEventId;

    @ApiModelProperty(value = "信息状态(0,处置中;1,处置完成;2,关闭)")
    @QueryCondition
    private Integer status;

    @ApiModelProperty(value = "产业分类表id")
    @QueryCondition
    private Integer industrialId;

    @ApiModelProperty(value = "研判信息表id")
    @QueryCondition
    private Integer judgeInfoId;

    @ApiModelProperty(value = "事件信息表id")
    @QueryCondition
    private Long eventInfoId;

    @ApiModelProperty(value = "态势监测信息源表id")
    @QueryCondition
    private String situMonitorSrcId;
}
