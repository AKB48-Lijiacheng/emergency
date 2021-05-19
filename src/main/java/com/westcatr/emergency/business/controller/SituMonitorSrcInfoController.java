package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.docking.Situational.component.schedule.getMonitorDataFromSituationlScheduleTask;
import com.westcatr.emergency.business.entity.SituMonitorSrcInfo;
import com.westcatr.emergency.business.pojo.query.SituMonitorSrcInfoQuery;
import com.westcatr.emergency.business.pojo.vo.SituMonitorSrcInfoVO;
import com.westcatr.emergency.business.service.SituMonitorSrcInfoService;
import com.westcatr.emergency.config.ThreadFactory;
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
 *  SituMonitorSrcInfo 控制器
 *   @author ls
 *  @since 2021-04-08
 */
@Validated
@Api(tags="监测信息数据源接口", description = "situMonitorSrcInfo")
@Slf4j
@RestController
@RequestMapping("//situMonitorSrcInfo")
public class SituMonitorSrcInfoController {

    @Autowired
    private SituMonitorSrcInfoService situMonitorSrcInfoService;
    @Autowired
    private getMonitorDataFromSituationlScheduleTask task;

    /**
     * 执行同步程序去态势拿监测信息
     * @author : ls
     * @since : Create in 2021-04-08
     */
    @ApiOperationSupport(order=1)
    @ApiOperation(value="分页数据接口", notes="situMonitorSrcInfo:page")
    @GetMapping("/getSituDate")
    public IResult<IPage<SituMonitorSrcInfo>> getSituDate() {
        ThreadFactory.excutor(()->task.getMonitorData());
        return IResult.ok("请求成功 大概需要等待1-2分钟");
    }


    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-08
     */
    @SaveLog(value="分页数据接口", module="管理")
    @IPermissions(value="situMonitorSrcInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="分页数据接口", notes="situMonitorSrcInfo:page")
    @GetMapping("/page")
    public IResult<IPage<SituMonitorSrcInfo>> getSituMonitorSrcInfoPage(SituMonitorSrcInfoQuery query) {
        return IResult.ok(situMonitorSrcInfoService.iPage(query));
    }

    /**
     * 通过id获取
     * @author : ls
     * @since : Create in 2021-04-08
     */
    @SaveLog(value="获取数据接口", module="管理")
    @IPermissions(value="situMonitorSrcInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取数据接口", notes="situMonitorSrcInfo:get")
    @GetMapping("/get")
    public IResult<SituMonitorSrcInfo> getSituMonitorSrcInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(situMonitorSrcInfoService.iGetById(id));
    }

    /**
     * 新增
     * @author : ls
     * @since : Create in 2021-04-08
     */
    @SaveLog(value="新增数据接口", level = 2, module="管理")
    @IPermissions(value="situMonitorSrcInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增数据接口", notes="situMonitorSrcInfo:add")
    @PostMapping("/add")
    public IResult addSituMonitorSrcInfo(@RequestBody @Validated(Insert.class) SituMonitorSrcInfo param) {
        return IResult.auto(situMonitorSrcInfoService.iSave(param));
    }

    /**
     * 更新
     * @author : ls
     * @since : Create in 2021-04-08
     */
    @SaveLog(value="更新数据接口", level = 2, module="管理")
    @IPermissions(value="situMonitorSrcInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新数据接口", notes="situMonitorSrcInfo:update")
    @PostMapping("/update")
    public IResult updateSituMonitorSrcInfoById(@RequestBody @Validated(Update.class) SituMonitorSrcInfo param) {
        return IResult.auto(situMonitorSrcInfoService.iUpdate(param));
    }

    /**
     * 通过id删除
     * @author : ls
     * @since : Create in 2021-04-08
     */
    @SaveLog(value="删除数据接口", level = 3, module="管理")
    @IPermissions(value="situMonitorSrcInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除数据接口", notes="situMonitorSrcInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteSituMonitorSrcInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            situMonitorSrcInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-08
     */
    @SaveLog(value="VO分页数据接口", module="管理")
    @IPermissions(value="situMonitorSrcInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="VO分页数据接口", notes="situMonitorSrcInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<SituMonitorSrcInfoVO>> getSituMonitorSrcInfoVoPage(SituMonitorSrcInfoQuery query) {
        AssociationQuery<SituMonitorSrcInfoVO> associationQuery = new AssociationQuery<>(SituMonitorSrcInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取VO
     * @author : ls
     * @since : Create in 2021-04-08
     */
    @SaveLog(value="获取VO数据接口", module="管理")
    @IPermissions(value="situMonitorSrcInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取VO数据接口", notes="situMonitorSrcInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<SituMonitorSrcInfoVO> getSituMonitorSrcInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<SituMonitorSrcInfoVO> associationQuery = new AssociationQuery<>(SituMonitorSrcInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new SituMonitorSrcInfoQuery()));
    }

}