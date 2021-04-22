package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.CountryInfoQuery;
import com.westcatr.emergency.business.entity.CountryInfo;
import com.westcatr.emergency.business.mapper.CountryInfoMapper;
import com.westcatr.emergency.business.service.CountryInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 二级行政区架构 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-04-21
 */
@Service
public class CountryInfoServiceImpl extends ServiceImpl<CountryInfoMapper, CountryInfo> implements CountryInfoService {

    @Override
    public IPage<CountryInfo> iPage(CountryInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<CountryInfo>().create(query));
    }

    @Override
    public boolean iSave(CountryInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(CountryInfo param) {
        return this.updateById(param);
    }

    @Override
    public CountryInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
