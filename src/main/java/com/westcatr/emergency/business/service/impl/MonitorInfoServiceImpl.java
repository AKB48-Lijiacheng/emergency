package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.MonitorInfoQuery;
import com.westcatr.emergency.business.entity.MonitorInfo;
import com.westcatr.emergency.business.mapper.MonitorInfoMapper;
import com.westcatr.emergency.business.service.MonitorInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 监测信息表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-26
 */
@Service
public class MonitorInfoServiceImpl extends ServiceImpl<MonitorInfoMapper, MonitorInfo> implements MonitorInfoService {

    @Override
    public IPage<MonitorInfo> iPage(MonitorInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<MonitorInfo>().create(query));
    }

    @Override
    public boolean iSave(MonitorInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(MonitorInfo param) {
        return this.updateById(param);
    }

    @Override
    public MonitorInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
