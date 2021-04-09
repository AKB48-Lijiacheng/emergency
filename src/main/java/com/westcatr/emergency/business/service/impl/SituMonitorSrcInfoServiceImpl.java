package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.SituMonitorSrcInfoQuery;
import com.westcatr.emergency.business.entity.SituMonitorSrcInfo;
import com.westcatr.emergency.business.mapper.SituMonitorSrcInfoMapper;
import com.westcatr.emergency.business.service.SituMonitorSrcInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-04-08
 */
@Service
public class SituMonitorSrcInfoServiceImpl extends ServiceImpl<SituMonitorSrcInfoMapper, SituMonitorSrcInfo> implements SituMonitorSrcInfoService {

    @Override
    public IPage<SituMonitorSrcInfo> iPage(SituMonitorSrcInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<SituMonitorSrcInfo>().create(query));
    }

    @Override
    public boolean iSave(SituMonitorSrcInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(SituMonitorSrcInfo param) {
        return this.updateById(param);
    }

    @Override
    public SituMonitorSrcInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
