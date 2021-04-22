package com.westcatr.emergency.business.docking.h3.controller;

import com.westcatr.emergency.business.docking.h3.query.H3WorkItemQuery;
import com.westcatr.emergency.business.docking.h3.service.H3FlowService;
import com.westcatr.emergency.business.docking.h3.vo.YjFormVo;
import com.westcatr.rd.base.acommon.vo.IResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/h3/flow")
@Api(tags = "H3流程相关接口=")
@Validated
@Slf4j
public class H3FlowController {
    @Autowired
    private H3ApiController h3ApiController;
    @Autowired
    H3FlowService h3FlowService;
    
    @Value("${h3.portal.bpm.address}")
    private String h3bpmAddress;
    @Value("${h3.portal.address}")
    private String h3Address;

    // H3表单编码
    private final String H3_YJ_FORMCODE = "Syjlcjxw";
    // H3流程模板编码
    private final String H3_YJ_WORKFLOWSCODE = "yjlcjxw";


    @PostMapping("/unFinishWorkItems")
    @ApiOperation(value = "查询用户待办任务")
    public IResult unFinishWorkItems(@RequestBody H3WorkItemQuery h3WorkItemQuery ) {
        return h3FlowService.getUnFinishWorkItems(h3WorkItemQuery,h3WorkItemQuery.getPage(),h3WorkItemQuery.getSize());
    }


    @PostMapping("/finishWorkItems")
    @ApiOperation(value = "查询用户已办任务")
    public IResult finishWorkItems(@RequestBody H3WorkItemQuery h3WorkItemQuery) {
        return h3FlowService.getFinishWorkItems(h3WorkItemQuery,h3WorkItemQuery.getPage(),h3WorkItemQuery.getSize());
    }

    /**
     * h3预警获取流程表单信息,通过待办流程id,
     * @author lijiacheng
     * @since 2021/4/13
     **/
    @ApiOperation(value = "获取流程表单信息")
    @GetMapping("/getFlowFomDataByWorkItemId/{workItemId}")
    public IResult getFlowFomDataByWorkItemId(@PathVariable("workItemId") String workItemId) {
        YjFormVo fomData = h3ApiController.getFlowFomDataById(workItemId);
        return IResult.ok(fomData);
    }



    /**
     * h3Yj获取组织
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    @ApiOperation(value = "h3获取所有组织")
    @GetMapping("/getOrgs")
    public IResult getOrgs() {
        return h3ApiController.getOrgs();
    }

    /**
     * h3Yj根据组织Id获取用户列表
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    @ApiOperation(value = "h3获取用户列表，通过组织id")
    @GetMapping("/getUsersByOrgId")
    public IResult getUsersByOrgId(@RequestParam("id") String orgId) {
        return h3ApiController.getUsersByOrgId(orgId);
    }
   
}