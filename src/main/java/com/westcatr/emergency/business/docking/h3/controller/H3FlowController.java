package com.westcatr.emergency.business.docking.h3.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.westcatr.emergency.business.docking.h3.pojo.query.H3WorkItemQuery;
import com.westcatr.emergency.business.docking.h3.pojo.vo.EventFormVo;
import com.westcatr.emergency.business.docking.h3.pojo.vo.YjFormVo;
import com.westcatr.emergency.business.docking.h3.service.H3EventService;
import com.westcatr.emergency.business.docking.h3.service.H3FlowService;
import com.westcatr.emergency.business.entity.EventInfo;
import com.westcatr.emergency.business.entity.MonitorNext;
import com.westcatr.emergency.business.service.EventInfoService;
import com.westcatr.emergency.business.service.MonitorNextService;
import com.westcatr.rd.base.acommon.vo.IResult;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Autowired
    MonitorNextService monitorNextService;
    @Autowired
    EventInfoService eventInfoService;

    
    @Value("${h3.portal.bpm.address}")
    private String h3bpmAddress;
    @Value("${h3.portal.address}")
    private String h3Address;

    // H3表单编码
    private final String H3_YJ_FORMCODE = "Syjlcjxw";
    // H3流程模板编码
    private final String H3_YJ_WORKFLOWSCODE = "yjlcjxw";

    @Autowired
    JdbcTemplate h3JdbcTemplate;
    @Autowired
    H3EventService h3EventService;



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
     * 预警流程完成后回调这个接口
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @ApiOperation(value="预警流程完成后回调", notes="monitorNext:post:disBindInstance")
    @PostMapping("/doFinished")
    public IResult disBindInstanceByInstanceId(@NotNull(message = "流程实例id不能为空") String instanceId) {
        log.info("流程结束后的回调");
        if (null==instanceId){
            return IResult.fail("请传入流程实例id");
        }
        Boolean flag = monitorNextService.setMonitorNextStatuByInstanceId(instanceId,2);//更新监测信息状态
        if (!flag){
           throw  new MyRuntimeException("监测信息设置完成状态失败");
        }
        String workItemId  =  h3FlowService.getWorkItemsIdByInstanceId(instanceId);
        YjFormVo yjFormVo = h3ApiController.getFlowFomDataById(workItemId);//获取表单项

        QueryWrapper<MonitorNext> qw = new QueryWrapper<MonitorNext>().eq("h3_instance_id", instanceId);
        MonitorNext monitorNext = monitorNextService.getOne(qw);
        if (null==monitorNext){
            throw new MyRuntimeException("没有对应的监测信息");
        }
        MonitorNext monitorNextSave = new MonitorNext();
        monitorNextSave.setId(monitorNext.getId());
        monitorNextSave.setWarningLevel(Integer.valueOf(yjFormVo.getEarlyWarnLevel()));
        boolean b = monitorNextService.updateById(monitorNextSave);
        if (!b){
            throw new MyRuntimeException("监测信息更新预警等级失败！！");
        }
        return IResult.ok();

    }
    /**
     * 事件流程完成后回调这个接口
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @ApiOperation(value="事件流程完成后回调")
    @PostMapping("/eventFinished")
    public IResult eventFinished(@NotNull(message = "事件流程实例id不能为空") String instanceId) {
        log.info("事件流程结束后的回调");
      String workItemId  =  h3FlowService.getWorkItemsIdByInstanceId(instanceId);
        EventFormVo formVo = h3EventService.getFlowFomDataByWorkItemId(workItemId);
        QueryWrapper<MonitorNext> qw = new QueryWrapper<MonitorNext>().eq("h3_event_instance_id", instanceId);
        MonitorNext monitorNext = monitorNextService.getOne(qw);
        if (monitorNext==null){
            throw new MyRuntimeException("该事件实例id没有绑定去重后的监测数据");
        }
        EventInfo eventInfoSave = new EventInfo();
        eventInfoSave.setEventName(null);
        eventInfoSave.setEventRemake(null);
        eventInfoSave.setCreateTime(null);
        eventInfoSave.setUpdateTime(null);
        eventInfoSave.setEventStartTime(null);
        eventInfoSave.setAttackSrc(null);
        eventInfoSave.setAttackTarget(null);
        eventInfoSave.setInjuryExtent(null);
        eventInfoSave.setScopeInfluence(null);
        eventInfoSave.setAccidentUnit(null);
        eventInfoSave.setInitialImpact(null);
        eventInfoSave.setEventLevel(null);
        eventInfoSave.setEventDescrible(null);
        eventInfoSave.setEventCause(null);
        eventInfoSave.setDisposalTime(null);
        eventInfoSave.setDetailInfo(formVo.getDetailInfo());
        eventInfoSave.setPersonCharge(formVo.getPersonCharge());
        eventInfoSave.setDisposalMethod(formVo.getDisposalMethod());
        eventInfoSave.setSupporMechan(formVo.getSupporMechan());
        eventInfoSave.setH3AttachFileIds(JSON.toJSONString(formVo.getAttachFilesInfo()));
        eventInfoSave.setH3EventInstanceId(instanceId);
        boolean save = eventInfoService.save(eventInfoSave);
        if (!save) {
            throw  new MyRuntimeException("事件信息生成失败！！！");
        }
        return IResult.ok();
    }


    /**
     * 事件流程激活后回调这个接口
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @ApiOperation(value="事件流程激活后回调")
    @PostMapping("/eventStart")
    public IResult eventStart(@NotNull(message = "事件流程实例id不能为空") String instanceId) {
        log.info("事件流程激活后回调");
     String  yjInstanceId=h3FlowService.getParentInstanceId(instanceId);//获取预警实例id
        QueryWrapper<MonitorNext> qw = new QueryWrapper<MonitorNext>().eq("h3_instance_id", yjInstanceId);
        MonitorNext monitorNext = monitorNextService.getOne(qw);
        if (monitorNext==null){
            throw new MyRuntimeException("对应去重后监测信息不存在");
        }
        MonitorNext monitorNextSave = new MonitorNext();
        monitorNextSave.setId( monitorNext.getId());
        monitorNextSave.setH3EventInstanceId(instanceId);
        boolean b = monitorNextService.updateById(monitorNextSave);
        if (!b) {
            throw new MyRuntimeException("监测信息绑定事件流程实例Id失败");
        }
        return IResult.ok();
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
        List orgList = h3ApiController.getOrgs();
        return IResult.ok(orgList);
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