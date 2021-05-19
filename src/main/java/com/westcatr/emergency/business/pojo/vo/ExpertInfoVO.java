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
 * 专家库信息表
 * </p>
 *
 * @author ls
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_expert_info")
@ApiModel(value="ExpertInfoVO对象", description="专家库信息表VO对象")
public class ExpertInfoVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "就职单位")
    private String workCompany;

    @ApiModelProperty(value = "职务")
    private String post;

    @ApiModelProperty(value = "职称")
    private String jobTitle;

    @ApiModelProperty(value = "联系电话")
    private String contactNumber;

    private Date createTime;

    private Date updateTime;


}
