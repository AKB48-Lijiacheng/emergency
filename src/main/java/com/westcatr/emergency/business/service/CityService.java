package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.CityQuery;
import com.westcatr.emergency.business.entity.City;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 市级架构 服务类
 * </p>
 *
 * @author ls
 * @since 2021-04-25
 */
public interface CityService extends IService<City> {

    IPage<City> iPage(CityQuery query);

    boolean iSave(City param);

    boolean iUpdate(City param);

    City iGetById(Long id);

    boolean iRemove(Long id);
}
