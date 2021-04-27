package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.entity.MonitorNextSrcInfo;
import com.westcatr.emergency.business.pojo.query.MonitorNextSrcInfoQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorNextSrcInfoVO;
import com.westcatr.emergency.business.service.MonitorNextService;
import com.westcatr.emergency.business.service.MonitorNextSrcInfoService;
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
 *  MonitorNextSrcInfo 控制器
 *   @author ls
 *  @since 2021-04-23
 */
@Validated
@Api(tags="去重后监测信息数据源接口", description = "monitorNextSrcInfo")
@Slf4j
@RestController
@RequestMapping("//monitorNextSrcInfo")
public class MonitorNextSrcInfoController {

    @Autowired
    private MonitorNextSrcInfoService monitorNextSrcInfoService;
    @Autowired
    private MonitorNextService monitorNextService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="分页数据接口", module="管理")
    @IPermissions(value="monitorNextSrcInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="分页数据接口", notes="monitorNextSrcInfo:page")
    @GetMapping("/page")
    public IResult<IPage<MonitorNextSrcInfo>> getMonitorNextSrcInfoPage(MonitorNextSrcInfoQuery query) {
        return IResult.ok(monitorNextSrcInfoService.iPage(query));
    }

    /**
     * 通过id获取
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="获取数据接口", module="管理")
    @IPermissions(value="monitorNextSrcInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取数据接口", notes="monitorNextSrcInfo:get")
    @GetMapping("/get")
    public IResult<MonitorNextSrcInfo> getMonitorNextSrcInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(monitorNextSrcInfoService.iGetById(id));
    }

    /**
     * 新增
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="新增数据接口", level = 2, module="管理")
    @IPermissions(value="monitorNextSrcInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增数据接口", notes="monitorNextSrcInfo:add")
    @PostMapping("/add")
    public IResult addMonitorNextSrcInfo(@RequestBody @Validated(Insert.class) MonitorNextSrcInfo param) {
        return IResult.auto(monitorNextSrcInfoService.iSave(param));
    }

    /**
     * 更新
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="更新数据接口", level = 2, module="管理")
    @IPermissions(value="monitorNextSrcInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新数据接口", notes="monitorNextSrcInfo:update")
    @PostMapping("/update")
    public IResult updateMonitorNextSrcInfoById(@RequestBody @Validated(Update.class) MonitorNextSrcInfo param) {
        return IResult.auto(monitorNextSrcInfoService.iUpdate(param));
    }

    /**
     * 通过id删除
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="删除数据接口", level = 3, module="管理")
    @IPermissions(value="monitorNextSrcInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除数据接口", notes="monitorNextSrcInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteMonitorNextSrcInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            monitorNextSrcInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="VO分页数据接口", module="管理")
    @IPermissions(value="monitorNextSrcInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="VO分页数据接口", notes="monitorNextSrcInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<MonitorNextSrcInfoVO>> getMonitorNextSrcInfoVoPage(MonitorNextSrcInfoQuery query) {
        AssociationQuery<MonitorNextSrcInfoVO> associationQuery = new AssociationQuery<>(MonitorNextSrcInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取VO
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="获取VO数据接口", module="管理")
    @IPermissions(value="monitorNextSrcInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取VO数据接口", notes="monitorNextSrcInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<MonitorNextSrcInfoVO> getMonitorNextSrcInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<MonitorNextSrcInfoVO> associationQuery = new AssociationQuery<>(MonitorNextSrcInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new MonitorNextSrcInfoQuery()));
    }




}