package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.CountryQuery;
import com.westcatr.emergency.business.entity.Country;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 二级行政区架构 服务类
 * </p>
 *
 * @author ls
 * @since 2021-04-25
 */
public interface CountryService extends IService<Country> {

    IPage<Country> iPage(CountryQuery query);

    boolean iSave(Country param);

    boolean iUpdate(Country param);

    Country iGetById(Long id);

    boolean iRemove(Long id);
}
