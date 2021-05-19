package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.SupportMechanismInfoQuery;
import com.westcatr.emergency.business.entity.SupportMechanismInfo;
import com.westcatr.emergency.business.mapper.SupportMechanismInfoMapper;
import com.westcatr.emergency.business.service.SupportMechanismInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 技术支撑机构 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class SupportMechanismInfoServiceImpl extends ServiceImpl<SupportMechanismInfoMapper, SupportMechanismInfo> implements SupportMechanismInfoService {

    @Override
    public IPage<SupportMechanismInfo> iPage(SupportMechanismInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<SupportMechanismInfo>().create(query));
    }

    @Override
    public boolean iSave(SupportMechanismInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(SupportMechanismInfo param) {
        return this.updateById(param);
    }

    @Override
    public SupportMechanismInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
