package com.westcatr.emergency.business.docking.h3.pojo.dto.entityDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * h3的用户信息
 * @author lijiacheng
 * @Date 2021/4/14
 */
@Data
public class H3User {
    private String ObjectID;
    private String Name;
    /**
     * 上级Id,用户通过这个Id关联上级组织，应急平台区县级ObjectId  =USER。ParentID
     **/
    @ApiModelProperty("上级id")
    private String ParentID;
    @ApiModelProperty("用户名")
    private String Code;
    private String Appellation;
    private String Email;
    private String OfficePhone;
    private String Mobile;
}
