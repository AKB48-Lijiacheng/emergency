package com.westcatr.emergency.business.controller;

import cn.hutool.core.io.resource.ClassPathResource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.entity.City;
import com.westcatr.emergency.business.entity.Country;
import com.westcatr.emergency.business.entity.EntInfo;
import com.westcatr.emergency.business.entity.User;
import com.westcatr.emergency.business.pojo.vo.MyInfoVo;
import com.westcatr.emergency.business.service.CityService;
import com.westcatr.emergency.business.service.CountryService;
import com.westcatr.emergency.business.service.EntInfoService;
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
import java.io.IOException;
import java.util.Properties;

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
    @Autowired
    EntInfoService entInfoService;
    @Autowired
    CountryService countryService;
    @Autowired
    CityService cityService;



    @ApiOperationSupport(order=2)
    @ApiOperation(value="根据token获取用户信息")
    @GetMapping("/getUserInfo")
    public IResult<UserInfoVO> getUserInfo(HttpServletRequest request){
        IUser user = authenticationProvider.getUser(request);
        MyInfoVo vo = new MyInfoVo();
        User userQuery = userService.getOne(new QueryWrapper<User>().eq("id", user.getId()));//数据库的
        BeanUtils.copyProperties(userQuery, vo);
        BeanUtils.copyProperties(user, vo);
        if (userQuery.getCityId()!=null){
            City city = cityService.getById(userQuery.getCityId());
            vo.setCity(city);

        }else if (userQuery.getCountryId()!=null){
            Country country = countryService.getById(userQuery.getCountryId());
            vo.setCountry(country);

        }else if (userQuery.getEntId()!=null){
            EntInfo ent = entInfoService.getById(userQuery.getEntId());
            vo.setEntInfo(ent);

        }
        return IResult.ok(vo);
    }


    public static void main(String[] args) throws IOException {
        ClassPathResource resource = new ClassPathResource("test.properties");
        Properties properties = new Properties();
        properties.load(resource.getStream());


    }
}
