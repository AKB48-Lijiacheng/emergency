package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.SecurityIncidentsInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.SecurityIncidentsInfoService;
import com.westcatr.emergency.business.entity.SecurityIncidentsInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.SecurityIncidentsInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  SecurityIncidentsInfo 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="应急处置与安全事件管理表接口", description = "securityIncidentsInfo")
@Slf4j
@RestController
@RequestMapping("//securityIncidentsInfo")
public class SecurityIncidentsInfoController {

    @Autowired
    private SecurityIncidentsInfoService securityIncidentsInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="应急处置与安全事件管理表分页数据接口", module="应急处置与安全事件管理表管理")
    @IPermissions(value="securityIncidentsInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="应急处置与安全事件管理表分页数据接口", notes="securityIncidentsInfo:page")
    @GetMapping("/page")
    public IResult<IPage<SecurityIncidentsInfo>> getSecurityIncidentsInfoPage(SecurityIncidentsInfoQuery query) {
        return IResult.ok(securityIncidentsInfoService.iPage(query));
    }

    /**
     * 通过id获取应急处置与安全事件管理表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取应急处置与安全事件管理表数据接口", module="应急处置与安全事件管理表管理")
    @IPermissions(value="securityIncidentsInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取应急处置与安全事件管理表数据接口", notes="securityIncidentsInfo:get")
    @GetMapping("/get")
    public IResult<SecurityIncidentsInfo> getSecurityIncidentsInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(securityIncidentsInfoService.iGetById(id));
    }

    /**
     * 新增应急处置与安全事件管理表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增应急处置与安全事件管理表数据接口", level = 2, module="应急处置与安全事件管理表管理")
    @IPermissions(value="securityIncidentsInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增应急处置与安全事件管理表数据接口", notes="securityIncidentsInfo:add")
    @PostMapping("/add")
    public IResult addSecurityIncidentsInfo(@RequestBody @Validated(Insert.class) SecurityIncidentsInfo param) {
        return IResult.auto(securityIncidentsInfoService.iSave(param));
    }

    /**
     * 更新应急处置与安全事件管理表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新应急处置与安全事件管理表数据接口", level = 2, module="应急处置与安全事件管理表管理")
    @IPermissions(value="securityIncidentsInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新应急处置与安全事件管理表数据接口", notes="securityIncidentsInfo:update")
    @PostMapping("/update")
    public IResult updateSecurityIncidentsInfoById(@RequestBody @Validated(Update.class) SecurityIncidentsInfo param) {
        return IResult.auto(securityIncidentsInfoService.iUpdate(param));
    }

    /**
     * 通过id删除应急处置与安全事件管理表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除应急处置与安全事件管理表数据接口", level = 3, module="应急处置与安全事件管理表管理")
    @IPermissions(value="securityIncidentsInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除应急处置与安全事件管理表数据接口", notes="securityIncidentsInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteSecurityIncidentsInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            securityIncidentsInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="应急处置与安全事件管理表VO分页数据接口", module="应急处置与安全事件管理表管理")
    @IPermissions(value="securityIncidentsInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="应急处置与安全事件管理表VO分页数据接口", notes="securityIncidentsInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<SecurityIncidentsInfoVO>> getSecurityIncidentsInfoVoPage(SecurityIncidentsInfoQuery query) {
        AssociationQuery<SecurityIncidentsInfoVO> associationQuery = new AssociationQuery<>(SecurityIncidentsInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取应急处置与安全事件管理表VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取应急处置与安全事件管理表VO数据接口", module="应急处置与安全事件管理表管理")
    @IPermissions(value="securityIncidentsInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取应急处置与安全事件管理表VO数据接口", notes="securityIncidentsInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<SecurityIncidentsInfoVO> getSecurityIncidentsInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<SecurityIncidentsInfoVO> associationQuery = new AssociationQuery<>(SecurityIncidentsInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new SecurityIncidentsInfoQuery()));
    }

}