package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.CityQuery;
import com.westcatr.emergency.business.entity.City;
import com.westcatr.emergency.business.mapper.CityMapper;
import com.westcatr.emergency.business.service.CityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 市级架构 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-04-25
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Override
    public IPage<City> iPage(CityQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<City>().create(query));
    }

    @Override
    public boolean iSave(City param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(City param) {
        return this.updateById(param);
    }

    @Override
    public City iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
