package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.CityInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.CityInfoService;
import com.westcatr.emergency.business.entity.CityInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.CityInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  CityInfo 控制器
 *   @author ls
 *  @since 2021-04-21
 */
@Validated
@Api(tags="市级架构接口", description = "cityInfo")
@Slf4j
@RestController
@RequestMapping("//cityInfo")
public class CityInfoController {

    @Autowired
    private CityInfoService cityInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="市级架构分页数据接口", module="市级架构管理")
    @IPermissions(value="cityInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="市级架构分页数据接口", notes="cityInfo:page")
    @GetMapping("/page")
    public IResult<IPage<CityInfo>> getCityInfoPage(CityInfoQuery query) {
        return IResult.ok(cityInfoService.iPage(query));
    }

    /**
     * 通过id获取市级架构
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="获取市级架构数据接口", module="市级架构管理")
    @IPermissions(value="cityInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取市级架构数据接口", notes="cityInfo:get")
    @GetMapping("/get")
    public IResult<CityInfo> getCityInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(cityInfoService.iGetById(id));
    }

    /**
     * 新增市级架构
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="新增市级架构数据接口", level = 2, module="市级架构管理")
    @IPermissions(value="cityInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增市级架构数据接口", notes="cityInfo:add")
    @PostMapping("/add")
    public IResult addCityInfo(@RequestBody @Validated(Insert.class) CityInfo param) {
        return IResult.auto(cityInfoService.iSave(param));
    }

    /**
     * 更新市级架构
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="更新市级架构数据接口", level = 2, module="市级架构管理")
    @IPermissions(value="cityInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新市级架构数据接口", notes="cityInfo:update")
    @PostMapping("/update")
    public IResult updateCityInfoById(@RequestBody @Validated(Update.class) CityInfo param) {
        return IResult.auto(cityInfoService.iUpdate(param));
    }

    /**
     * 通过id删除市级架构
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="删除市级架构数据接口", level = 3, module="市级架构管理")
    @IPermissions(value="cityInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除市级架构数据接口", notes="cityInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteCityInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            cityInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="市级架构VO分页数据接口", module="市级架构管理")
    @IPermissions(value="cityInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="市级架构VO分页数据接口", notes="cityInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<CityInfoVO>> getCityInfoVoPage(CityInfoQuery query) {
        AssociationQuery<CityInfoVO> associationQuery = new AssociationQuery<>(CityInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取市级架构VO
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="获取市级架构VO数据接口", module="市级架构管理")
    @IPermissions(value="cityInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取市级架构VO数据接口", notes="cityInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<CityInfoVO> getCityInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<CityInfoVO> associationQuery = new AssociationQuery<>(CityInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new CityInfoQuery()));
    }

}