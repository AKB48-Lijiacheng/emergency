package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.entity.OrgConstruct;
import com.westcatr.emergency.business.pojo.query.OrgConstructQuery;
import com.westcatr.emergency.business.pojo.vo.OrgConstructVO;
import com.westcatr.emergency.business.service.OrgConstructService;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import com.westcatr.rd.base.acommon.annotation.Update;
import com.westcatr.rd.base.acommon.vo.IResult;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  OrgConstruct 控制器
 *   @author ls
 *  @since 2021-04-26
 */
@Validated
@Api(tags = "组织架构接口",description = "org")
@Slf4j
@RestController
@RequestMapping("//orgConstruct")
public class OrgConstructController {

    @Autowired
    private OrgConstructService orgConstructService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-26
     */
    @SaveLog(value="分页数据接口", module="管理")
    @IPermissions(value="orgConstruct:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="分页数据接口", notes="orgConstruct:page")
    @GetMapping("/page")
    public IResult<IPage<OrgConstruct>> getOrgConstructPage(OrgConstructQuery query) {
        return IResult.ok(orgConstructService.iPage(query));
    }

    /**
     * 通过id获取
     * @author : ls
     * @since : Create in 2021-04-26
     */
    @SaveLog(value="获取数据接口", module="管理")
    @IPermissions(value="orgConstruct:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取数据接口", notes="orgConstruct:get")
    @GetMapping("/get")
    public IResult<OrgConstruct> getOrgConstructById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(orgConstructService.iGetById(id));
    }

    /**
     * 新增
     * @author : ls
     * @since : Create in 2021-04-26
     */
    @SaveLog(value="新增数据接口", level = 2, module="管理")
    @IPermissions(value="orgConstruct:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增数据接口", notes="orgConstruct:add")
    @PostMapping("/add")
    public IResult addOrgConstruct(@RequestBody @Validated(Insert.class) OrgConstruct param) {
        return IResult.auto(orgConstructService.iSave(param));
    }

    /**
     * 更新
     * @author : ls
     * @since : Create in 2021-04-26
     */
    @SaveLog(value="更新数据接口", level = 2, module="管理")
    @IPermissions(value="orgConstruct:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新数据接口", notes="orgConstruct:update")
    @PostMapping("/update")
    public IResult updateOrgConstructById(@RequestBody @Validated(Update.class) OrgConstruct param) {
        return IResult.auto(orgConstructService.iUpdate(param));
    }

    /**
     * 通过id删除
     * @author : ls
     * @since : Create in 2021-04-26
     */
    @SaveLog(value="删除数据接口", level = 3, module="管理")
    @IPermissions(value="orgConstruct:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除数据接口", notes="orgConstruct:del")
    @DeleteMapping("/delete")
    public IResult deleteOrgConstructById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            orgConstructService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-26
     */
    @SaveLog(value="VO分页数据接口", module="管理")
    @IPermissions(value="orgConstruct:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="VO分页数据接口", notes="orgConstruct:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<OrgConstructVO>> getOrgConstructVoPage(OrgConstructQuery query) {
        AssociationQuery<OrgConstructVO> associationQuery = new AssociationQuery<>(OrgConstructVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取VO
     * @author : ls
     * @since : Create in 2021-04-26
     */
    @SaveLog(value="获取VO数据接口", module="管理")
    @IPermissions(value="orgConstruct:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取VO数据接口", notes="orgConstruct:get:vo")
    @GetMapping("/getVo")
    public IResult<OrgConstructVO> getOrgConstructVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<OrgConstructVO> associationQuery = new AssociationQuery<>(OrgConstructVO.class);
        return IResult.ok(associationQuery.getVo(id, new OrgConstructQuery()));
    }

}