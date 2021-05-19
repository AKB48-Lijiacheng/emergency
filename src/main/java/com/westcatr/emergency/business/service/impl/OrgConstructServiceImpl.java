package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.OrgConstructQuery;
import com.westcatr.emergency.business.entity.OrgConstruct;
import com.westcatr.emergency.business.mapper.OrgConstructMapper;
import com.westcatr.emergency.business.service.OrgConstructService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-04-26
 */
@Service
public class OrgConstructServiceImpl extends ServiceImpl<OrgConstructMapper, OrgConstruct> implements OrgConstructService {

    @Override
    public IPage<OrgConstruct> iPage(OrgConstructQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<OrgConstruct>().create(query));
    }

    @Override
    public boolean iSave(OrgConstruct param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(OrgConstruct param) {
        return this.updateById(param);
    }

    @Override
    public OrgConstruct iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
