package com.westcatr.emergency.business.docking.h3.dto.flowDto;

import com.westcatr.emergency.business.docking.h3.dto.BaseDTO;
import lombok.Data;

/**
 * BPMServiceResult
 */
@Data
public class H3BPMServiceResult extends BaseDTO {
    private boolean success;
    private String instanceId;
    private String message;
    private String workItemId;
    private String workItemUrl;
    private String sequenceNo;


}