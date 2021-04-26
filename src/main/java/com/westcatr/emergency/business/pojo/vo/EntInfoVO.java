package com.westcatr.emergency.business.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.annotation.Select;
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
@ApiModel(value="EntInfoVO对象", description="企业信息表VO对象")
public class EntInfoVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "企业名称")
    private String entName;

    @ApiModelProperty(value = "企业类别")
    private String entCategory;

    @ApiModelProperty(value = "行业类别")
    private String entIndustryCategory;

    @ApiModelProperty(value = "企业地址")
    private String entAddress;

    @ApiModelProperty(value = "邮政编码")
    private String postCode;

    @ApiModelProperty(value = "统一社会信用代码")
    private String socialCreditCode;

    @ApiModelProperty(value = "法人")
    private String legalPerson;

    @ApiModelProperty(value = "联系人姓名")
    private String contactName;

    @ApiModelProperty(value = "联系人电话")
    private String contactNumber;

    @ApiModelProperty(value = "联系人邮箱")
    private String contactEmail;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "所属区县id")
    private Long countryId;

    @ApiModelProperty(value = "所属区县")
    private String belongCountry;


}
