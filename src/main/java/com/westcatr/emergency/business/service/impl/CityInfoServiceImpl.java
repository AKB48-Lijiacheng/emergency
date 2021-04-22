package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.CityInfoQuery;
import com.westcatr.emergency.business.entity.CityInfo;
import com.westcatr.emergency.business.mapper.CityInfoMapper;
import com.westcatr.emergency.business.service.CityInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 市级架构 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-04-21
 */
@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo> implements CityInfoService {

    @Override
    public IPage<CityInfo> iPage(CityInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<CityInfo>().create(query));
    }

    @Override
    public boolean iSave(CityInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(CityInfo param) {
        return this.updateById(param);
    }

    @Override
    public CityInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
