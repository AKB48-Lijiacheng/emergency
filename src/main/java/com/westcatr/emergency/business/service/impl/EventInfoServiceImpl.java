package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.EventInfoQuery;
import com.westcatr.emergency.business.entity.EventInfo;
import com.westcatr.emergency.business.mapper.EventInfoMapper;
import com.westcatr.emergency.business.service.EventInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 事件信息表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-04-23
 */
@Service
public class EventInfoServiceImpl extends ServiceImpl<EventInfoMapper, EventInfo> implements EventInfoService {

    @Override
    public IPage<EventInfo> iPage(EventInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<EventInfo>().create(query));
    }

    @Override
    public boolean iSave(EventInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(EventInfo param) {
        return this.updateById(param);
    }

    @Override
    public EventInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
