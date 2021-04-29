package com.westcatr.emergency.business.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lijiacheng
 * @Date 2021/4/29
 */
@Data
public class ResetPasswordDto {
    @NotNull(message = "用户名不为null")
    String username;
    @NotNull(message="用户名不为null")
    String password;
    @NotNull(message="不为null")
    String email;

    @NotNull(message="邮箱验证码不能为空")
    String activCode;
}
