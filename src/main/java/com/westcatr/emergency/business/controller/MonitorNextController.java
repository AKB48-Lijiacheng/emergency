package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.entity.MonitorNext;
import com.westcatr.emergency.business.pojo.query.MonitorNextQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorNextVO;
import com.westcatr.emergency.business.service.MonitorNextService;
import com.westcatr.emergency.business.service.SituMonitorSrcInfoService;
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
    private SituMonitorSrcInfoService situMonitorSrcInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="监测信息表---去重后 等待开启流程的检测信息表分页数据接口", module="监测信息表---去重后 等待开启流程的检测信息表管理")
    @IPermissions(value="monitorNext:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="监测信息表---去重后 等待开启流程的检测信息表分页数据接口", notes="monitorNext:page")
    @GetMapping("/page")
    public IResult<IPage<MonitorNext>> getMonitorNextPage(MonitorNextQuery query) {
        return IResult.ok(monitorNextService.iPage(query));
    }

    /**
     * 通过id获取监测信息表---去重后 等待开启流程的检测信息表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="获取监测信息表---去重后 等待开启流程的检测信息表数据接口", module="监测信息表---去重后 等待开启流程的检测信息表管理")
    @IPermissions(value="monitorNext:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取监测信息表---去重后 等待开启流程的检测信息表数据接口", notes="monitorNext:get")
    @GetMapping("/get")
    public IResult<MonitorNext> getMonitorNextById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(monitorNextService.iGetById(id));
    }

    /**
     * 新增监测信息表---去重后 等待开启流程的检测信息表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="新增监测信息表---去重后 等待开启流程的检测信息表数据接口", level = 2, module="监测信息表---去重后 等待开启流程的检测信息表管理")
    @IPermissions(value="monitorNext:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增监测信息表---去重后 等待开启流程的检测信息表数据接口", notes="monitorNext:add")
    @PostMapping("/add")
    public IResult addMonitorNext(@RequestBody @Validated(Insert.class) MonitorNext param) {
        return IResult.auto(monitorNextService.iSave(param));
    }

    /**
     * 更新监测信息表---去重后 等待开启流程的检测信息表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="更新监测信息表---去重后 等待开启流程的检测信息表数据接口", level = 2, module="监测信息表---去重后 等待开启流程的检测信息表管理")
    @IPermissions(value="monitorNext:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新监测信息表---去重后 等待开启流程的检测信息表数据接口", notes="monitorNext:update")
    @PostMapping("/update")
    public IResult updateMonitorNextById(@RequestBody @Validated(Update.class) MonitorNext param) {
        return IResult.auto(monitorNextService.iUpdate(param));
    }

    /**
     * 通过id删除监测信息表---去重后 等待开启流程的检测信息表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="删除监测信息表---去重后 等待开启流程的检测信息表数据接口", level = 3, module="监测信息表---去重后 等待开启流程的检测信息表管理")
    @IPermissions(value="monitorNext:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除监测信息表---去重后 等待开启流程的检测信息表数据接口", notes="monitorNext:del")
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
    @SaveLog(value="监测信息表---去重后 等待开启流程的检测信息表VO分页数据接口", module="监测信息表---去重后 等待开启流程的检测信息表管理")
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
    @SaveLog(value="获取监测信息表---去重后 等待开启流程的检测信息表VO数据接口", module="监测信息表---去重后 等待开启流程的检测信息表管理")
    @IPermissions(value="monitorNext:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取监测信息表---去重后 等待开启流程的检测信息表VO数据接口", notes="monitorNext:get:vo")
    @GetMapping("/getVo")
    public IResult<MonitorNextVO> getMonitorNextVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<MonitorNextVO> associationQuery = new AssociationQuery<>(MonitorNextVO.class);
        return IResult.ok(associationQuery.getVo(id, new MonitorNextQuery()));
    }







    /**
     * 流程完成后回调这个接口去解除绑定
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="获取监测信息表---去重后 流程完成后回调这个接口去解除绑定", module="监测信息表---去重后 等待开启流程的检测信息表管理")
    @IPermissions(value="monitorNext:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="流程完成后回调这个接口去解除绑定", notes="monitorNext:get:disBindInstance")
    @PostMapping("/disBindInstance")
    public IResult disBindInstanceByInstanceId(@NotNull(message = "流程实例id不能为空") String instanceId) {
        log.info("sss");
        if (null==instanceId){
            return IResult.fail("请传入流程实例id");
        }
        Boolean flag = monitorNextService.endFlow(instanceId);
        if (!flag){
            return IResult.fail("监测信息解绑失败");
        }else {
            return IResult.fail("监测信息解绑成功");
        }

    }

}