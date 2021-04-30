package com.westcatr.emergency.business.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.CreditCodeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.docking.h3.service.H3YjService;
import com.westcatr.emergency.business.entity.EntInfo;
import com.westcatr.emergency.business.entity.User;
import com.westcatr.emergency.business.pojo.dto.ParamDto.EmailDto;
import com.westcatr.emergency.business.pojo.dto.ResetPasswordDto;
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
import com.westcatr.rd.base.authority.authority.properties.ISecurityProperties;
import com.westcatr.rd.base.authority.authority.provider.AuthenticationProvider;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

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
@RequestMapping("/user")
public class UserController {
    @Autowired
    protected ISecurityProperties iSecurityProperties;
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
    @Autowired
    StringRedisTemplate stringRedisTemplate;

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
    public IResult<UserVO> register(@RequestBody@Validated UserDto userDto) {
        String socialCreditCode = userDto.getEntInfo().getSocialCreditCode();
        if (!CreditCodeUtil.isCreditCode(socialCreditCode)) {
            throw new MyRuntimeException("请填写正确的统一社会信用代码");
        }


        QueryWrapper<User> qw = new QueryWrapper<User>().eq("username", userDto.getUsername());
        int count = userService.count(qw);
        if (count>0){
            throw  new MyRuntimeException("该用户名已被注册!!");
        }
        //邮箱验证码进行校验
        Object activCode = stringRedisTemplate.opsForValue().get(userDto.getEmail());
        if (!userDto.getEmailActivCode().equals(activCode)){
            throw new MyRuntimeException("邮箱验证码错误");
        }
        //保存用户信息
        User user = new User();
        BeanUtil.copyProperties(userDto,user);
        user.setPassword(SecureUtil.md5(iSecurityProperties.getBeginSalt()+user.getPassword()+iSecurityProperties.getEndSalt()));
        //企业信息保存
        EntInfo entInfo = new EntInfo();
        BeanUtil.copyProperties(userDto.getEntInfo(),entInfo);
        QueryWrapper<EntInfo> entQw = new QueryWrapper<EntInfo>().eq("social_credit_code", socialCreditCode);
        boolean b = entInfoService.saveOrUpdate(entInfo, entQw);
        if (!b){
            throw  new MyRuntimeException("企业信息保存失败！,请检查统一社会信用代码是否填写正确或联系管理员");
        }
        //user设置企业用户和绑定类型
        user.setEntId(entInfo.getId());
        user.setEnable(1);
        user.setOrgConstructId(4);
        user.setUserType("企业级");
        boolean save = userService.save(user);
        if (!save){
            throw  new MyRuntimeException("用户注册失败！！！请联系管理员");
        }
        //绑定h3_id
        Boolean h3flag = h3YjService.synUsersToH3(user.getUsername());
        if (!h3flag){
            throw new MyRuntimeException("用户绑定H3失败！！");
        }
        //删除缓存
        stringRedisTemplate.opsForHash().delete(user.getUsername(),user.getEmail());
        return  IResult.ok("注册成功!!");
    }


    @SaveLog(value = "发送邮箱验证接口", module = "系统用户管理")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "发送邮箱验证接口", notes = "user:get:checkEmail")
    @PostMapping("/sendEmailCode")
    public IResult sendEmail(@RequestBody EmailDto emailDto) {
        String email = emailDto.getEmail();
        String acivCode = IdUtil.randomUUID();
        if (!Validator.isEmail(email)) {
            throw new MyRuntimeException("邮箱格式错误！");
        }
        stringRedisTemplate.opsForValue().set(email,acivCode);
        stringRedisTemplate.expire(email,5, TimeUnit.MINUTES);
        ThreadFactory.excutor(()->rabbitProducer.emailRegister(email,acivCode));//发送注册验证邮箱
        return IResult.ok("验证邮件已发送" );
    }

    @SaveLog(value = "找回密码邮箱验证", module = "系统用户管理")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "找回密码邮箱验证", notes = "user:post:findPassword")
    @PostMapping("/findPasswordEmailSend")
    public IResult findPasswordEmailSend(@RequestBody EmailDto emailDto) {
        String username = emailDto.getUsername();
        String email = emailDto.getEmail();
        if (!Validator.isEmail(email)) {
            throw new MyRuntimeException("邮箱格式错误！");
        }
        QueryWrapper<User> qw = new QueryWrapper<User>().eq("username", username);
        User user = userService.getOne(qw);
        if (user==null){
            throw  new MyRuntimeException("用户不存在");
        }
        if (email.equals(user.getEmail())){
            throw  new MyRuntimeException("邮箱错误，或未与用户绑定");
        }
        String acivCode = IdUtil.randomUUID();
        stringRedisTemplate.opsForHash().put(username,email,acivCode);
        stringRedisTemplate.expire(username,5, TimeUnit.MINUTES);
        ThreadFactory.excutor(()->rabbitProducer.EmailFindPassword(email,username,acivCode));//发送注册验证邮箱
        return IResult.ok("验证邮件已发送" );
    }


    @SaveLog(value = "重置密码", module = "系统用户管理")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "重置密码", notes = "user:post:resetPassword")
    @PostMapping("/resetPassword")
    public IResult resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        //邮箱验证码进行校验
        Object activCode = stringRedisTemplate.opsForHash().get(resetPasswordDto.getUsername(), resetPasswordDto.getEmail());
        if (!resetPasswordDto.getActivCode().equals(activCode)){
            throw new MyRuntimeException("邮箱验证码错误");
        }
        //重置密码
        String username = resetPasswordDto.getUsername();
        String password = resetPasswordDto.getPassword();
        User userUpdate = new User();
        userUpdate.setPassword(SecureUtil.md5(iSecurityProperties.getBeginSalt()+password+iSecurityProperties.getEndSalt()));
        UpdateWrapper<User> uw = new UpdateWrapper<User>().eq("username", username);
        userService.update(userUpdate,uw);

        return IResult.ok("密码已重置: 您的重置密码为："+password );
    }




}