package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.InfoDeliveryInfoQuery;
import com.westcatr.emergency.business.entity.InfoDeliveryInfo;
import com.westcatr.emergency.business.mapper.InfoDeliveryInfoMapper;
import com.westcatr.emergency.business.service.InfoDeliveryInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 信息发布表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class InfoDeliveryInfoServiceImpl extends ServiceImpl<InfoDeliveryInfoMapper, InfoDeliveryInfo> implements InfoDeliveryInfoService {

    @Override
    public IPage<InfoDeliveryInfo> iPage(InfoDeliveryInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<InfoDeliveryInfo>().create(query));
    }

    @Override
    public boolean iSave(InfoDeliveryInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(InfoDeliveryInfo param) {
        return this.updateById(param);
    }

    @Override
    public InfoDeliveryInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
