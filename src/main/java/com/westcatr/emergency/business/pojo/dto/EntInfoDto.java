package com.westcatr.emergency.business.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lijiacheng
 * @Date 2021/4/25
 */
@TableName("bus_ent_info")
@Data
public class EntInfoDto {

    @ApiModelProperty(value = "企业名称")
    @NotNull(message = "企业名称不能为空")
    private String entName;

    @ApiModelProperty(value = "企业类别")

    private String entCategory;

    @ApiModelProperty(value = "行业类别")
    private String entIndustryCategory;

    @ApiModelProperty(value = "企业地址")
    @NotNull(message = "企业地址不能为空")
    private String entAddress;

    @ApiModelProperty(value = "邮政编码")
    private String postCode;

    @ApiModelProperty(value = "统一社会信用代码")
    @NotNull(message = "统一社会信用代码不能为空")
    private String socialCreditCode;

    @ApiModelProperty(value = "法人")
    @NotNull(message = "法人不能为空")
    private String legalPerson;

    @ApiModelProperty(value = "联系人姓名")
    @NotNull(message = "联系人姓名不能为空")
    private String contactName;

    @ApiModelProperty(value = "联系人电话")
    @NotNull(message = "联系人姓名不能为空")
    private String contactNumber;

    @ApiModelProperty(value = "联系人邮箱")
    @NotNull(message = "联系人姓名不能为空")
    private String contactEmail;


    @ApiModelProperty(value = "所属区县id")
    @TableField("country_id")
    private Long countryId;





}
