package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.westcatr.emergency.business.entity.MonitorInfo;
import com.westcatr.emergency.business.pojo.query.MonitorInfoQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorInfoVO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 监测信息表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-26
 */
public interface MonitorInfoService extends IService<MonitorInfo> {

    @Cacheable(cacheNames = "cache-monitor-page")
    IPage<MonitorInfo> iPage(MonitorInfoQuery query);

    @CacheEvict("cache-monitor-page")
    boolean iSave(MonitorInfo param);

    @CacheEvict("cache-monitor-page")
    @CachePut(cacheNames = "cache-monitor-id",key = "#param.id")
    boolean iUpdate(MonitorInfo param);

    @Cacheable(cacheNames ="cache-monitor-id" )
    MonitorInfo iGetById(Long id);

    @CacheEvict({"cache-monitor-page","cache-monitor-id"})
    boolean iRemove(Long id);


    public File buildDoc(String type, List<MonitorInfoVO> records);


}
