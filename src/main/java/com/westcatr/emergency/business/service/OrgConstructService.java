package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.OrgConstructQuery;
import com.westcatr.emergency.business.entity.OrgConstruct;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2021-04-26
 */
public interface OrgConstructService extends IService<OrgConstruct> {

    IPage<OrgConstruct> iPage(OrgConstructQuery query);

    boolean iSave(OrgConstruct param);

    boolean iUpdate(OrgConstruct param);

    OrgConstruct iGetById(Long id);

    boolean iRemove(Long id);
}
