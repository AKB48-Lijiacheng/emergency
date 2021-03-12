package com.westcatr.emergency.business.pojo.query;

import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.QueryCondition;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.TimeDTO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 专家库信息表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ExpertInfo查询对象", description="专家库信息表查询对象")
public class ExpertInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "姓名")
    @QueryCondition
    private String name;

    @ApiModelProperty(value = "性别")
    @QueryCondition
    private String sex;

    @ApiModelProperty(value = "身份证号")
    @QueryCondition
    private String idCard;

    @ApiModelProperty(value = "就职单位")
    @QueryCondition
    private String workCompany;

    @ApiModelProperty(value = "职务")
    @QueryCondition
    private String post;

    @ApiModelProperty(value = "联系电话")
    @QueryCondition
    private String contactNumber;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;
}
