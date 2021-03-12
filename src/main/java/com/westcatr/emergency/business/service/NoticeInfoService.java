package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.NoticeInfoQuery;
import com.westcatr.emergency.business.entity.NoticeInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 系统通知内容表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface NoticeInfoService extends IService<NoticeInfo> {

    IPage<NoticeInfo> iPage(NoticeInfoQuery query);

    boolean iSave(NoticeInfo param);

    boolean iUpdate(NoticeInfo param);

    NoticeInfo iGetById(Long id);

    boolean iRemove(Long id);
}
