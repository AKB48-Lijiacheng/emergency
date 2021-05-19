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
 * 技术支撑机构
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SupportMechanismInfo查询对象", description="技术支撑机构查询对象")
public class SupportMechanismInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "机构名称")
    @QueryCondition
    private String organizationName;

    @ApiModelProperty(value = "安全产品或服务")
    @QueryCondition
    private String safeProductService;

    @ApiModelProperty(value = "技术支撑领域")
    @QueryCondition
    private String technicalSupportField;

    @ApiModelProperty(value = "企业介绍")
    @QueryCondition
    private String enterpriseInfo;

    @ApiModelProperty(value = "资质")
    @QueryCondition
    private String qualification;

    @ApiModelProperty(value = "案例")
    @QueryCondition
    private String exampleInfo;

    @ApiModelProperty(value = "联系方式")
    @QueryCondition
    private String contactInfo;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;
}
