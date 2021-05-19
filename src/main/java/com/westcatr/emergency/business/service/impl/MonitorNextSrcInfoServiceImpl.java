package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.westcatr.emergency.business.entity.MonitorNextSrcInfo;
import com.westcatr.emergency.business.mapper.MonitorNextSrcInfoMapper;
import com.westcatr.emergency.business.pojo.query.MonitorNextSrcInfoQuery;
import com.westcatr.emergency.business.service.MonitorNextService;
import com.westcatr.emergency.business.service.MonitorNextSrcInfoService;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-04-23
 */
@Service
public class MonitorNextSrcInfoServiceImpl extends ServiceImpl<MonitorNextSrcInfoMapper, MonitorNextSrcInfo> implements MonitorNextSrcInfoService {

    @Autowired
    MonitorNextService monitorNextService;

    @Override
    public IPage<MonitorNextSrcInfo> iPage(MonitorNextSrcInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<MonitorNextSrcInfo>().create(query));
    }

    @Override
    public boolean iSave(MonitorNextSrcInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(MonitorNextSrcInfo param) {
        return this.updateById(param);
    }

    @Override
    public MonitorNextSrcInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }


}
