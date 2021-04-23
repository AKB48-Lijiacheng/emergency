package com.westcatr.emergency.business.pojo.query;

import com.westcatr.rd.base.bmybatisplusbootstarter.dto.TimeDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.QueryCondition;
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
@ApiModel(value="User查询对象", description="系统用户查询对象")
public class UserQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "账号")
    @QueryCondition
    private String username;

    @ApiModelProperty(value = "密码")
    @QueryCondition
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    @QueryCondition
    private String phone;

    @ApiModelProperty(value = "真实姓名")
    @QueryCondition
    private String fullName;

    @ApiModelProperty(value = "部门id")
    @QueryCondition
    private Integer departmentId;

    @ApiModelProperty(value = "状态，1正常，0禁用")
    @QueryCondition
    private Integer enable;

    @ApiModelProperty(value = "注册时间")
    @QueryCondition
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @QueryCondition
    private Date updateTime;

    @ApiModelProperty(value = "所属账户")
    @QueryCondition
    private Long accountId;

    @ApiModelProperty(value = "所属区县id")
    @QueryCondition
    private Long cityId;

    @ApiModelProperty(value = "所属市级id")
    @QueryCondition
    private Long countryId;

    @ApiModelProperty(value = "所属企业id")
    @QueryCondition
    private Long entId;

    @ApiModelProperty(value = "大平台用户ID")
    @QueryCondition
    private String ssoId;

    @ApiModelProperty(value = "大平台头像")
    @QueryCondition
    private String ssoUserIcon;

    @ApiModelProperty(value = "大平台邮箱")
    @QueryCondition
    private String ssoEmail;

    @ApiModelProperty(value = "大平台用户类型")
    @QueryCondition
    private String ssoUserType;

    @ApiModelProperty(value = "大平台姓名")
    @QueryCondition
    private String ssoName;

    @ApiModelProperty(value = "大平台账户")
    @QueryCondition
    private String ssoAccount;

    @ApiModelProperty(value = "大平台电话")
    @QueryCondition
    private String ssoMobile;

    @ApiModelProperty(value = "H3用户表id")
    @QueryCondition
    private String h3UserId;
}
