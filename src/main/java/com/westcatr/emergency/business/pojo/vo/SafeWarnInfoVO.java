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
 * 安全预警表
 * </p>
 *
 * @author ls
 * @since 2021-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_safe_warn_info")
@ApiModel(value="SafeWarnInfoVO对象", description="安全预警表VO对象")
public class SafeWarnInfoVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "目标资产名称")
    private String targetAssetName;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "事件唯一id")
    private Long eventId;

    @ApiModelProperty(value = "处置措施")
    private String disposalMeasure;

    @ApiModelProperty(value = "发布机关")
    private String issuAuthority;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "预警级别")
    private String alertLevel;

    @ApiModelProperty(value = "预警起始时间")
    private Date alertStartTime;

    @ApiModelProperty(value = "可能影响范围")
    private String possibleScopeInfluence;

    @ApiModelProperty(value = "发展趋势")
    private String developmentTren;

    @ApiModelProperty(value = "相关企业")
    private String enterpriseName;


}
