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
 * 
 * </p>
 *
 * @author ls
 * @since 2021-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrgConstruct查询对象", description="查询对象")
public class OrgConstructQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Integer id;

    @ApiModelProperty(value = "组织名称")
    @QueryCondition
    private String orgName;

    @ApiModelProperty(value = "备注信息")
    @QueryCondition
    private String remake;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;
}
