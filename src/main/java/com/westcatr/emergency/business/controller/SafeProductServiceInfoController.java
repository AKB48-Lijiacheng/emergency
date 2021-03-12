package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.SafeProductServiceInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.SafeProductServiceInfoService;
import com.westcatr.emergency.business.entity.SafeProductServiceInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.SafeProductServiceInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  SafeProductServiceInfo 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="安全产品及服务表接口", description = "safeProductServiceInfo")
@Slf4j
@RestController
@RequestMapping("//safeProductServiceInfo")
public class SafeProductServiceInfoController {

    @Autowired
    private SafeProductServiceInfoService safeProductServiceInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="安全产品及服务表分页数据接口", module="安全产品及服务表管理")
    @IPermissions(value="safeProductServiceInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="安全产品及服务表分页数据接口", notes="safeProductServiceInfo:page")
    @GetMapping("/page")
    public IResult<IPage<SafeProductServiceInfo>> getSafeProductServiceInfoPage(SafeProductServiceInfoQuery query) {
        return IResult.ok(safeProductServiceInfoService.iPage(query));
    }

    /**
     * 通过id获取安全产品及服务表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取安全产品及服务表数据接口", module="安全产品及服务表管理")
    @IPermissions(value="safeProductServiceInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取安全产品及服务表数据接口", notes="safeProductServiceInfo:get")
    @GetMapping("/get")
    public IResult<SafeProductServiceInfo> getSafeProductServiceInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(safeProductServiceInfoService.iGetById(id));
    }

    /**
     * 新增安全产品及服务表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增安全产品及服务表数据接口", level = 2, module="安全产品及服务表管理")
    @IPermissions(value="safeProductServiceInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增安全产品及服务表数据接口", notes="safeProductServiceInfo:add")
    @PostMapping("/add")
    public IResult addSafeProductServiceInfo(@RequestBody @Validated(Insert.class) SafeProductServiceInfo param) {
        return IResult.auto(safeProductServiceInfoService.iSave(param));
    }

    /**
     * 更新安全产品及服务表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新安全产品及服务表数据接口", level = 2, module="安全产品及服务表管理")
    @IPermissions(value="safeProductServiceInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新安全产品及服务表数据接口", notes="safeProductServiceInfo:update")
    @PostMapping("/update")
    public IResult updateSafeProductServiceInfoById(@RequestBody @Validated(Update.class) SafeProductServiceInfo param) {
        return IResult.auto(safeProductServiceInfoService.iUpdate(param));
    }

    /**
     * 通过id删除安全产品及服务表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除安全产品及服务表数据接口", level = 3, module="安全产品及服务表管理")
    @IPermissions(value="safeProductServiceInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除安全产品及服务表数据接口", notes="safeProductServiceInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteSafeProductServiceInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            safeProductServiceInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="安全产品及服务表VO分页数据接口", module="安全产品及服务表管理")
    @IPermissions(value="safeProductServiceInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="安全产品及服务表VO分页数据接口", notes="safeProductServiceInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<SafeProductServiceInfoVO>> getSafeProductServiceInfoVoPage(SafeProductServiceInfoQuery query) {
        AssociationQuery<SafeProductServiceInfoVO> associationQuery = new AssociationQuery<>(SafeProductServiceInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取安全产品及服务表VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取安全产品及服务表VO数据接口", module="安全产品及服务表管理")
    @IPermissions(value="safeProductServiceInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取安全产品及服务表VO数据接口", notes="safeProductServiceInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<SafeProductServiceInfoVO> getSafeProductServiceInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<SafeProductServiceInfoVO> associationQuery = new AssociationQuery<>(SafeProductServiceInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new SafeProductServiceInfoQuery()));
    }

}