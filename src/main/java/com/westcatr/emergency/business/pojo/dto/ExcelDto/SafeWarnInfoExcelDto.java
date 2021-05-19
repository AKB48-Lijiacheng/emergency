package com.westcatr.emergency.business.pojo.dto.ExcelDto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 安全预警表
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@TableName("bus_safe_warn_info")
@ContentRowHeight(10)
@ColumnWidth(15)
public class SafeWarnInfoExcelDto extends Model<SafeWarnInfoExcelDto> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ExcelProperty({"目标资产名称"})
    @ApiModelProperty(value = "目标资产名称")
    private String targetAssetName;

    @ExcelProperty({"创建时间"})
    private Date createTime;

    @ExcelProperty({"更新时间"})
    private Date updateTime;


    @ExcelProperty({"处置措施"})
    @ApiModelProperty(value = "处置措施")
    private String disposalMeasure;

    @ExcelProperty({"发布机关"})
    @ApiModelProperty(value = "发布机关")
    private String issuAuthority;

    @ExcelProperty({"类型"})
    @ApiModelProperty(value = "类型")
    private String type;

    @ExcelProperty({"预警级别"})
    @ApiModelProperty(value = "预警级别")
    private String alertLevel;

    @ExcelProperty({"预警起始时间"})
    @ApiModelProperty(value = "预警起始时间")
    private Date alertStartTime;

    @ExcelProperty({"可能影响范围"})
    @ApiModelProperty(value = "可能影响范围")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String possibleScopeInfluence;

    @ExcelProperty({"发展趋势"})
    @ApiModelProperty(value = "发展趋势")
    private String developmentTren;

    @ExcelIgnore
    @ExcelProperty({"事件唯一id"})
    @ApiModelProperty(value = "事件唯一id")
    private Long eventId;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
