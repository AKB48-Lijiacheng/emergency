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
 * 企业信息表
 * </p>
 *
 * @author ls
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_ent_info")
@ApiModel(value="EntInfo对象", description="企业信息表")
public class EntInfo extends Model<EntInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "企业名称")
    @TableField("ent_name")
    private String entName;

    @ApiModelProperty(value = "企业类别")
    @TableField("ent_category")
    private String entCategory;

    @ApiModelProperty(value = "行业类别")
    @TableField("ent_industry_category")
    private String entIndustryCategory;

    @ApiModelProperty(value = "企业地址")
    @TableField("ent_address")
    private String entAddress;

    @ApiModelProperty(value = "邮政编码")
    @TableField("post_code")
    private String postCode;

    @ApiModelProperty(value = "统一社会信用代码")
    @TableField("social_credit_code")
    private String socialCreditCode;

    @ApiModelProperty(value = "法人")
    @TableField("legal_person")
    private String legalPerson;

    @ApiModelProperty(value = "联系人姓名")
    @TableField("contact_name")
    private String contactName;

    @ApiModelProperty(value = "联系人电话")
    @TableField("contact_number")
    private String contactNumber;

    @ApiModelProperty(value = "联系人邮箱")
    @TableField("contact_email")
    private String contactEmail;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "所属区县id")
    @TableField("country_id")
    private Long countryId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
