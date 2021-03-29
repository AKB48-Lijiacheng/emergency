package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.ExpertInfoQuery;
import com.westcatr.emergency.business.entity.ExpertInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 专家库信息表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-26
 */
public interface ExpertInfoService extends IService<ExpertInfo> {

    IPage<ExpertInfo> iPage(ExpertInfoQuery query);

    boolean iSave(ExpertInfo param);

    boolean iUpdate(ExpertInfo param);

    ExpertInfo iGetById(Long id);

    boolean iRemove(Long id);
}
