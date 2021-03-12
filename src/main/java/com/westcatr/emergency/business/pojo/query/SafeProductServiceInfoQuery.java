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
 * 安全产品及服务表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SafeProductServiceInfo查询对象", description="安全产品及服务表查询对象")
public class SafeProductServiceInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "名称")
    @QueryCondition
    private String name;

    @ApiModelProperty(value = "所属机构")
    @QueryCondition
    private String affiliatInstitution;

    @ApiModelProperty(value = "产品概述")
    @QueryCondition
    private String productOverview;

    @ApiModelProperty(value = "产品模块")
    @QueryCondition
    private String functionModule;

    @ApiModelProperty(value = "特色特点")
    @QueryCondition
    private String characteristics;

    @ApiModelProperty(value = "备注")
    @QueryCondition
    private String remark;

    @ApiModelProperty(value = "评级")
    @QueryCondition
    private Double grade;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;
}
