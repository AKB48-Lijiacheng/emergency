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
 * 登录日志
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LoginLog查询对象", description="登录日志查询对象")
public class LoginLogQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @QueryCondition
    private Long userId;

    @ApiModelProperty(value = "用户名")
    @QueryCondition
    private String username;

    @ApiModelProperty(value = "登录时间")
    @QueryCondition
    private Date loginTime;

    @ApiModelProperty(value = "登录地址")
    @QueryCondition
    private String address;

    @ApiModelProperty(value = "登录ip")
    @QueryCondition
    private String ip;

    @ApiModelProperty(value = "操作系统")
    @QueryCondition
    private String systemName;

    @ApiModelProperty(value = "登录浏览器")
    @QueryCondition
    private String browser;

    @ApiModelProperty(value = "创建时间")
    @QueryCondition
    private Date createTime;

    @ApiModelProperty(value = "客户端编号")
    @QueryCondition
    private Integer clientCode;
}
