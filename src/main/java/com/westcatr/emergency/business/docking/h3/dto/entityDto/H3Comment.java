package com.westcatr.emergency.business.docking.h3.dto.entityDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**h3备注信息表
 * @author lijiacheng
 * @Date 2021/4/21
 */
@Data
public class H3Comment {
    private  String ObjectID;
    @ApiModelProperty("业务对象模式编码")
    private  String BizObjectSchemaCode;
    @ApiModelProperty("审批人名称")
    private  String UserName;
    private Date CreatedTime;
    @ApiModelProperty("业务对象模式编码")
    private  String InstanceId;
    @ApiModelProperty("活动编码")
    private  String Activity;
    @ApiModelProperty("委托人")
    private  String Delegant;
    @ApiModelProperty("审批意见")
    private  String Text;
    @ApiModelProperty("审批人OU")
    private  String OUName;
    private  String SignatureId;
    @ApiModelProperty("数据项名称")
    private  String DataField;
    private  Date ModifiedTime;
    @ApiModelProperty("委托人")
    private  String UserID;
    @ApiModelProperty("工作任务ID")
    private  String WorkItemId;
    private  String to_users;
    @ApiModelProperty("业务对象ID")
    private  String BizObjectId;
    @ApiModelProperty("流程TokenID")
    private  Long TokenId;
    @ApiModelProperty("是否同意")
    private  Integer Approval;
    @ApiModelProperty("委托人姓名")
    private  String DelegantName;
    private  String AttachMentIds;

}
