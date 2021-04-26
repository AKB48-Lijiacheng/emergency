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
 * 
 * </p>
 *
 * @author ls
 * @since 2021-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_org_construct")
@ApiModel(value="OrgConstructVO对象", description="VO对象")
public class OrgConstructVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Integer id;

    @ApiModelProperty(value = "组织名称")
    private String orgName;

    @ApiModelProperty(value = "备注信息")
    private String remake;

    private Date createTime;

    private Date updateTime;


}
