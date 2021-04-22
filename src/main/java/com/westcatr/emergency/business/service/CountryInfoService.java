package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.CountryInfoQuery;
import com.westcatr.emergency.business.entity.CountryInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 二级行政区架构 服务类
 * </p>
 *
 * @author ls
 * @since 2021-04-21
 */
public interface CountryInfoService extends IService<CountryInfo> {

    IPage<CountryInfo> iPage(CountryInfoQuery query);

    boolean iSave(CountryInfo param);

    boolean iUpdate(CountryInfo param);

    CountryInfo iGetById(Long id);

    boolean iRemove(Long id);
}
