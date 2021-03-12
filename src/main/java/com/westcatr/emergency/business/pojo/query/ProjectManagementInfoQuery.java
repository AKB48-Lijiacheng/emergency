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
 * 项目管理表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProjectManagementInfo查询对象", description="项目管理表查询对象")
public class ProjectManagementInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "名称")
    @QueryCondition
    private String name;

    @ApiModelProperty(value = "项目经理")
    @QueryCondition
    private String projectManager;

    @ApiModelProperty(value = "参与者")
    @QueryCondition
    private String participant;

    @ApiModelProperty(value = "开始时间")
    @QueryCondition
    private Date startInfoTime;

    @ApiModelProperty(value = "结束时间")
    @QueryCondition
    private Date endInfoTime;

    @ApiModelProperty(value = "评级")
    @QueryCondition
    private Double grade;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;
}
