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
 * 安全产品及服务表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_safe_product_service_info")
@ApiModel(value="SafeProductServiceInfoVO对象", description="安全产品及服务表VO对象")
public class SafeProductServiceInfoVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "所属机构")
    private String affiliatInstitution;

    @ApiModelProperty(value = "产品概述")
    private String productOverview;

    @ApiModelProperty(value = "产品模块")
    private String functionModule;

    @ApiModelProperty(value = "特色特点")
    private String characteristics;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "评级")
    private Double grade;

    private Date createTime;

    private Date updateTime;


}
