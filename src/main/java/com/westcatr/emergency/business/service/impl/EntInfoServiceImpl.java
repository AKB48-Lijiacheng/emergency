package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.EntInfoQuery;
import com.westcatr.emergency.business.entity.EntInfo;
import com.westcatr.emergency.business.mapper.EntInfoMapper;
import com.westcatr.emergency.business.service.EntInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 企业信息表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class EntInfoServiceImpl extends ServiceImpl<EntInfoMapper, EntInfo> implements EntInfoService {

    @Override
    public IPage<EntInfo> iPage(EntInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<EntInfo>().create(query));
    }

    @Override
    public boolean iSave(EntInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(EntInfo param) {
        return this.updateById(param);
    }

    @Override
    public EntInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
