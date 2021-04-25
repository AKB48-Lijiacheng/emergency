package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.CountryQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.CountryService;
import com.westcatr.emergency.business.entity.Country;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.CountryVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  Country 控制器
 *   @author ls
 *  @since 2021-04-25
 */
@Validated
@Api(tags="二级行政区架构接口", description = "country")
@Slf4j
@RestController
@RequestMapping("//country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="二级行政区架构分页数据接口", module="二级行政区架构管理")
    @IPermissions(value="country:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="二级行政区架构分页数据接口", notes="country:page")
    @GetMapping("/page")
    public IResult<IPage<Country>> getCountryPage(CountryQuery query) {
        return IResult.ok(countryService.iPage(query));
    }

    /**
     * 通过id获取二级行政区架构
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="获取二级行政区架构数据接口", module="二级行政区架构管理")
    @IPermissions(value="country:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取二级行政区架构数据接口", notes="country:get")
    @GetMapping("/get")
    public IResult<Country> getCountryById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(countryService.iGetById(id));
    }

    /**
     * 新增二级行政区架构
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="新增二级行政区架构数据接口", level = 2, module="二级行政区架构管理")
    @IPermissions(value="country:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增二级行政区架构数据接口", notes="country:add")
    @PostMapping("/add")
    public IResult addCountry(@RequestBody @Validated(Insert.class) Country param) {
        return IResult.auto(countryService.iSave(param));
    }

    /**
     * 更新二级行政区架构
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="更新二级行政区架构数据接口", level = 2, module="二级行政区架构管理")
    @IPermissions(value="country:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新二级行政区架构数据接口", notes="country:update")
    @PostMapping("/update")
    public IResult updateCountryById(@RequestBody @Validated(Update.class) Country param) {
        return IResult.auto(countryService.iUpdate(param));
    }

    /**
     * 通过id删除二级行政区架构
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="删除二级行政区架构数据接口", level = 3, module="二级行政区架构管理")
    @IPermissions(value="country:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除二级行政区架构数据接口", notes="country:del")
    @DeleteMapping("/delete")
    public IResult deleteCountryById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            countryService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="二级行政区架构VO分页数据接口", module="二级行政区架构管理")
    @IPermissions(value="country:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="二级行政区架构VO分页数据接口", notes="country:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<CountryVO>> getCountryVoPage(CountryQuery query) {
        AssociationQuery<CountryVO> associationQuery = new AssociationQuery<>(CountryVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取二级行政区架构VO
     * @author : ls
     * @since : Create in 2021-04-25
     */
    @SaveLog(value="获取二级行政区架构VO数据接口", module="二级行政区架构管理")
    @IPermissions(value="country:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取二级行政区架构VO数据接口", notes="country:get:vo")
    @GetMapping("/getVo")
    public IResult<CountryVO> getCountryVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<CountryVO> associationQuery = new AssociationQuery<>(CountryVO.class);
        return IResult.ok(associationQuery.getVo(id, new CountryQuery()));
    }

}