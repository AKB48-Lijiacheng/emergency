package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.EmergencySuppliesInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.EmergencySuppliesInfoService;
import com.westcatr.emergency.business.entity.EmergencySuppliesInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.EmergencySuppliesInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  EmergencySuppliesInfo 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="应急物资存储信息表接口", description = "emergencySuppliesInfo")
@Slf4j
@RestController
@RequestMapping("//emergencySuppliesInfo")
public class EmergencySuppliesInfoController {

    @Autowired
    private EmergencySuppliesInfoService emergencySuppliesInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="应急物资存储信息表分页数据接口", module="应急物资存储信息表管理")
    @IPermissions(value="emergencySuppliesInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="应急物资存储信息表分页数据接口", notes="emergencySuppliesInfo:page")
    @GetMapping("/page")
    public IResult<IPage<EmergencySuppliesInfo>> getEmergencySuppliesInfoPage(EmergencySuppliesInfoQuery query) {
        return IResult.ok(emergencySuppliesInfoService.iPage(query));
    }

    /**
     * 通过id获取应急物资存储信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取应急物资存储信息表数据接口", module="应急物资存储信息表管理")
    @IPermissions(value="emergencySuppliesInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取应急物资存储信息表数据接口", notes="emergencySuppliesInfo:get")
    @GetMapping("/get")
    public IResult<EmergencySuppliesInfo> getEmergencySuppliesInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(emergencySuppliesInfoService.iGetById(id));
    }

    /**
     * 新增应急物资存储信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增应急物资存储信息表数据接口", level = 2, module="应急物资存储信息表管理")
    @IPermissions(value="emergencySuppliesInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增应急物资存储信息表数据接口", notes="emergencySuppliesInfo:add")
    @PostMapping("/add")
    public IResult addEmergencySuppliesInfo(@RequestBody @Validated(Insert.class) EmergencySuppliesInfo param) {
        return IResult.auto(emergencySuppliesInfoService.iSave(param));
    }

    /**
     * 更新应急物资存储信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新应急物资存储信息表数据接口", level = 2, module="应急物资存储信息表管理")
    @IPermissions(value="emergencySuppliesInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新应急物资存储信息表数据接口", notes="emergencySuppliesInfo:update")
    @PostMapping("/update")
    public IResult updateEmergencySuppliesInfoById(@RequestBody @Validated(Update.class) EmergencySuppliesInfo param) {
        return IResult.auto(emergencySuppliesInfoService.iUpdate(param));
    }

    /**
     * 通过id删除应急物资存储信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除应急物资存储信息表数据接口", level = 3, module="应急物资存储信息表管理")
    @IPermissions(value="emergencySuppliesInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除应急物资存储信息表数据接口", notes="emergencySuppliesInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteEmergencySuppliesInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            emergencySuppliesInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="应急物资存储信息表VO分页数据接口", module="应急物资存储信息表管理")
    @IPermissions(value="emergencySuppliesInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="应急物资存储信息表VO分页数据接口", notes="emergencySuppliesInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<EmergencySuppliesInfoVO>> getEmergencySuppliesInfoVoPage(EmergencySuppliesInfoQuery query) {
        AssociationQuery<EmergencySuppliesInfoVO> associationQuery = new AssociationQuery<>(EmergencySuppliesInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取应急物资存储信息表VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取应急物资存储信息表VO数据接口", module="应急物资存储信息表管理")
    @IPermissions(value="emergencySuppliesInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取应急物资存储信息表VO数据接口", notes="emergencySuppliesInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<EmergencySuppliesInfoVO> getEmergencySuppliesInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<EmergencySuppliesInfoVO> associationQuery = new AssociationQuery<>(EmergencySuppliesInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new EmergencySuppliesInfoQuery()));
    }

}