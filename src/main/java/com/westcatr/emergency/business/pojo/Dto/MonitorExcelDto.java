package com.westcatr.emergency.business.pojo.Dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.annotation.Select;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 监测信息表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_monitor_info")
public class MonitorExcelDto implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;
    @ExcelProperty({"目标资产名称"})
    @ApiModelProperty(value = "目标资产名称")
    private String targetAssetName;
    @ExcelProperty({"问题名称"})
    @ApiModelProperty(value = "问题名称")
    private String problemName;

    @ExcelProperty({"监测时间"})
    @ApiModelProperty(value = "监测时间")
    private Date monitorTime;

    @ExcelProperty({"问题类别"})
    @ApiModelProperty(value = "问题类别")
    private String problemType;

    @ExcelProperty({"问题描述"})
    @ApiModelProperty(value = "问题描述")
    private String problemDescribe;

    @ExcelProperty({"相关企业"})
    @ApiModelProperty(value = "相关企业")

    private String enterpriseName;
    @ExcelProperty({"处置措施"})
    @ApiModelProperty(value = "处置措施")

    private String disposalMeasure;
    @ExcelProperty({"是否上级指示"})
    @ApiModelProperty(value = "是否上级指示")
    private String tfMajorEvents;

    @ExcelProperty({"是否上级指示"})
    @ApiModelProperty(value = "是否上级指示")
    private String tfSuperiorInstructions;

    @ExcelIgnore
    private Date createTime;

    @ExcelIgnore
    private Date updateTime;

    @ExcelIgnore
    @ApiModelProperty(value = "事件唯一id")
    private Long eventId;

    @ExcelProperty({"该事件是否被处理过"})
    @ApiModelProperty(value = "该事件是否被处理过")
    private Boolean isHandle;

    @ExcelIgnore
    @ApiModelProperty(value = "产业分类表唯一id")
    private Integer industrialId;


}
