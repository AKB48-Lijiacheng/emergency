package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.AccountInfoQuery;
import com.westcatr.emergency.business.entity.AccountInfo;
import com.westcatr.emergency.business.mapper.AccountInfoMapper;
import com.westcatr.emergency.business.service.AccountInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 账户信息表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements AccountInfoService {

    @Override
    public IPage<AccountInfo> iPage(AccountInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<AccountInfo>().create(query));
    }

    @Override
    public boolean iSave(AccountInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(AccountInfo param) {
        return this.updateById(param);
    }

    @Override
    public AccountInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
