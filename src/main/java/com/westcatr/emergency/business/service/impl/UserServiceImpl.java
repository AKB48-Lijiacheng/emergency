package com.westcatr.emergency.business.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.westcatr.emergency.business.docking.JingxinPlatformDocking.pojo.dto.SsoUser;
import com.westcatr.emergency.business.entity.User;
import com.westcatr.emergency.business.mapper.UserMapper;
import com.westcatr.emergency.business.pojo.query.UserQuery;
import com.westcatr.emergency.business.service.UserService;
import com.westcatr.rd.base.authority.authority.properties.ISecurityProperties;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-04-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    protected ISecurityProperties iSecurityProperties;

    @Override
    public IPage<User> iPage(UserQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<User>().create(query));
    }

    @Override
    public boolean iSave(User param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(User param) {
        return this.updateById(param);
    }

    @Override
    public User iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }

    @Override
    public void updateUserBySsoId(SsoUser ssoUser) {
        User user = new User();
        user.setSsoId(ssoUser.getId());
        user.setSsoUserIcon(ssoUser.getUser_icon());
        user.setSsoEmail(ssoUser.getEmail());
        user.setSsoUserType(ssoUser.getUser_type());
        user.setSsoAccount(ssoUser.getAccount());
        user.setSsoMobile(ssoUser.getMobile());
        user.setSsoName(ssoUser.getName());
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("sso_id",ssoUser.getId());
        this.update(user,qw);
    }

    @Override
    public User createNewUserBySsoUser(SsoUser ssoUser) {
        User user = new User();
        user.setUsername(ssoUser.getAccount());
        user.setPassword(SecureUtil.md5(iSecurityProperties.getBeginSalt()+"000000"+iSecurityProperties.getEndSalt()));
        user.setPhone(ssoUser.getMobile());
        user.setFullName(ssoUser.getName());
        user.setEnable(1);
        user.setSsoId(ssoUser.getId());
        user.setSsoUserIcon(ssoUser.getUser_icon());
        user.setSsoEmail(ssoUser.getEmail());
        user.setSsoUserType(ssoUser.getUser_type());
        user.setSsoName(ssoUser.getName());
        user.setSsoAccount(ssoUser.getAccount());
        user.setSsoMobile(ssoUser.getMobile());
        this.save(user);
        return user;
    }
}
