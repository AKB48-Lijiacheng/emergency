package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.entity.MonitorNext;
import com.westcatr.emergency.business.entity.MonitorNextSrcInfo;
import com.westcatr.emergency.business.pojo.dto.AddEntNameDto;
import com.westcatr.emergency.business.pojo.query.MonitorNextQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorNextVO;
import com.westcatr.emergency.business.service.MonitorNextService;
import com.westcatr.emergency.business.service.MonitorNextSrcInfoService;
import com.westcatr.emergency.business.utils.CommonUtil;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import com.westcatr.rd.base.acommon.annotation.Update;
import com.westcatr.rd.base.acommon.vo.IResult;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  MonitorNext 控制器
 *   @author ls
 *  @since 2021-04-21
 */
@Validated
@Api(tags="监测信息去重后表", description = "monitorNext")
@Slf4j
@RestController
@RequestMapping("//monitorNext")
public class MonitorNextController {

    @Autowired
    private MonitorNextService monitorNextService;
    @Autowired
    private MonitorNextSrcInfoService monitorNextSrcInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="监测信息表---去重后 ", module="监测信息表---去重后 ")
    @IPermissions(value="monitorNext:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="监测信息表---去重后 ", notes="monitorNext:page")
    @GetMapping("/page")
    public IResult<IPage<MonitorNext>> getMonitorNextPage(MonitorNextQuery query) {
        return IResult.ok(monitorNextService.iPage(query));
    }

    /**
     * 通过id获取监测信息表---去重后 等待开启流程的检测信息表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="获取监测信息表---去重后 ", module="监测信息表---去重后 ")
    @IPermissions(value="monitorNext:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取监测信息表---去重后 ", notes="monitorNext:get")
    @GetMapping("/get")
    public IResult<MonitorNext> getMonitorNextById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(monitorNextService.iGetById(id));
    }

    /**
     * 新增监测信息表---去重后 等待开启流程的检测信息表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="新增监测信息表---去重后", level = 2, module="监测信息表---去重后 ")
    @IPermissions(value="monitorNext:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增监测信息表---去重后 ", notes="monitorNext:add")
    @PostMapping("/add")
    public IResult addMonitorNext(@RequestBody @Validated(Insert.class) MonitorNext param) {
        return IResult.auto(monitorNextService.iSave(param));
    }

    /**
     * 更新监测信息表---去重后 等待开启流程的检测信息表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="更新监测信息表---去重后 ", level = 2, module="监测信息表---去重后 ")
    @IPermissions(value="monitorNext:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新监测信息表---去重后 ", notes="monitorNext:update")
    @PostMapping("/update")
    public IResult updateMonitorNextById(@RequestBody @Validated(Update.class) MonitorNext param) {
        return IResult.auto(monitorNextService.iUpdate(param));
    }

    /**
     * 通过id删除监测信息表---去重后 等待开启流程的检测信息表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="删除监测信息表---去重后 ", level = 3, module="监测信息表---去重后 ")
    @IPermissions(value="monitorNext:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除监测信息表---去重后 ", notes="monitorNext:del")
    @DeleteMapping("/delete")
    public IResult deleteMonitorNextById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            monitorNextService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="监测信息表---去重后 ", module="监测信息表---去重后 ")
    @IPermissions(value="monitorNext:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="监测信息表---去重后 等待开启流程的检测信息表VO分页数据接口", notes="monitorNext:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<MonitorNextVO>> getMonitorNextVoPage(MonitorNextQuery query) {
        AssociationQuery<MonitorNextVO> associationQuery = new AssociationQuery<>(MonitorNextVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取监测信息表---去重后 等待开启流程的检测信息表VO
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="获取监测信息表---去重后 ", module="监测信息表---去重后 ")
    @IPermissions(value="monitorNext:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取监测信息表---去重后 ", notes="monitorNext:get:vo")
    @GetMapping("/getVo")
    public IResult<MonitorNextVO> getMonitorNextVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<MonitorNextVO> associationQuery = new AssociationQuery<>(MonitorNextVO.class);
        return IResult.ok(associationQuery.getVo(id, new MonitorNextQuery()));
    }


    @SaveLog(value = "获取监测信息数据源", module = "监测信息表管理")
    @ApiOperation(value = "获取监测信息数据源", notes = "monitorInfo:srcDetail")
    @ApiOperationSupport(order = 9)
    @GetMapping("/srcDetail/{monitorNextId}")
    public IResult getMonitorNextSrc(@PathVariable("monitorNextId") String id) {
        MonitorNext monitor = monitorNextService.getById(id);
        MonitorNextSrcInfo monitorNextSrcInfo = monitorNextSrcInfoService.getOne(new QueryWrapper<MonitorNextSrcInfo>().eq("id", monitor.getSituMonitorSrcId()));
        return IResult.ok(monitorNextSrcInfo);
    }


    @SaveLog(value = "根据流程实例Id获取监测信息", module = "监测信息表管理")
    @ApiOperation(value = "根据流程实例Id获取监测信息", notes = "monitorInfo:getInfoByInstanceId")
    @ApiOperationSupport(order = 9)
    @GetMapping("/getInfoByInstanceId/{instanceId}")
    public IResult getInfoByInstanceId(@PathVariable("instanceId") String instanceId) {
        MonitorNextVO vo = monitorNextService.getInfoByInstanceId(instanceId);
        return IResult.ok(vo);
    }


    @SaveLog(value = "监测信息添加相关企业字段", module = "监测信息表管理")
    @ApiOperation(value = "监测信息添加相关企业字段", notes = "monitorInfo:addEntName")
    @ApiOperationSupport(order = 9)
    @PostMapping("/addEntName")
    public IResult addEntName(@RequestBody@Validated AddEntNameDto addEntNameDto) {
        Long monitNextId = addEntNameDto.getId();
        Long entId = addEntNameDto.getEntInfoId();
        Boolean b = monitorNextService.addEntName(monitNextId,entId);
        return IResult.auto(b);
    }






    /**
     * 事件图标查询接口
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="预警信息去重后月统计图查询接口", module="事件信息表管理")
    @IPermissions(value="eventInfo:get:getMonitorNextCountByMonth")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="预警信息处理月统计图查询接口", notes="eventInfo:get:getMonitorNextCountByMonth")
    @GetMapping("/getMonitorNextCountByMonth")
    public IResult getMonitorNextCountByMonth() {
        List<Map<Object, Object>> timeList = CommonUtil.getYearAgoMonthMap();
        List<Map<Object,Object>> queryList  =monitorNextService.getMonitorNextCount();
        for (Map<Object, Object> mao : queryList) {
            Object time = mao.get("time");
            Object count = mao.get("count");
            for (Map<Object, Object> timeMap : timeList) {
                if (timeMap.get("time").equals(time)) {
                    timeMap.put("count", count);
                    break;
                }
            }

        }
        return IResult.ok(timeList);
        //todo
    }



}