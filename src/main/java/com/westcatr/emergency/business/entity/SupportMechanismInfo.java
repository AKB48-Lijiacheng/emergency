package com.westcatr.emergency.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 技术支撑机构
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_support_mechanism_info")
@ApiModel(value="SupportMechanismInfo对象", description="技术支撑机构")
public class SupportMechanismInfo extends Model<SupportMechanismInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "机构名称")
    @TableField("organization_name")
    private String organizationName;

    @ApiModelProperty(value = "安全产品或服务")
    @TableField("safe_product_service")
    private String safeProductService;

    @ApiModelProperty(value = "技术支撑领域")
    @TableField("technical_support_field")
    private String technicalSupportField;

    @ApiModelProperty(value = "企业介绍")
    @TableField("enterprise_info")
    private String enterpriseInfo;

    @ApiModelProperty(value = "资质")
    @TableField("qualification")
    private String qualification;

    @ApiModelProperty(value = "案例")
    @TableField("example_info")
    private String exampleInfo;

    @ApiModelProperty(value = "联系方式")
    @TableField("contact_info")
    private String contactInfo;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
