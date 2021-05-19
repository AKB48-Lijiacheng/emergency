package com.westcatr.emergency.business.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author lijiacheng
 * @Date 2021/4/30
 */
public class CommonUtil {


    public static List<Map<Object,Object>> getYearAgoMonthMap(){
        List<Map<Object,Object>> timeList = new LinkedList<>();
        for (int i = 12; i > 0; i--) {
            DateTime dateTime = DateUtil.offsetMonth(DateUtil.date(), -i);
            String format = DateUtil.format(dateTime, "yyyy-MM");
            Map<Object, Object> map = new HashMap<>();
            map.put("time",format);
            map.put("count",null);
            timeList.add(map);
        }
        return  timeList;
    }
}
