package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.AreaInfoQuery;
import com.westcatr.emergency.business.entity.AreaInfo;
import com.westcatr.emergency.business.mapper.AreaInfoMapper;
import com.westcatr.emergency.business.service.AreaInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 区县基本信息表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class AreaInfoServiceImpl extends ServiceImpl<AreaInfoMapper, AreaInfo> implements AreaInfoService {

    @Override
    public IPage<AreaInfo> iPage(AreaInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<AreaInfo>().create(query));
    }

    @Override
    public boolean iSave(AreaInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(AreaInfo param) {
        return this.updateById(param);
    }

    @Override
    public AreaInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
