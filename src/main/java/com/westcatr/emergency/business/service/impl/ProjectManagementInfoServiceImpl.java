package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.ProjectManagementInfoQuery;
import com.westcatr.emergency.business.entity.ProjectManagementInfo;
import com.westcatr.emergency.business.mapper.ProjectManagementInfoMapper;
import com.westcatr.emergency.business.service.ProjectManagementInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 项目管理表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class ProjectManagementInfoServiceImpl extends ServiceImpl<ProjectManagementInfoMapper, ProjectManagementInfo> implements ProjectManagementInfoService {

    @Override
    public IPage<ProjectManagementInfo> iPage(ProjectManagementInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<ProjectManagementInfo>().create(query));
    }

    @Override
    public boolean iSave(ProjectManagementInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(ProjectManagementInfo param) {
        return this.updateById(param);
    }

    @Override
    public ProjectManagementInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
