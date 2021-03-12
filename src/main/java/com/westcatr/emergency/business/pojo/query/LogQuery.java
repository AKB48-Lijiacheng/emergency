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
 * 操作日志
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Log查询对象", description="操作日志查询对象")
public class LogQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "用户id")
    @QueryCondition
    private Long userId;

    @ApiModelProperty(value = "用户名")
    @QueryCondition
    private String username;

    @ApiModelProperty(value = "用户姓名")
    @QueryCondition
    private String fullName;

    @ApiModelProperty(value = "说明")
    @QueryCondition
    private String description;

    @ApiModelProperty(value = "错误详情")
    @QueryCondition
    private String exceptionDetail;

    @ApiModelProperty(value = "日志类型")
    @QueryCondition
    private String logType;

    @ApiModelProperty(value = "操作方法")
    @QueryCondition
    private String method;

    @ApiModelProperty(value = "参数")
    @QueryCondition
    private String params;

    @ApiModelProperty(value = "ip")
    @QueryCondition
    private String requestIp;

    @ApiModelProperty(value = "address")
    @QueryCondition
    private String address;

    @ApiModelProperty(value = "用时")
    @QueryCondition
    private Long time;

    @ApiModelProperty(value = "日志级别（1一般，2敏感，3危险）")
    @QueryCondition
    private Integer logLevel;

    @ApiModelProperty(value = "模块名称")
    @QueryCondition
    private String module;

    @ApiModelProperty(value = "创建时间")
    @QueryCondition
    private Date createTime;
}
