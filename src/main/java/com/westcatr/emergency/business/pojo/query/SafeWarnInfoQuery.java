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
 * 安全预警表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SafeWarnInfo查询对象", description="安全预警表查询对象")
public class SafeWarnInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "目标资产名称")
    @QueryCondition
    private String targetAssetName;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;

    @ApiModelProperty(value = "事件唯一id")
    @QueryCondition
    private Long eventId;

    @ApiModelProperty(value = "处置措施")
    @QueryCondition
    private String disposalMeasure;

    @ApiModelProperty(value = "发布机关")
    @QueryCondition
    private String issuAuthority;

    @ApiModelProperty(value = "类型")
    @QueryCondition
    private String type;

    @ApiModelProperty(value = "预警级别")
    @QueryCondition
    private String alertLevel;

    @ApiModelProperty(value = "预警起始时间")
    @QueryCondition
    private Date alertStartTime;

    @ApiModelProperty(value = "可能影响范围")
    @QueryCondition
    private String possibleScopeInfluence;

    @ApiModelProperty(value = "发展趋势")
    @QueryCondition
    private String developmentTren;
}
