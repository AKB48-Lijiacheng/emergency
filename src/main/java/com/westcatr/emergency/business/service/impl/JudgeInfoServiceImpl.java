package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.JudgeInfoQuery;
import com.westcatr.emergency.business.entity.JudgeInfo;
import com.westcatr.emergency.business.mapper.JudgeInfoMapper;
import com.westcatr.emergency.business.service.JudgeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 研判信息表（1-4级，1：特别重大，红色；2：重大，橙色；3：较大，黄色；4：一般，蓝色;0：灰色 无 ） 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-26
 */
@Service
public class JudgeInfoServiceImpl extends ServiceImpl<JudgeInfoMapper, JudgeInfo> implements JudgeInfoService {

    @Override
    public IPage<JudgeInfo> iPage(JudgeInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<JudgeInfo>().create(query));
    }

    @Override
    public boolean iSave(JudgeInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(JudgeInfo param) {
        return this.updateById(param);
    }

    @Override
    public JudgeInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
