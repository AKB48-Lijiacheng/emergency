package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.UserQuery;
import com.westcatr.emergency.business.entity.User;
import com.westcatr.emergency.business.mapper.UserMapper;
import com.westcatr.emergency.business.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

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
}
