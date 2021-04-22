package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.MonitorNextQuery;
import com.westcatr.emergency.business.entity.MonitorNext;
import com.westcatr.emergency.business.mapper.MonitorNextMapper;
import com.westcatr.emergency.business.service.MonitorNextService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 监测信息表---去重后 等待开启流程的检测信息表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-04-21
 */
@Service
public class MonitorNextServiceImpl extends ServiceImpl<MonitorNextMapper, MonitorNext> implements MonitorNextService {

    @Override
    public IPage<MonitorNext> iPage(MonitorNextQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<MonitorNext>().create(query));
    }

    @Override
    public boolean iSave(MonitorNext param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(MonitorNext param) {
        return this.updateById(param);
    }

    @Override
    public MonitorNext iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
