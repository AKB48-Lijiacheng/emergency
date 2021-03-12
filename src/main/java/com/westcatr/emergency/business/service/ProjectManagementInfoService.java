package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.ProjectManagementInfoQuery;
import com.westcatr.emergency.business.entity.ProjectManagementInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 项目管理表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface ProjectManagementInfoService extends IService<ProjectManagementInfo> {

    IPage<ProjectManagementInfo> iPage(ProjectManagementInfoQuery query);

    boolean iSave(ProjectManagementInfo param);

    boolean iUpdate(ProjectManagementInfo param);

    ProjectManagementInfo iGetById(Long id);

    boolean iRemove(Long id);
}
