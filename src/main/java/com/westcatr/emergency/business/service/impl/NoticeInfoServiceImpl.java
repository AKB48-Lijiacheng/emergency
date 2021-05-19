package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.NoticeInfoQuery;
import com.westcatr.emergency.business.entity.NoticeInfo;
import com.westcatr.emergency.business.mapper.NoticeInfoMapper;
import com.westcatr.emergency.business.service.NoticeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 系统通知内容表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class NoticeInfoServiceImpl extends ServiceImpl<NoticeInfoMapper, NoticeInfo> implements NoticeInfoService {

    @Override
    public IPage<NoticeInfo> iPage(NoticeInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<NoticeInfo>().create(query));
    }

    @Override
    public boolean iSave(NoticeInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(NoticeInfo param) {
        return this.updateById(param);
    }

    @Override
    public NoticeInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
