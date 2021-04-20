package com.westcatr.emergency.business.docking.h3.controller;

import com.westcatr.emergency.business.docking.h3.query.H3WorkItemQuery;
import com.westcatr.emergency.business.docking.h3.service.H3FlowService;
import com.westcatr.rd.base.acommon.vo.IResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/h3/flow")
@Api(description = "H3流程相关接口")
public class H3FlowController {
    @Autowired
    private H3ApiController h3ApiController;
    @Autowired
    H3FlowService h3FlowService;
    
    @Value("${h3.portal.bpm.address}")
    private String h3bpmAddress;
    @Value("${h3.portal.address}")
    private String h3Address;




    @PostMapping("/unFinishWorkItems")
    @ApiOperation(value = "查询用户待办任务")
    public IResult unFinishWorkItems(@RequestBody H3WorkItemQuery h3WorkItemQuery, @RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "size",required = false) Integer size) {
        return h3FlowService.getUnFinishWorkItems(h3WorkItemQuery,page,size);
    }


    @PostMapping("/finishWorkItems")
    @ApiOperation(value = "查询用户已办任务")
    public IResult finishWorkItems(@RequestBody H3WorkItemQuery finishedFlowsQuery, @RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "size",required = false) Integer rows) {
        return h3FlowService.getFinishWorkItems(finishedFlowsQuery,page, rows);
    }


   
}