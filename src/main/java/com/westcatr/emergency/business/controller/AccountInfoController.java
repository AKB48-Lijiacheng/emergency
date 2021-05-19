package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.entity.AccountInfo;
import com.westcatr.emergency.business.pojo.query.AccountInfoQuery;
import com.westcatr.emergency.business.pojo.vo.AccountInfoVO;
import com.westcatr.emergency.business.service.AccountInfoService;
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
 *  AccountInfo 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="账户信息表接口", description = "accountInfo")
@Slf4j
@RestController
@RequestMapping("//accountInfo")
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="账户信息表分页数据接口", module="账户信息表管理")
    @IPermissions(value="accountInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="账户信息表分页数据接口", notes="accountInfo:page")
    @GetMapping("/page")
    public IResult<IPage<AccountInfo>> getAccountInfoPage(AccountInfoQuery query) {
        return IResult.ok(accountInfoService.iPage(query));
    }

    /**
     * 通过id获取账户信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取账户信息表数据接口", module="账户信息表管理")
    @IPermissions(value="accountInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取账户信息表数据接口", notes="accountInfo:get")
    @GetMapping("/get")
    public IResult<AccountInfo> getAccountInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(accountInfoService.iGetById(id));
    }

    /**
     * 新增账户信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增账户信息表数据接口", level = 2, module="账户信息表管理")
    @IPermissions(value="accountInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增账户信息表数据接口", notes="accountInfo:add")
    @PostMapping("/add")
    public IResult addAccountInfo(@RequestBody @Validated(Insert.class) AccountInfo param) {
        return IResult.auto(accountInfoService.iSave(param));
    }

    /**
     * 更新账户信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新账户信息表数据接口", level = 2, module="账户信息表管理")
    @IPermissions(value="accountInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新账户信息表数据接口", notes="accountInfo:update")
    @PostMapping("/update")
    public IResult updateAccountInfoById(@RequestBody @Validated(Update.class) AccountInfo param) {
        return IResult.auto(accountInfoService.iUpdate(param));
    }

    /**
     * 通过id删除账户信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除账户信息表数据接口", level = 3, module="账户信息表管理")
    @IPermissions(value="accountInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除账户信息表数据接口", notes="accountInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteAccountInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            accountInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="账户信息表VO分页数据接口", module="账户信息表管理")
    @IPermissions(value="accountInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="账户信息表VO分页数据接口", notes="accountInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<AccountInfoVO>> getAccountInfoVoPage(AccountInfoQuery query) {
        AssociationQuery<AccountInfoVO> associationQuery = new AssociationQuery<>(AccountInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取账户信息表VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取账户信息表VO数据接口", module="账户信息表管理")
    @IPermissions(value="accountInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取账户信息表VO数据接口", notes="accountInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<AccountInfoVO> getAccountInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<AccountInfoVO> associationQuery = new AssociationQuery<>(AccountInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new AccountInfoQuery()));
    }

}