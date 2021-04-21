package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.entity.User;
import com.westcatr.emergency.business.pojo.vo.MyInfoVo;
import com.westcatr.emergency.business.service.UserService;
import com.westcatr.rd.base.acommon.domain.IUser;
import com.westcatr.rd.base.acommon.vo.IResult;
import com.westcatr.rd.base.authority.authority.domain.UserInfoVO;
import com.westcatr.rd.base.authority.authority.provider.AuthenticationProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Validated
@Api(tags="用户信息接口", description = "user")
@Slf4j
@RestController
@RequestMapping("/login")
public class UserInfoController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationProvider authenticationProvider;



    @ApiOperationSupport(order=2)
    @ApiOperation(value="根据token获取用户信息")
    @GetMapping("/getUserInfo")
    public IResult<UserInfoVO> getUserInfo(HttpServletRequest request){
        IUser user = authenticationProvider.getUser(request);
        UserInfoVO vo = new MyInfoVo();
        User userDate = userService.getOne(new QueryWrapper<User>().eq("id", user.getId()));//数据库的
        BeanUtils.copyProperties(userDate, vo);
        BeanUtils.copyProperties(user, vo);
        return IResult.ok(vo);
    }
}
