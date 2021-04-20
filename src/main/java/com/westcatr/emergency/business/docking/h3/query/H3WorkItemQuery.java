package com.westcatr.emergency.business.docking.h3.query;

import com.westcatr.rd.base.bmybatisplusbootstarter.dto.TimeDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lijiacheng
 * @Date 2021/4/17
 */
@Data
public class H3WorkItemQuery extends TimeDTO implements Serializable {
    private String  systemCode;//系统编码

    private String  secret;//系统秘钥

    @NotNull(message = "用户id不能为null")
    private String  userId;//用户ID
    @NotNull(message = "开始时间不能为null")
    private Date startTime;//开始时间
    @NotNull(message = "结束时间不能为null")
    private Date  endTime;//结束时间
    private Integer  startIndex;//开始索引 >0 (或-1）
    private Integer  endIndex;//结束索引 >0 (或-1）

    private String  workflowCode;//流程模板编码

    private String  instanceName;//流程实例名称
}
