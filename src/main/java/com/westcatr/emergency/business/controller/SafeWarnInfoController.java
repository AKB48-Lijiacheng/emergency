package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.SafeWarnInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.SafeWarnInfoService;
import com.westcatr.emergency.business.entity.SafeWarnInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.SafeWarnInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  SafeWarnInfo 控制器
 *   @author ls
 *  @since 2021-03-29
 */
@Validated
@Api(tags="安全预警表接口", description = "safeWarnInfo")
@Slf4j
@RestController
@RequestMapping("//safeWarnInfo")
public class SafeWarnInfoController {

    @Autowired
    private SafeWarnInfoService safeWarnInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-29
     */
    @SaveLog(value="安全预警表分页数据接口", module="安全预警表管理")
    @IPermissions(value="safeWarnInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="安全预警表分页数据接口", notes="safeWarnInfo:page")
    @GetMapping("/page")
    public IResult<IPage<SafeWarnInfo>> getSafeWarnInfoPage(SafeWarnInfoQuery query) {
        return IResult.ok(safeWarnInfoService.iPage(query));
    }

    /**
     * 通过id获取安全预警表
     * @author : ls
     * @since : Create in 2021-03-29
     */
    @SaveLog(value="获取安全预警表数据接口", module="安全预警表管理")
    @IPermissions(value="safeWarnInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取安全预警表数据接口", notes="safeWarnInfo:get")
    @GetMapping("/get")
    public IResult<SafeWarnInfo> getSafeWarnInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(safeWarnInfoService.iGetById(id));
    }

    /**
     * 新增安全预警表
     * @author : ls
     * @since : Create in 2021-03-29
     */
    @SaveLog(value="新增安全预警表数据接口", level = 2, module="安全预警表管理")
    @IPermissions(value="safeWarnInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增安全预警表数据接口", notes="safeWarnInfo:add")
    @PostMapping("/add")
    public IResult addSafeWarnInfo(@RequestBody @Validated(Insert.class) SafeWarnInfo param) {
        return IResult.auto(safeWarnInfoService.iSave(param));
    }

    /**
     * 更新安全预警表
     * @author : ls
     * @since : Create in 2021-03-29
     */
    @SaveLog(value="更新安全预警表数据接口", level = 2, module="安全预警表管理")
    @IPermissions(value="safeWarnInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新安全预警表数据接口", notes="safeWarnInfo:update")
    @PostMapping("/update")
    public IResult updateSafeWarnInfoById(@RequestBody @Validated(Update.class) SafeWarnInfo param) {
        return IResult.auto(safeWarnInfoService.iUpdate(param));
    }

    /**
     * 通过id删除安全预警表
     * @author : ls
     * @since : Create in 2021-03-29
     */
    @SaveLog(value="删除安全预警表数据接口", level = 3, module="安全预警表管理")
    @IPermissions(value="safeWarnInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除安全预警表数据接口", notes="safeWarnInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteSafeWarnInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            safeWarnInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-29
     */
    @SaveLog(value="安全预警表VO分页数据接口", module="安全预警表管理")
    @IPermissions(value="safeWarnInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="安全预警表VO分页数据接口", notes="safeWarnInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<SafeWarnInfoVO>> getSafeWarnInfoVoPage(SafeWarnInfoQuery query) {
        AssociationQuery<SafeWarnInfoVO> associationQuery = new AssociationQuery<>(SafeWarnInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取安全预警表VO
     * @author : ls
     * @since : Create in 2021-03-29
     */
    @SaveLog(value="获取安全预警表VO数据接口", module="安全预警表管理")
    @IPermissions(value="safeWarnInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取安全预警表VO数据接口", notes="safeWarnInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<SafeWarnInfoVO> getSafeWarnInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<SafeWarnInfoVO> associationQuery = new AssociationQuery<>(SafeWarnInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new SafeWarnInfoQuery()));
    }

}