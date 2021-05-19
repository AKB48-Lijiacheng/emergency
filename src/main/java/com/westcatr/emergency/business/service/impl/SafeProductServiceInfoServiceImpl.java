package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.SafeProductServiceInfoQuery;
import com.westcatr.emergency.business.entity.SafeProductServiceInfo;
import com.westcatr.emergency.business.mapper.SafeProductServiceInfoMapper;
import com.westcatr.emergency.business.service.SafeProductServiceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 安全产品及服务表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class SafeProductServiceInfoServiceImpl extends ServiceImpl<SafeProductServiceInfoMapper, SafeProductServiceInfo> implements SafeProductServiceInfoService {

    @Override
    public IPage<SafeProductServiceInfo> iPage(SafeProductServiceInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<SafeProductServiceInfo>().create(query));
    }

    @Override
    public boolean iSave(SafeProductServiceInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(SafeProductServiceInfo param) {
        return this.updateById(param);
    }

    @Override
    public SafeProductServiceInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
