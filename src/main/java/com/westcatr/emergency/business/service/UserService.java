package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.UserQuery;
import com.westcatr.emergency.business.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface UserService extends IService<User> {

    IPage<User> iPage(UserQuery query);

    boolean iSave(User param);

    boolean iUpdate(User param);

    User iGetById(Long id);

    boolean iRemove(Long id);
}
