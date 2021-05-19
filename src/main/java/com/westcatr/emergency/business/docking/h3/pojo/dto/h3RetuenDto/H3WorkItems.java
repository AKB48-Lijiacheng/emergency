package com.westcatr.emergency.business.docking.h3.pojo.dto.h3RetuenDto;

import com.westcatr.emergency.business.docking.h3.pojo.dto.BaseDTO;
import com.westcatr.emergency.business.docking.h3.pojo.dto.H3TimeDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
 * 获取流程待办任务的实体
 * @author lijiacheng
 * @since  2021/4/17
 **/
@Data
public class H3WorkItems extends BaseDTO {
    @ApiModelProperty("流程实例创建时间")
    private Date instanceCreatedTime;
    @ApiModelProperty("流程实例状态")
    private String instanceState;
    @ApiModelProperty("流程实例流水号")
    private String instanceSequenceNo;
    @ApiModelProperty("流程优先级")
    private String Priority;
    @ApiModelProperty("状态")
    private int State;
    @ApiModelProperty("显示名称")
    private String DisplayName;
    @ApiModelProperty("流程实例id")
    private String InstanceId;
    @ApiModelProperty("接收时间")
    private Date ReceiveTime;
    @ApiModelProperty("任务摘要")
    private String ItemSummary;
    @ApiModelProperty("是否有协办过该工作项的意见")
    private boolean Assisted;
    @ApiModelProperty("是否催办")
    private boolean Urged;
    @ApiModelProperty("是否征询过该工作项的意见")
    private boolean Consulted;
    @ApiModelProperty("")
    private H3TimeDTO StayTime;
    @ApiModelProperty("完成时间")
    private Date FinishTime;
    @ApiModelProperty("发起人姓名")
    private String OriginatorName;
    @ApiModelProperty("")
    private String ItemCount;
    @ApiModelProperty("参与者姓名")
    private String ParticipantName;
    @ApiModelProperty("参与者")
    private String Participant;
    @ApiModelProperty("流程模板编码")
    private String WorkflowCode;
    @ApiModelProperty("活动编码")
    private String ActivityCode;
    @ApiModelProperty("流程实例名称")
    private String InstanceName;
    @ApiModelProperty("发起人")
    private String Originator;
    @ApiModelProperty("发起人组织名称")
    private String OriginatorOUName;
    @ApiModelProperty("")
    private String CirculateCreatorName;
    @ApiModelProperty("")
    private boolean DisplayWorkflowCode;
    @ApiModelProperty("")
    private String CirculateCreator;
    @ApiModelProperty("计划完成时间")
    private Date PlanFinishTime;
    @ApiModelProperty("接收时间")
    private boolean ConsultantFinished;
    @ApiModelProperty("征询意见工作项是否完成")
    private boolean AssistantFinished;
    @ApiModelProperty("显示名称")
    private String WorkflowName;
    @ApiModelProperty("待办流程id")
    private String ObjectID;
    @ApiModelProperty("审批人")
    private String Approver;
    @ApiModelProperty("")
    private String ApproverLink;
    @ApiModelProperty("创建时间")
    private Date CreatedTime;
    @ApiModelProperty("完成时间")
    private Date FinishedTime;
    @ApiModelProperty("")
    private Date CanceledTime;


}
