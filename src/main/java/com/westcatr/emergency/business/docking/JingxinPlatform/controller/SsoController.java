package com.westcatr.emergency.business.docking.JingxinPlatform.controller;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.westcatr.emergency.MyProperties;
import com.westcatr.emergency.business.docking.JingxinPlatform.pojo.dto.SsoToken;
import com.westcatr.emergency.business.docking.JingxinPlatform.pojo.dto.SsoUser;
import com.westcatr.emergency.business.docking.JingxinPlatform.service.SsoService;
import com.westcatr.emergency.business.entity.User;
import com.westcatr.emergency.business.service.UserService;
import com.westcatr.emergency.config.ThreadFactory;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import com.westcatr.rd.base.acommon.vo.IResult;
import com.westcatr.rd.base.authority.authority.domain.LoginDTO;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 大平台台单点登录
 * @author lijiacheng
 * @Date 2021/4/2
 */
@Validated
@Api(tags="大平台相关接口", description = "大平台相关接口")
@Slf4j
@RestController
@RequestMapping("/sso")
public class SsoController {
    @Autowired
    UserService userService;
    @Autowired
    MyProperties myProperties;
    @Autowired
    SsoService ssoService;
    //todo
    /**
     *单点登录
     * @author lijiacheng
     * @since  2021/4/6
     **/
    @SaveLog(value="大平台sso登录接口", module="大平台相关接口")
    @ApiOperation(value="大平台sso登录接口")
    @PostMapping ("/login")
    public  IResult  ssoLogin(@RequestBody SsoToken ssoToken){
        //通过token请求用户信息
        String ssoUserinfo = HttpRequest.get(myProperties.SsoGetUserInfoAddr).header(HttpHeaders.HOST, "netcaso.com")
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+ssoToken.getAccess_token())
                .execute().body();
        SsoUser ssoUser = JSONObject.parseObject(ssoUserinfo, SsoUser.class);
        QueryWrapper<User> qw = new QueryWrapper<User>().eq("sso_id", ssoUser.getId());
        User user = userService.getOne(qw);
        //如果他们那有用户,我们sso_id匹配不到，有可能我们这有这个用户，但是sso_id没有绑定。那么我们就用username再匹配一下
        if (null==user){
            qw = new QueryWrapper<User>().eq("username", ssoUser.getAccount());
            user = userService.getOne(qw);
        }
        if (null==user){//如果用username匹配还匹配不到那么说明我们这没有，那么就给他新建一个
            user= userService.createNewUserBySsoUser(ssoUser);
        }else {//如果有了,那么就给他sso_id绑定一下
            User bindingSsoId = new User();
            bindingSsoId.setId(user.getId());
            bindingSsoId.setSsoId(ssoUser.getId());
            userService.updateById(bindingSsoId);
        }
        if (user.getEnable()==0){
           throw new MyRuntimeException("用户状态非正常",400);
        }
       //异步去更新user的sso信息
        ThreadFactory.executorPool.execute(()-> {
            userService.updateUserBySsoId(ssoUser);
        });
        //用谢哥的登录控制器去登录获取token
        LoginDTO loginDTO = new LoginDTO();
         loginDTO.setUsername(user.getUsername());
        loginDTO.setPassword(user.getPassword());
        loginDTO.setSaveLogin(true);
        String token =ssoService.LoginAndgetToken(loginDTO);
        return IResult.ok(null,token);
    }


}
