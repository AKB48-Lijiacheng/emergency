package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.AreaInfoQuery;
import com.westcatr.emergency.business.entity.AreaInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 区县基本信息表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface AreaInfoService extends IService<AreaInfo> {

    IPage<AreaInfo> iPage(AreaInfoQuery query);

    boolean iSave(AreaInfo param);

    boolean iUpdate(AreaInfo param);

    AreaInfo iGetById(Long id);

    boolean iRemove(Long id);
}
