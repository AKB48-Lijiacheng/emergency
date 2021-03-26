package com.westcatr.emergency.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="User对象", description="系统用户")
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "账号")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

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
    @TableField("create_time") private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "所属账户")
    @TableField("account_id")
    private Long accountId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
