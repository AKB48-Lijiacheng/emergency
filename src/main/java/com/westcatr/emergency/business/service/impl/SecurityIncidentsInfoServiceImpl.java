package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.SecurityIncidentsInfoQuery;
import com.westcatr.emergency.business.entity.SecurityIncidentsInfo;
import com.westcatr.emergency.business.mapper.SecurityIncidentsInfoMapper;
import com.westcatr.emergency.business.service.SecurityIncidentsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 应急处置与安全事件管理表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class SecurityIncidentsInfoServiceImpl extends ServiceImpl<SecurityIncidentsInfoMapper, SecurityIncidentsInfo> implements SecurityIncidentsInfoService {

    @Override
    public IPage<SecurityIncidentsInfo> iPage(SecurityIncidentsInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<SecurityIncidentsInfo>().create(query));
    }

    @Override
    public boolean iSave(SecurityIncidentsInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(SecurityIncidentsInfo param) {
        return this.updateById(param);
    }

    @Override
    public SecurityIncidentsInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
