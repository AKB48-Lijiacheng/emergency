package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.CityInfoQuery;
import com.westcatr.emergency.business.entity.CityInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 市级架构 服务类
 * </p>
 *
 * @author ls
 * @since 2021-04-21
 */
public interface CityInfoService extends IService<CityInfo> {

    IPage<CityInfo> iPage(CityInfoQuery query);

    boolean iSave(CityInfo param);

    boolean iUpdate(CityInfo param);

    CityInfo iGetById(Long id);

    boolean iRemove(Long id);
}
