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
 * 角色
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Role查询对象", description="角色查询对象")
public class RoleQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色id")
    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "角色名称")
    @QueryCondition
    private String roleName;

    @ApiModelProperty(value = "备注")
    @QueryCondition
    private String comments;

    @ApiModelProperty(value = "角色标识")
    @QueryCondition
    private String label;

    @ApiModelProperty(value = "创建时间")
    @QueryCondition
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @QueryCondition
    private Date updateTime;
}
