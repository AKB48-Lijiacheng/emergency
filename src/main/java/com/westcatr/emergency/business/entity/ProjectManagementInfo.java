package com.westcatr.emergency.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@TableName("bus_project_management_info")
@ApiModel(value="ProjectManagementInfo对象", description="项目管理表")
public class ProjectManagementInfo extends Model<ProjectManagementInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "项目经理")
    @TableField("project_manager")
    private String projectManager;

    @ApiModelProperty(value = "参与者")
    @TableField("participant")
    private String participant;

    @ApiModelProperty(value = "开始时间")
    @TableField("start_info_time")
    private Date startInfoTime;

    @ApiModelProperty(value = "结束时间")
    @TableField("end_info_time")
    private Date endInfoTime;

    @ApiModelProperty(value = "评级")
    @TableField("grade")
    private Double grade;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
