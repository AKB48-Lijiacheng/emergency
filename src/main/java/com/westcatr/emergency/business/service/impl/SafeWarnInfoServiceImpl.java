package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.SafeWarnInfoQuery;
import com.westcatr.emergency.business.entity.SafeWarnInfo;
import com.westcatr.emergency.business.mapper.SafeWarnInfoMapper;
import com.westcatr.emergency.business.service.SafeWarnInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 安全预警表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-29
 */
@Service
public class SafeWarnInfoServiceImpl extends ServiceImpl<SafeWarnInfoMapper, SafeWarnInfo> implements SafeWarnInfoService {

    @Override
    public IPage<SafeWarnInfo> iPage(SafeWarnInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<SafeWarnInfo>().create(query));
    }

    @Override
    public boolean iSave(SafeWarnInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(SafeWarnInfo param) {
        return this.updateById(param);
    }

    @Override
    public SafeWarnInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
