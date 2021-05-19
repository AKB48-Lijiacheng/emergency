package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.SupportMechanismInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.SupportMechanismInfoService;
import com.westcatr.emergency.business.entity.SupportMechanismInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.SupportMechanismInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  SupportMechanismInfo 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="技术支撑机构接口", description = "supportMechanismInfo")
@Slf4j
@RestController
@RequestMapping("//supportMechanismInfo")
public class SupportMechanismInfoController {

    @Autowired
    private SupportMechanismInfoService supportMechanismInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="技术支撑机构分页数据接口", module="技术支撑机构管理")
    @IPermissions(value="supportMechanismInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="技术支撑机构分页数据接口", notes="supportMechanismInfo:page")
    @GetMapping("/page")
    public IResult<IPage<SupportMechanismInfo>> getSupportMechanismInfoPage(SupportMechanismInfoQuery query) {
        return IResult.ok(supportMechanismInfoService.iPage(query));
    }

    /**
     * 通过id获取技术支撑机构
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取技术支撑机构数据接口", module="技术支撑机构管理")
    @IPermissions(value="supportMechanismInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取技术支撑机构数据接口", notes="supportMechanismInfo:get")
    @GetMapping("/get")
    public IResult<SupportMechanismInfo> getSupportMechanismInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(supportMechanismInfoService.iGetById(id));
    }

    /**
     * 新增技术支撑机构
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增技术支撑机构数据接口", level = 2, module="技术支撑机构管理")
    @IPermissions(value="supportMechanismInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增技术支撑机构数据接口", notes="supportMechanismInfo:add")
    @PostMapping("/add")
    public IResult addSupportMechanismInfo(@RequestBody @Validated(Insert.class) SupportMechanismInfo param) {
        return IResult.auto(supportMechanismInfoService.iSave(param));
    }

    /**
     * 更新技术支撑机构
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新技术支撑机构数据接口", level = 2, module="技术支撑机构管理")
    @IPermissions(value="supportMechanismInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新技术支撑机构数据接口", notes="supportMechanismInfo:update")
    @PostMapping("/update")
    public IResult updateSupportMechanismInfoById(@RequestBody @Validated(Update.class) SupportMechanismInfo param) {
        return IResult.auto(supportMechanismInfoService.iUpdate(param));
    }

    /**
     * 通过id删除技术支撑机构
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除技术支撑机构数据接口", level = 3, module="技术支撑机构管理")
    @IPermissions(value="supportMechanismInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除技术支撑机构数据接口", notes="supportMechanismInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteSupportMechanismInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            supportMechanismInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="技术支撑机构VO分页数据接口", module="技术支撑机构管理")
    @IPermissions(value="supportMechanismInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="技术支撑机构VO分页数据接口", notes="supportMechanismInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<SupportMechanismInfoVO>> getSupportMechanismInfoVoPage(SupportMechanismInfoQuery query) {
        AssociationQuery<SupportMechanismInfoVO> associationQuery = new AssociationQuery<>(SupportMechanismInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取技术支撑机构VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取技术支撑机构VO数据接口", module="技术支撑机构管理")
    @IPermissions(value="supportMechanismInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取技术支撑机构VO数据接口", notes="supportMechanismInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<SupportMechanismInfoVO> getSupportMechanismInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<SupportMechanismInfoVO> associationQuery = new AssociationQuery<>(SupportMechanismInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new SupportMechanismInfoQuery()));
    }

}