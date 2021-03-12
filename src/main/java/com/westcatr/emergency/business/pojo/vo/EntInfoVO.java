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
 * 企业信息表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_ent_info")
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

    @ApiModelProperty(value = "法人")
    private String legalPerson;

    @ApiModelProperty(value = "注册城市")
    private String registerCity;

    @ApiModelProperty(value = "业务省份")
    private String businesProvince;

    @ApiModelProperty(value = "联系人姓名")
    private String contactName;

    @ApiModelProperty(value = "联系人电话")
    private String contactNumber;

    @ApiModelProperty(value = "联系人邮箱")
    private String contactEmail;

    private Date createTime;

    private Date updateTime;


}
