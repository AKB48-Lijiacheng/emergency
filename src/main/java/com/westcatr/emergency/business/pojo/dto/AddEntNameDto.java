package com.westcatr.emergency.business.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lijiacheng
 * @Date 2021/4/28
 */
@Data
public class AddEntNameDto {
    @NotNull(message = "监测信息id不能为空")
    private Long MonitInfoId;
    @NotNull(message = "相关企业id不能为空")
    private Long entInfoId;
}
