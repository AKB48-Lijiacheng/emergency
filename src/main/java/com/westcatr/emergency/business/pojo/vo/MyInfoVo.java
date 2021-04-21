package com.westcatr.emergency.business.pojo.vo;

import com.westcatr.rd.base.authority.authority.domain.UserInfoVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lijiacheng
 * @Date 2021/4/21
 */
@Data
public class MyInfoVo extends UserInfoVO {
    @ApiModelProperty(value = "H3用户表id")
    private String h3UserId;
    @ApiModelProperty(value = "大平台头像")
    private String ssoUserIcon;
    @ApiModelProperty(value = "大平台用户ID")
    private String ssoId;
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
    @ApiModelProperty(value = "所属区县id")
    private Long cityId;
    @ApiModelProperty(value = "所属市级id")
    private Long countryId;
    @ApiModelProperty(value = "所属企业id")
    private Long entId;
}
