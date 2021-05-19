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
 * 操作日志
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_log")
@ApiModel(value="LogVO对象", description="操作日志VO对象")
public class LogVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户姓名")
    private String fullName;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "错误详情")
    private String exceptionDetail;

    @ApiModelProperty(value = "日志类型")
    private String logType;

    @ApiModelProperty(value = "操作方法")
    private String method;

    @ApiModelProperty(value = "参数")
    private String params;

    @ApiModelProperty(value = "ip")
    private String requestIp;

    @ApiModelProperty(value = "address")
    private String address;

    @ApiModelProperty(value = "用时")
    private Long time;

    @ApiModelProperty(value = "日志级别（1一般，2敏感，3危险）")
    private Integer logLevel;

    @ApiModelProperty(value = "模块名称")
    private String module;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
