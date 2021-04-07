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
 * 系统用户
 * </p>
 *
 * @author ls
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="UserVO对象", description="系统用户VO对象")
public class UserVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "真实姓名")
    private String fullName;

    @ApiModelProperty(value = "部门id")
    private Integer departmentId;

    @ApiModelProperty(value = "状态，1正常，0禁用")
    private Integer enable;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "所属账户")
    private Long accountId;

    @ApiModelProperty(value = "所属区县id")
    private Long cityId;

    @ApiModelProperty(value = "所属市级id")
    private Long countryId;

    @ApiModelProperty(value = "所属企业id")
    private Long entId;

    @ApiModelProperty(value = "大平台用户ID")
    private String ssoId;

    @ApiModelProperty(value = "大平台头像")
    private String ssoUserIcon;

    @ApiModelProperty(value = "大平台邮箱")
    private String ssoEmail;

    @ApiModelProperty(value = "大平台用户类型")
    private String ssoUserType;

    @ApiModelProperty(value = "大平台姓名")
    private String ssoName;

    @ApiModelProperty(value = "大平台账户")
    private String ssoAccount;

    @ApiModelProperty(value = "大平台电话")
    private String ssoMobile;


}
