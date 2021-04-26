package com.westcatr.emergency.business.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.docking.h3.service.H3YjService;
import com.westcatr.emergency.business.entity.EntInfo;
import com.westcatr.emergency.business.entity.User;
import com.westcatr.emergency.business.pojo.dto.UserDto;
import com.westcatr.emergency.business.pojo.query.UserQuery;
import com.westcatr.emergency.business.pojo.vo.UserVO;
import com.westcatr.emergency.business.service.EntInfoService;
import com.westcatr.emergency.business.service.UserService;
import com.westcatr.emergency.config.ThreadFactory;
import com.westcatr.emergency.config.rabbitMqConfig.RabbitProducer;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import com.westcatr.rd.base.acommon.annotation.Update;
import com.westcatr.rd.base.acommon.vo.IResult;
import com.westcatr.rd.base.authority.authority.provider.AuthenticationProvider;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  User 控制器
 *   @author ls
 *  @since 2021-04-09
 */
@Validated
@Api(tags="系统用户接口", description = "user")
@Slf4j
@RestController
@RequestMapping("//user")
public class UserController {
    @Autowired
    RabbitProducer rabbitProducer;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    EntInfoService entInfoService;
    @Autowired
    H3YjService h3YjService;

    /**
     * 获取分页列表
     *
     * @author : ls
     * @since : Create in 2021-04-09
     */
    @SaveLog(value = "系统用户分页数据接口", module = "系统用户管理")
    @IPermissions(value = "user:page")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "系统用户分页数据接口", notes = "user:page")
    @GetMapping("/page")
    public IResult<IPage<User>> getUserPage(UserQuery query) {
        return IResult.ok(userService.iPage(query));
    }

    /**
     * 通过id获取系统用户
     *
     * @author : ls
     * @since : Create in 2021-04-09
     */
    @SaveLog(value = "获取系统用户数据接口", module = "系统用户管理")
    @IPermissions(value = "user:get")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "获取系统用户数据接口", notes = "user:get")
    @GetMapping("/get")
    public IResult<User> getUserById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(userService.iGetById(id));
    }

    /**
     * 新增系统用户
     *
     * @author : ls
     * @since : Create in 2021-04-09
     */
    @SaveLog(value = "新增系统用户数据接口", level = 2, module = "系统用户管理")
    @IPermissions(value = "user:add")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "新增系统用户数据接口", notes = "user:add")
    @PostMapping("/add")
    public IResult addUser(@RequestBody @Validated(Insert.class) User param) {
        return IResult.auto(userService.iSave(param));
    }

    /**
     * 更新系统用户
     *
     * @author : ls
     * @since : Create in 2021-04-09
     */
    @SaveLog(value = "更新系统用户数据接口", level = 2, module = "系统用户管理")
    @IPermissions(value = "user:update")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "更新系统用户数据接口", notes = "user:update")
    @PostMapping("/update")
    public IResult updateUserById(@RequestBody @Validated(Update.class) User param) {
        return IResult.auto(userService.iUpdate(param));
    }

    /**
     * 通过id删除系统用户
     *
     * @author : ls
     * @since : Create in 2021-04-09
     */
    @SaveLog(value = "删除系统用户数据接口", level = 3, module = "系统用户管理")
    @IPermissions(value = "user:del")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除系统用户数据接口", notes = "user:del")
    @DeleteMapping("/delete")
    public IResult deleteUserById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            userService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     *
     * @author : ls
     * @since : Create in 2021-04-09
     */
    @SaveLog(value = "系统用户VO分页数据接口", module = "系统用户管理")
    @IPermissions(value = "user:page:vo")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "系统用户VO分页数据接口", notes = "user:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<UserVO>> getUserVoPage(UserQuery query) {
        AssociationQuery<UserVO> associationQuery = new AssociationQuery<>(UserVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取系统用户VO
     *
     * @author : ls
     * @since : Create in 2021-04-09
     */
    @SaveLog(value = "获取系统用户VO数据接口", module = "系统用户管理")
    @IPermissions(value = "user:get:vo")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "获取系统用户VO数据接口", notes = "user:get:vo")
    @GetMapping("/getVo")
    public IResult<UserVO> getUserVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<UserVO> associationQuery = new AssociationQuery<>(UserVO.class);
        return IResult.ok(associationQuery.getVo(id, new UserQuery()));
    }


    @SaveLog(value = "注册用户接口", module = "系统用户管理")
    @IPermissions(value = "user:post:register")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "注册用户接口", notes = "user:get:vo")
    @Transactional
    @PostMapping("/register")
    public IResult<UserVO> register(UserDto userDto) {
        QueryWrapper<User> qw = new QueryWrapper<User>().eq("username", userDto.getUsername());
        int count = userService.count(qw);
        if (count>0){
            throw  new MyRuntimeException("用户名已存在!!");
        }
        //保存用户信息
        User user = new User();
        BeanUtil.copyProperties(userDto,user);
        //企业信息保存
        EntInfo entInfo = new EntInfo();
        BeanUtil.copyProperties(userDto.getEntInfo(),entInfo);
        QueryWrapper<EntInfo> entQw = new QueryWrapper<EntInfo>().eq("social_credit_code", userDto.getEntInfo().getSocialCreditCode());
        boolean b = entInfoService.saveOrUpdate(entInfo, entQw);
        if (!b){
            throw  new MyRuntimeException("企业信息保存失败！,请检查统一社会信用代码是否填写正确或联系管理员");
        }
        //user设置回显id
        user.setEntId(entInfo.getId());
        user.setEnable(0);
        user.setOrgConstructId(4);
        user.setUserType("区县级");
        String acivCode = IdUtil.randomUUID();
        user.setActivityCode(acivCode);//激活验证码
        userService.save(user);
        ThreadFactory.excutor(()->rabbitProducer.emailRegister(user.getEmail(),user.getUsername(),acivCode));//发送注册验证邮箱
        return  IResult.ok("注册成功，请登录验证邮箱激活用户");
    }


    @SaveLog(value = "邮箱验证接口", module = "系统用户管理")
    @IPermissions(value = "user:get:register")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "邮箱验证接口", notes = "user:get:checkEmail")
    @GetMapping("/checkEmail;")
    public IResult checkEmail(@RequestParam("username") String userName,@RequestParam("acivCode")String acivCode) {
        QueryWrapper<User> qw = new QueryWrapper<User>().eq("username", userName).select("username","activity_code");
        User user = userService.getOne(qw);
        if (!acivCode.equals(user.getActivityCode())) {
           return IResult.fail("激活用户:"+userName+" 失败！ 激活码错误！" );
        }
        user.setEnable(1);
        userService.updateById(user);
        Boolean b = h3YjService.synUsersToH3(userName);
        if (!b){
            throw new MyRuntimeException("用户绑定H3失败！！");
        }
        return IResult.ok("激活用户:"+userName+" 成功！ 可以登录啦！" );
    }
}