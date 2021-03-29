package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.SafeWarnInfoQuery;
import com.westcatr.emergency.business.entity.SafeWarnInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 安全预警表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-29
 */
public interface SafeWarnInfoService extends IService<SafeWarnInfo> {

    IPage<SafeWarnInfo> iPage(SafeWarnInfoQuery query);

    boolean iSave(SafeWarnInfo param);

    boolean iUpdate(SafeWarnInfo param);

    SafeWarnInfo iGetById(Long id);

    boolean iRemove(Long id);
}
