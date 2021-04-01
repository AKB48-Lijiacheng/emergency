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
 * 企业信息表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EntInfo查询对象", description="企业信息表查询对象")
public class EntInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "企业名称")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String entName;

    @ApiModelProperty(value = "企业类别")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String entCategory;

    @ApiModelProperty(value = "行业类别")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String entIndustryCategory;

    @ApiModelProperty(value = "企业地址")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String entAddress;

    @ApiModelProperty(value = "邮政编码")
    @QueryCondition
    private String postCode;

    @ApiModelProperty(value = "法人")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String legalPerson;

    @ApiModelProperty(value = "注册城市")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String registerCity;

    @ApiModelProperty(value = "业务省份")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String businesProvince;

    @ApiModelProperty(value = "联系人姓名")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String contactName;

    @ApiModelProperty(value = "联系人电话")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String contactNumber;

    @ApiModelProperty(value = "联系人邮箱")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String contactEmail;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;




}
