package com.westcatr.emergency.business.pojo.Dto.ParamDto;

import com.westcatr.emergency.business.pojo.Dto.ExcelDto.MonitorExcelDto;
import com.westcatr.emergency.business.pojo.Dto.ExcelDto.SafeWarnInfoExcelDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value="文档导出参数对象", description="文档导出对象")
public class DocDto implements Serializable {
    @NotNull(message = "请传入文件类型")
    private String type; //导出文档类型

    private MonitorExcelDto monitorExcelDto;

    private SafeWarnInfoExcelDto safeWarnInfoExcelDto;

}
