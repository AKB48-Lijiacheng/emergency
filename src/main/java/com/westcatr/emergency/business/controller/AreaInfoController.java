package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.AreaInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.AreaInfoService;
import com.westcatr.emergency.business.entity.AreaInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.AreaInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  AreaInfo 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="区县基本信息表接口", description = "areaInfo")
@Slf4j
@RestController
@RequestMapping("//areaInfo")
public class AreaInfoController {

    @Autowired
    private AreaInfoService areaInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="区县基本信息表分页数据接口", module="区县基本信息表管理")
    @IPermissions(value="areaInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="区县基本信息表分页数据接口", notes="areaInfo:page")
    @GetMapping("/page")
    public IResult<IPage<AreaInfo>> getAreaInfoPage(AreaInfoQuery query) {
        return IResult.ok(areaInfoService.iPage(query));
    }

    /**
     * 通过id获取区县基本信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取区县基本信息表数据接口", module="区县基本信息表管理")
    @IPermissions(value="areaInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取区县基本信息表数据接口", notes="areaInfo:get")
    @GetMapping("/get")
    public IResult<AreaInfo> getAreaInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(areaInfoService.iGetById(id));
    }

    /**
     * 新增区县基本信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增区县基本信息表数据接口", level = 2, module="区县基本信息表管理")
    @IPermissions(value="areaInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增区县基本信息表数据接口", notes="areaInfo:add")
    @PostMapping("/add")
    public IResult addAreaInfo(@RequestBody @Validated(Insert.class) AreaInfo param) {
        return IResult.auto(areaInfoService.iSave(param));
    }

    /**
     * 更新区县基本信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新区县基本信息表数据接口", level = 2, module="区县基本信息表管理")
    @IPermissions(value="areaInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新区县基本信息表数据接口", notes="areaInfo:update")
    @PostMapping("/update")
    public IResult updateAreaInfoById(@RequestBody @Validated(Update.class) AreaInfo param) {
        return IResult.auto(areaInfoService.iUpdate(param));
    }

    /**
     * 通过id删除区县基本信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除区县基本信息表数据接口", level = 3, module="区县基本信息表管理")
    @IPermissions(value="areaInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除区县基本信息表数据接口", notes="areaInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteAreaInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            areaInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="区县基本信息表VO分页数据接口", module="区县基本信息表管理")
    @IPermissions(value="areaInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="区县基本信息表VO分页数据接口", notes="areaInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<AreaInfoVO>> getAreaInfoVoPage(AreaInfoQuery query) {
        AssociationQuery<AreaInfoVO> associationQuery = new AssociationQuery<>(AreaInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取区县基本信息表VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取区县基本信息表VO数据接口", module="区县基本信息表管理")
    @IPermissions(value="areaInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取区县基本信息表VO数据接口", notes="areaInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<AreaInfoVO> getAreaInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<AreaInfoVO> associationQuery = new AssociationQuery<>(AreaInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new AreaInfoQuery()));
    }

}