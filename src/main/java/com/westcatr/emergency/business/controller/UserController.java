package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.UserQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.UserService;
import com.westcatr.emergency.business.entity.User;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.UserVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  User 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="系统用户接口", description = "user")
@Slf4j
@RestController
@RequestMapping("//user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="系统用户分页数据接口", module="系统用户管理")
    @IPermissions(value="user:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="系统用户分页数据接口", notes="user:page")
    @GetMapping("/page")
    public IResult<IPage<User>> getUserPage(UserQuery query) {
        return IResult.ok(userService.iPage(query));
    }

    /**
     * 通过id获取系统用户
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取系统用户数据接口", module="系统用户管理")
    @IPermissions(value="user:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取系统用户数据接口", notes="user:get")
    @GetMapping("/get")
    public IResult<User> getUserById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(userService.iGetById(id));
    }

    /**
     * 新增系统用户
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增系统用户数据接口", level = 2, module="系统用户管理")
    @IPermissions(value="user:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增系统用户数据接口", notes="user:add")
    @PostMapping("/add")
    public IResult addUser(@RequestBody @Validated(Insert.class) User param) {
        return IResult.auto(userService.iSave(param));
    }

    /**
     * 更新系统用户
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新系统用户数据接口", level = 2, module="系统用户管理")
    @IPermissions(value="user:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新系统用户数据接口", notes="user:update")
    @PostMapping("/update")
    public IResult updateUserById(@RequestBody @Validated(Update.class) User param) {
        return IResult.auto(userService.iUpdate(param));
    }

    /**
     * 通过id删除系统用户
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除系统用户数据接口", level = 3, module="系统用户管理")
    @IPermissions(value="user:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除系统用户数据接口", notes="user:del")
    @DeleteMapping("/delete")
    public IResult deleteUserById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            userService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="系统用户VO分页数据接口", module="系统用户管理")
    @IPermissions(value="user:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="系统用户VO分页数据接口", notes="user:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<UserVO>> getUserVoPage(UserQuery query) {
        AssociationQuery<UserVO> associationQuery = new AssociationQuery<>(UserVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取系统用户VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取系统用户VO数据接口", module="系统用户管理")
    @IPermissions(value="user:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取系统用户VO数据接口", notes="user:get:vo")
    @GetMapping("/getVo")
    public IResult<UserVO> getUserVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<UserVO> associationQuery = new AssociationQuery<>(UserVO.class);
        return IResult.ok(associationQuery.getVo(id, new UserQuery()));
    }

}