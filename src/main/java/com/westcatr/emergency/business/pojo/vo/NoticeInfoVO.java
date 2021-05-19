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
 * 系统通知内容表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_notice_info")
@ApiModel(value="NoticeInfoVO对象", description="系统通知内容表VO对象")
public class NoticeInfoVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String context;

    private Date createTime;

    private Date updateTime;


}
