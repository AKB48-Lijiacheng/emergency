package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.CountryQuery;
import com.westcatr.emergency.business.entity.Country;
import com.westcatr.emergency.business.mapper.CountryMapper;
import com.westcatr.emergency.business.service.CountryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 二级行政区架构 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-04-25
 */
@Service
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements CountryService {

    @Override
    public IPage<Country> iPage(CountryQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<Country>().create(query));
    }

    @Override
    public boolean iSave(Country param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(Country param) {
        return this.updateById(param);
    }

    @Override
    public Country iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
