package com.westcatr.emergency.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value="SafeProductServiceInfo对象", description="安全产品及服务表")
public class SafeProductServiceInfo extends Model<SafeProductServiceInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "所属机构")
    @TableField("affiliat_institution")
    private String affiliatInstitution;

    @ApiModelProperty(value = "产品概述")
    @TableField("product_overview")
    private String productOverview;

    @ApiModelProperty(value = "产品模块")
    @TableField("function_module")
    private String functionModule;

    @ApiModelProperty(value = "特色特点")
    @TableField("characteristics")
    private String characteristics;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "评级")
    @TableField("grade")
    private Double grade;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
