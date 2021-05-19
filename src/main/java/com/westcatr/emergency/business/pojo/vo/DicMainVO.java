package com.westcatr.emergency.business.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * 字典主信息
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dic_main")
@ApiModel(value="DicMainVO对象", description="字典主信息VO对象")
public class DicMainVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "字典名称")
    private String dicCode;

    @ApiModelProperty(value = "说明")
    private String dicExplain;

    @ApiModelProperty(value = "字典类型，0系统字典，无法修改；1用户字典")
    private Integer dicType;


}
