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
 * 路由权限
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
@ApiModel(value="PermissionVO对象", description="路由权限VO对象")
public class PermissionVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "授权标识")
    private String permissionCode;

    @ApiModelProperty(value = "名称")
    private String permissionName;

    private Long pid;

    @ApiModelProperty(value = "排序号")
    private Integer sort;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "请求方式")
    private String requestWay;

    @ApiModelProperty(value = "1路由，2操作")
    private Integer type;

    @ApiModelProperty(value = "组件地址")
    private String componentUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "状态，0不需要验证，1需要登录，2需要验证")
    private Integer status;

    private Integer updateLock;


}
