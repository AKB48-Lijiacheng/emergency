package com.westcatr.emergency.business.pojo.vo;

import com.westcatr.emergency.business.entity.MonitorInfo;
import lombok.Data;

import java.util.List;

/**
 * @author lijiacheng
 * @Date 2021/4/22
 */
@Data
public class MonitorSimilarDto {
    MonitorInfo monitor;
    List<MonitorInfo> similiars;
    List<MonitorInfo> notSimiliars;
    Integer similiarsTotal;
    Integer notSimiliarsTotal;




}
