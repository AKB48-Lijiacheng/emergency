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
 * 路由权限
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Permission查询对象", description="路由权限查询对象")
public class PermissionQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "授权标识")
    @QueryCondition
    private String permissionCode;

    @ApiModelProperty(value = "名称")
    @QueryCondition
    private String permissionName;

    @QueryCondition
    private Long pid;

    @ApiModelProperty(value = "排序号")
    @QueryCondition
    private Integer sort;

    @ApiModelProperty(value = "url")
    @QueryCondition
    private String url;

    @ApiModelProperty(value = "请求方式")
    @QueryCondition
    private String requestWay;

    @ApiModelProperty(value = "1路由，2操作")
    @QueryCondition
    private Integer type;

    @ApiModelProperty(value = "组件地址")
    @QueryCondition
    private String componentUrl;

    @ApiModelProperty(value = "创建时间")
    @QueryCondition
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @QueryCondition
    private Date updateTime;

    @ApiModelProperty(value = "状态，0不需要验证，1需要登录，2需要验证")
    @QueryCondition
    private Integer status;

    @QueryCondition
    private Integer updateLock;
}
