package com.westcatr.emergency.business.docking.h3.dto.h3RetuenDto;

import com.westcatr.emergency.business.docking.h3.dto.BaseDTO;
import com.westcatr.emergency.business.docking.h3.dto.H3TimeDTO;
import lombok.Data;

import java.util.Date;
/**
 * 获取流程待办任务的实体
 * @author lijiacheng
 * @since  2021/4/17
 **/
@Data
public class H3WorkItemsReturn extends BaseDTO {
    private Date instanceCreatedTime;
    private String instanceState;
    private String instanceSequenceNo;
    private String Priority;
    private int State;
    private String DisplayName;
    private String InstanceId;
    private Date ReceiveTime;
    private String ItemSummary;
    private boolean Assisted;
    private boolean Urged;
    private boolean Consulted;
    private H3TimeDTO StayTime;
    private Date FinishTime;
    private String OriginatorName;
    private String ItemCount;
    private String ParticipantName;
    private String Participant;
    private String WorkflowCode;
    private String ActivityCode;
    private String InstanceName;
    private String Originator;
    private String OriginatorOUName;
    private String CirculateCreatorName;
    private boolean DisplayWorkflowCode;
    private String CirculateCreator;
    private Date PlanFinishTime;
    private boolean ConsultantFinished;
    private boolean AssistantFinished;
    private String WorkflowName;
    private String ObjectID;
    private String Approver;
    private String ApproverLink;
    private Date CreatedTime;
    private Date FinishedTime;
    private Date CanceledTime;


}
