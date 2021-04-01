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
 * 账户信息表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AccountInfo查询对象", description="账户信息表查询对象")
public class AccountInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "名称")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String name;

    @ApiModelProperty(value = "显示名称")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String displayName;

    @ApiModelProperty(value = "邮箱")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String email;

    @ApiModelProperty(value = "电话")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String phone;

    @ApiModelProperty(value = "组织信息")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String organization;

    @ApiModelProperty(value = "权限信息")
    @QueryCondition(condition = QueryCondition.Condition.LIKE)
    private String role;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;

    @ApiModelProperty(value = "所属地区id")
    @QueryCondition
    private Long areaId;
}
