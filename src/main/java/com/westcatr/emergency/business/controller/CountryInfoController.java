package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.CountryInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.CountryInfoService;
import com.westcatr.emergency.business.entity.CountryInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.CountryInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  CountryInfo 控制器
 *   @author ls
 *  @since 2021-04-21
 */
@Validated
@Api(tags="二级行政区架构接口", description = "countryInfo")
@Slf4j
@RestController
@RequestMapping("//countryInfo")
public class CountryInfoController {

    @Autowired
    private CountryInfoService countryInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="二级行政区架构分页数据接口", module="二级行政区架构管理")
    @IPermissions(value="countryInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="二级行政区架构分页数据接口", notes="countryInfo:page")
    @GetMapping("/page")
    public IResult<IPage<CountryInfo>> getCountryInfoPage(CountryInfoQuery query) {
        return IResult.ok(countryInfoService.iPage(query));
    }

    /**
     * 通过id获取二级行政区架构
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="获取二级行政区架构数据接口", module="二级行政区架构管理")
    @IPermissions(value="countryInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取二级行政区架构数据接口", notes="countryInfo:get")
    @GetMapping("/get")
    public IResult<CountryInfo> getCountryInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(countryInfoService.iGetById(id));
    }

    /**
     * 新增二级行政区架构
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="新增二级行政区架构数据接口", level = 2, module="二级行政区架构管理")
    @IPermissions(value="countryInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增二级行政区架构数据接口", notes="countryInfo:add")
    @PostMapping("/add")
    public IResult addCountryInfo(@RequestBody @Validated(Insert.class) CountryInfo param) {
        return IResult.auto(countryInfoService.iSave(param));
    }

    /**
     * 更新二级行政区架构
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="更新二级行政区架构数据接口", level = 2, module="二级行政区架构管理")
    @IPermissions(value="countryInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新二级行政区架构数据接口", notes="countryInfo:update")
    @PostMapping("/update")
    public IResult updateCountryInfoById(@RequestBody @Validated(Update.class) CountryInfo param) {
        return IResult.auto(countryInfoService.iUpdate(param));
    }

    /**
     * 通过id删除二级行政区架构
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="删除二级行政区架构数据接口", level = 3, module="二级行政区架构管理")
    @IPermissions(value="countryInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除二级行政区架构数据接口", notes="countryInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteCountryInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            countryInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="二级行政区架构VO分页数据接口", module="二级行政区架构管理")
    @IPermissions(value="countryInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="二级行政区架构VO分页数据接口", notes="countryInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<CountryInfoVO>> getCountryInfoVoPage(CountryInfoQuery query) {
        AssociationQuery<CountryInfoVO> associationQuery = new AssociationQuery<>(CountryInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取二级行政区架构VO
     * @author : ls
     * @since : Create in 2021-04-21
     */
    @SaveLog(value="获取二级行政区架构VO数据接口", module="二级行政区架构管理")
    @IPermissions(value="countryInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取二级行政区架构VO数据接口", notes="countryInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<CountryInfoVO> getCountryInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<CountryInfoVO> associationQuery = new AssociationQuery<>(CountryInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new CountryInfoQuery()));
    }

}