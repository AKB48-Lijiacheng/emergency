/*
 * @Author: your name
 * @Date: 2019-11-11 11:58:13
 * @LastEditTime: 2019-12-12 14:54:24
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /WitPark-Spring-Cloud/wp-common-util/src/main/java/com/westcatr/rd/wp/base/dto/H3/H3PushInfoDTO.java
 */
package com.westcatr.emergency.business.docking.h3.pojo.dto.flowDto;

import java.util.List;

import com.westcatr.emergency.business.docking.h3.pojo.dto.DataItemParam;
import lombok.Data;

/**
 * H3PushInfoDTO
 */
@Data
public class H3PushInfoDTO {

    private String systemCode;
    private String secret;
    private String userId;
    private String bizObjectSchemaCode;
    private String bizObjectId;
    // private String commentText="同意";
    private List<DataItemParam> keyValues;
}