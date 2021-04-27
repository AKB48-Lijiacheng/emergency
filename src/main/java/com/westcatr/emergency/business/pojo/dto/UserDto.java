package com.westcatr.emergency.business.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户注册Dto
 * @author lijiacheng
 * @Date 2021/4/25
 */
@Data
public class UserDto {
    @ApiModelProperty(value = "账号")
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @NotNull(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "真实姓名")
    @NotNull(message = "真实姓名不能为空")
    private String fullName;

    @ApiModelProperty(value = "邮箱验证码")
    @NotNull(message = "邮箱验证码不能为空")
    private String emailActivCode;


    @ApiModelProperty(value = "验证码")
    @NotNull(message = "验证码不能为空")
    private String code;

    @ApiModelProperty(value = "企业信息")
    @NotNull(message = "企业信息不能为空")
     private EntInfoDto entInfo;


}
