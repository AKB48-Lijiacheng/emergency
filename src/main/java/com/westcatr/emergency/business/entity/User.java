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
 * 系统用户
 * </p>
 *
 * @author ls
 * @since 2021-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "User对象", description = "系统用户")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "账号")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户类型")
    @TableField("user_type")
    private String userType;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "真实姓名")
    @TableField("full_name")
    private String fullName;

    @ApiModelProperty(value = "部门id")
    @TableField("department_id")
    private Integer departmentId;

    @ApiModelProperty(value = "状态，1正常，0禁用")
    @TableField("enable")
    private Integer enable;

    @ApiModelProperty(value = "注册时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "所属账户")
    @TableField("account_id")
    private Long accountId;

    @ApiModelProperty(value = "所属区县id")
    @TableField("city_id")
    private Long cityId;

    @ApiModelProperty(value = "所属市级id")
    @TableField("country_id")
    private Long countryId;

    @ApiModelProperty(value = "所属企业id")
    @TableField("ent_id")
    private Long entId;

    @ApiModelProperty(value = "大平台用户ID")
    @TableField("sso_id")
    private String ssoId;

    @ApiModelProperty(value = "大平台头像")
    @TableField("sso_user_icon")
    private String ssoUserIcon;

    @ApiModelProperty(value = "大平台邮箱")
    @TableField("sso_email")
    private String ssoEmail;

    @ApiModelProperty(value = "大平台用户类型")
    @TableField("sso_user_type")
    private String ssoUserType;

    @ApiModelProperty(value = "大平台姓名")
    @TableField("sso_name")
    private String ssoName;

    @ApiModelProperty(value = "大平台账户")
    @TableField("sso_account")
    private String ssoAccount;

    @ApiModelProperty(value = "大平台电话")
    @TableField("sso_mobile")
    private String ssoMobile;

    @ApiModelProperty(value = "H3用户表id")
    @TableField("h3_user_id")
    private String h3UserId;

    @ApiModelProperty(value = "用户激活验证Code")
    @TableField("activity_code")
    private String activityCode;
    @ApiModelProperty(value = "组织架构id")
    @TableField("org_construct_id")
    private Integer orgConstructId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
