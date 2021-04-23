package com.westcatr.emergency.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ramostear.captcha.HappyCaptcha;
import com.westcatr.emergency.business.entity.User;
import com.westcatr.emergency.business.service.UserService;
import com.westcatr.rd.base.acommon.domain.IUser;
import com.westcatr.rd.base.authority.authority.domain.LoginDTO;
import com.westcatr.rd.base.authority.authority.provider.impl.AbstractAuthenticationProviderImpl;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import com.westcatr.rd.base.bweb.util.HttpContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*/**
 * 重写谢哥登录方法
 * @author lijiacheng
 * @Date 2021/4/22
 */
@Component
public class MyAuthenticationProviderImpl extends AbstractAuthenticationProviderImpl {
    @Autowired
    UserService userService;

    @Override
    public String login(LoginDTO dto) {
        if (iSecurityProperties.isCaptcha()){
            if (StrUtil.isBlank(dto.getCode())){
                throw new MyRuntimeException("请输入验证码",400);
            }
            boolean flag = HappyCaptcha.verification(HttpContextUtil.getHttpServletRequest(),dto.getCode(),true);
            if (flag){
                HappyCaptcha.remove(HttpContextUtil.getHttpServletRequest());
            }else {
                throw new MyRuntimeException("验证码错误",400);
            }
        }
        if (dto.getClientCode()==null){
            dto.setClientCode(1);
        }
        if (dto.getSaveLogin()==null){
            dto.setSaveLogin(false);
        }
        User user = userService.getOne(new QueryWrapper<User>().eq("username", dto.getUsername()));
        if(null==user){
            throw new MyRuntimeException("用户名不存在！");
        }

        if (!passwordEncryption(dto.getPassword(), user.getPassword())&&null!=user.getSsoId()){
            throw new MyRuntimeException("改用户是大平台用户，请从大平台登录！");
        }
        IUser iUser = userAndPermissionProvider.getByUsername(dto.getUsername());
        return saveUserToCache(iUser, dto);
    }
}
