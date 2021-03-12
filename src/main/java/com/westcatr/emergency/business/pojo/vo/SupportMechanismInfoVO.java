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
 * 技术支撑机构
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_support_mechanism_info")
@ApiModel(value="SupportMechanismInfoVO对象", description="技术支撑机构VO对象")
public class SupportMechanismInfoVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "机构名称")
    private String organizationName;

    @ApiModelProperty(value = "安全产品或服务")
    private String safeProductService;

    @ApiModelProperty(value = "技术支撑领域")
    private String technicalSupportField;

    @ApiModelProperty(value = "企业介绍")
    private String enterpriseInfo;

    @ApiModelProperty(value = "资质")
    private String qualification;

    @ApiModelProperty(value = "案例")
    private String exampleInfo;

    @ApiModelProperty(value = "联系方式")
    private String contactInfo;

    private Date createTime;

    private Date updateTime;


}
