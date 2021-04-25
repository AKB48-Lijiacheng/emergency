package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.EntInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.EntInfoService;
import com.westcatr.emergency.business.entity.EntInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.EntInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  EntInfo 控制器
 *   @author ls
 *  @since 2021-04-25
 */
@Validated
@Api(tags="企业信息表接口", description = "entInfo")
@Slf4j
@RestController
@RequestMapping("//entInfo")
public class EntInfoController {

    @Autowired
    private EntInfoService entInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="企业信息表分页数据接口", module="企业信息表管理")
    @IPermissions(value="entInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="企业信息表分页数据接口", notes="entInfo:page")
    @GetMapping("/page")
    public IResult<IPage<EntInfo>> getEntInfoPage(EntInfoQuery query) {
        return IResult.ok(entInfoService.iPage(query));
    }

    /**
     * 通过id获取企业信息表
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="获取企业信息表数据接口", module="企业信息表管理")
    @IPermissions(value="entInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取企业信息表数据接口", notes="entInfo:get")
    @GetMapping("/get")
    public IResult<EntInfo> getEntInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(entInfoService.iGetById(id));
    }

    /**
     * 新增企业信息表
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="新增企业信息表数据接口", level = 2, module="企业信息表管理")
    @IPermissions(value="entInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增企业信息表数据接口", notes="entInfo:add")
    @PostMapping("/add")
    public IResult addEntInfo(@RequestBody @Validated(Insert.class) EntInfo param) {
        return IResult.auto(entInfoService.iSave(param));
    }

    /**
     * 更新企业信息表
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="更新企业信息表数据接口", level = 2, module="企业信息表管理")
    @IPermissions(value="entInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新企业信息表数据接口", notes="entInfo:update")
    @PostMapping("/update")
    public IResult updateEntInfoById(@RequestBody @Validated(Update.class) EntInfo param) {
        return IResult.auto(entInfoService.iUpdate(param));
    }

    /**
     * 通过id删除企业信息表
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="删除企业信息表数据接口", level = 3, module="企业信息表管理")
    @IPermissions(value="entInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除企业信息表数据接口", notes="entInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteEntInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            entInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="企业信息表VO分页数据接口", module="企业信息表管理")
    @IPermissions(value="entInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="企业信息表VO分页数据接口", notes="entInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<EntInfoVO>> getEntInfoVoPage(EntInfoQuery query) {
        AssociationQuery<EntInfoVO> associationQuery = new AssociationQuery<>(EntInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取企业信息表VO
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="获取企业信息表VO数据接口", module="企业信息表管理")
    @IPermissions(value="entInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取企业信息表VO数据接口", notes="entInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<EntInfoVO> getEntInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<EntInfoVO> associationQuery = new AssociationQuery<>(EntInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new EntInfoQuery()));
    }

}