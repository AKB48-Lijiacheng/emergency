package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.EmergencySuppliesInfoQuery;
import com.westcatr.emergency.business.entity.EmergencySuppliesInfo;
import com.westcatr.emergency.business.mapper.EmergencySuppliesInfoMapper;
import com.westcatr.emergency.business.service.EmergencySuppliesInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 应急物资存储信息表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class EmergencySuppliesInfoServiceImpl extends ServiceImpl<EmergencySuppliesInfoMapper, EmergencySuppliesInfo> implements EmergencySuppliesInfoService {

    @Override
    public IPage<EmergencySuppliesInfo> iPage(EmergencySuppliesInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<EmergencySuppliesInfo>().create(query));
    }

    @Override
    public boolean iSave(EmergencySuppliesInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(EmergencySuppliesInfo param) {
        return this.updateById(param);
    }

    @Override
    public EmergencySuppliesInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
