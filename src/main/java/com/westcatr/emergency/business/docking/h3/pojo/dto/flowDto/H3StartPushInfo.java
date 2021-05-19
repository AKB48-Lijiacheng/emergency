/*
 * @Author: your name
 * @Date: 2019-12-16 17:06:30
 * @LastEditTime: 2019-12-16 17:47:46
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /WitPark-Spring-Cloud/wp-common-util/src/main/java/com/westcatr/rd/wp/base/dto/H3/DocStartInfo.java
 */
package com.westcatr.emergency.business.docking.h3.pojo.dto.flowDto;

import java.util.List;

import com.westcatr.emergency.business.docking.h3.pojo.dto.DataItemParam;
import lombok.Data;

/**
 * StartInfo
 */
@Data
public class H3StartPushInfo {

    /**
     * 系统key
     */
    private String systemCode;

    /**
     * 系统secret
     */
    private String secret;

    /**
     * 流程编码,默认为发文流程
     */
    private String workflowCode="yjlc2";

    /**
     * 发起流程的用户id
     */
    private String userCode;

    /**
     * 是否结束第一个活动
     */
    private Boolean finishStart=true;

    /**
     * 数据项
     */
    private List<DataItemParam> paramValues;
}