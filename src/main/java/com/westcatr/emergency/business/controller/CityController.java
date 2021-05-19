package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.CityQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.CityService;
import com.westcatr.emergency.business.entity.City;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.CityVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  City 控制器
 *   @author ls
 *  @since 2021-04-25
 */
@Validated
@Api(tags="市级架构接口", description = "city")
@Slf4j
@RestController
@RequestMapping("//city")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="市级架构分页数据接口", module="市级架构管理")
    @IPermissions(value="city:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="市级架构分页数据接口", notes="city:page")
    @GetMapping("/page")
    public IResult<IPage<City>> getCityPage(CityQuery query) {
        return IResult.ok(cityService.iPage(query));
    }

    /**
     * 通过id获取市级架构
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="获取市级架构数据接口", module="市级架构管理")
    @IPermissions(value="city:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取市级架构数据接口", notes="city:get")
    @GetMapping("/get")
    public IResult<City> getCityById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(cityService.iGetById(id));
    }

    /**
     * 新增市级架构
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="新增市级架构数据接口", level = 2, module="市级架构管理")
    @IPermissions(value="city:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增市级架构数据接口", notes="city:add")
    @PostMapping("/add")
    public IResult addCity(@RequestBody @Validated(Insert.class) City param) {
        return IResult.auto(cityService.iSave(param));
    }

    /**
     * 更新市级架构
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="更新市级架构数据接口", level = 2, module="市级架构管理")
    @IPermissions(value="city:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新市级架构数据接口", notes="city:update")
    @PostMapping("/update")
    public IResult updateCityById(@RequestBody @Validated(Update.class) City param) {
        return IResult.auto(cityService.iUpdate(param));
    }

    /**
     * 通过id删除市级架构
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="删除市级架构数据接口", level = 3, module="市级架构管理")
    @IPermissions(value="city:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除市级架构数据接口", notes="city:del")
    @DeleteMapping("/delete")
    public IResult deleteCityById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            cityService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="市级架构VO分页数据接口", module="市级架构管理")
    @IPermissions(value="city:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="市级架构VO分页数据接口", notes="city:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<CityVO>> getCityVoPage(CityQuery query) {
        AssociationQuery<CityVO> associationQuery = new AssociationQuery<>(CityVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取市级架构VO
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="获取市级架构VO数据接口", module="市级架构管理")
    @IPermissions(value="city:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取市级架构VO数据接口", notes="city:get:vo")
    @GetMapping("/getVo")
    public IResult<CityVO> getCityVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<CityVO> associationQuery = new AssociationQuery<>(CityVO.class);
        return IResult.ok(associationQuery.getVo(id, new CityQuery()));
    }

}