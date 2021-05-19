package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.ExpertInfoQuery;
import com.westcatr.emergency.business.entity.ExpertInfo;
import com.westcatr.emergency.business.mapper.ExpertInfoMapper;
import com.westcatr.emergency.business.service.ExpertInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 专家库信息表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-26
 */
@Service
public class ExpertInfoServiceImpl extends ServiceImpl<ExpertInfoMapper, ExpertInfo> implements ExpertInfoService {

    @Override
    public IPage<ExpertInfo> iPage(ExpertInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<ExpertInfo>().create(query));
    }

    @Override
    public boolean iSave(ExpertInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(ExpertInfo param) {
        return this.updateById(param);
    }

    @Override
    public ExpertInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
