package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.AccountInfoQuery;
import com.westcatr.emergency.business.entity.AccountInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 账户信息表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface AccountInfoService extends IService<AccountInfo> {

    IPage<AccountInfo> iPage(AccountInfoQuery query);

    boolean iSave(AccountInfo param);

    boolean iUpdate(AccountInfo param);

    AccountInfo iGetById(Long id);

    boolean iRemove(Long id);
}
