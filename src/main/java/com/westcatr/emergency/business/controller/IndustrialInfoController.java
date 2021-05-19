package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.IndustrialInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.IndustrialInfoService;
import com.westcatr.emergency.business.entity.IndustrialInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.IndustrialInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  IndustrialInfo 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="产业库大全接口", description = "industrialInfo")
@Slf4j
@RestController
@RequestMapping("//industrialInfo")
public class IndustrialInfoController {

    @Autowired
    private IndustrialInfoService industrialInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="产业库大全分页数据接口", module="产业库大全管理")
    @IPermissions(value="industrialInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="产业库大全分页数据接口", notes="industrialInfo:page")
    @GetMapping("/page")
    public IResult<IPage<IndustrialInfo>> getIndustrialInfoPage(IndustrialInfoQuery query) {
        return IResult.ok(industrialInfoService.iPage(query));
    }

    /**
     * 通过id获取产业库大全
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取产业库大全数据接口", module="产业库大全管理")
    @IPermissions(value="industrialInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取产业库大全数据接口", notes="industrialInfo:get")
    @GetMapping("/get")
    public IResult<IndustrialInfo> getIndustrialInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(industrialInfoService.iGetById(id));
    }

    /**
     * 新增产业库大全
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增产业库大全数据接口", level = 2, module="产业库大全管理")
    @IPermissions(value="industrialInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增产业库大全数据接口", notes="industrialInfo:add")
    @PostMapping("/add")
    public IResult addIndustrialInfo(@RequestBody @Validated(Insert.class) IndustrialInfo param) {
        return IResult.auto(industrialInfoService.iSave(param));
    }

    /**
     * 更新产业库大全
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新产业库大全数据接口", level = 2, module="产业库大全管理")
    @IPermissions(value="industrialInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新产业库大全数据接口", notes="industrialInfo:update")
    @PostMapping("/update")
    public IResult updateIndustrialInfoById(@RequestBody @Validated(Update.class) IndustrialInfo param) {
        return IResult.auto(industrialInfoService.iUpdate(param));
    }

    /**
     * 通过id删除产业库大全
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除产业库大全数据接口", level = 3, module="产业库大全管理")
    @IPermissions(value="industrialInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除产业库大全数据接口", notes="industrialInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteIndustrialInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            industrialInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="产业库大全VO分页数据接口", module="产业库大全管理")
    @IPermissions(value="industrialInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="产业库大全VO分页数据接口", notes="industrialInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<IndustrialInfoVO>> getIndustrialInfoVoPage(IndustrialInfoQuery query) {
        AssociationQuery<IndustrialInfoVO> associationQuery = new AssociationQuery<>(IndustrialInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取产业库大全VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取产业库大全VO数据接口", module="产业库大全管理")
    @IPermissions(value="industrialInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取产业库大全VO数据接口", notes="industrialInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<IndustrialInfoVO> getIndustrialInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<IndustrialInfoVO> associationQuery = new AssociationQuery<>(IndustrialInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new IndustrialInfoQuery()));
    }

}