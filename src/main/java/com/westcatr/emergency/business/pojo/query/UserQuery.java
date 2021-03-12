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
 * 系统用户
 * </p>
 *
 * @author ls
 * @since 2021-03-10
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
}
