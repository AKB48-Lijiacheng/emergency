package com.westcatr.emergency.business.docking.h3.dto.flowDto;

import com.westcatr.emergency.business.docking.h3.dto.DataItemParam;
import com.westcatr.emergency.business.docking.h3.dto.formDto.YjFormDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 开启流程的参数实体
 * @author lijiacheng
 * @since  2021/4/13
 **/
@Data
public class H3FlowStartDto {
    @NotNull(message = "请传入开启流程的数据id")
    private String monitorNextId;

    @NotNull(message = "启动流程的用户名不能为null")
    private String userCode;
    @NotNull(message = "结束第一个活动不能为null")
    private boolean finishStart;
//应急前端参数接收
    private YjFormDto formDto;
    private List<DataItemParam> paramValues;


}