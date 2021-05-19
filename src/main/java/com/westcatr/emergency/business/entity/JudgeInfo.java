package com.westcatr.emergency.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 研判信息表（1-4级，1：特别重大，红色；2：重大，橙色；3：较大，黄色；4：一般，蓝色;0：灰色 无 ）
 * </p>
 *
 * @author ls
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_judge_info")
@ApiModel(value="JudgeInfo对象", description="研判信息表（1-4级，1：特别重大，红色；2：重大，橙色；3：较大，黄色；4：一般，蓝色;0：灰色 无 ）")
public class JudgeInfo extends Model<JudgeInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty(value = "研判依据")
    @TableField("judge_based")
    private String judgeBased;

    @ApiModelProperty(value = "研判等级(1-4级=1:特别重大,红色;2:重大,橙色;3:较大,黄色;4:一般,蓝色;0:无,灰色;)")
    @TableField("judge_degree")
    private Integer judgeDegree;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
