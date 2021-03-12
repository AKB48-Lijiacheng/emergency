package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.IndustrialInfoQuery;
import com.westcatr.emergency.business.entity.IndustrialInfo;
import com.westcatr.emergency.business.mapper.IndustrialInfoMapper;
import com.westcatr.emergency.business.service.IndustrialInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 产业库大全 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class IndustrialInfoServiceImpl extends ServiceImpl<IndustrialInfoMapper, IndustrialInfo> implements IndustrialInfoService {

    @Override
    public IPage<IndustrialInfo> iPage(IndustrialInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<IndustrialInfo>().create(query));
    }

    @Override
    public boolean iSave(IndustrialInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(IndustrialInfo param) {
        return this.updateById(param);
    }

    @Override
    public IndustrialInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
