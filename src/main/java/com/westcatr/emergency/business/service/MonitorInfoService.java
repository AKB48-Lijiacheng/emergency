package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.westcatr.emergency.business.entity.MonitorInfo;
import com.westcatr.emergency.business.pojo.dto.MonitorDto;
import com.westcatr.emergency.business.pojo.query.MonitorInfoQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorInfoVO;
import com.westcatr.emergency.business.pojo.vo.MonitorSimilarDto;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 监测信息表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-04-07
 */
public interface MonitorInfoService extends IService<MonitorInfo> {

    IPage<MonitorInfo> iPage(MonitorInfoQuery query);

    boolean iSave(MonitorInfo param);

    boolean iUpdate(MonitorInfo param);

    MonitorInfo iGetById(Long id);

    boolean iRemove(Long id);

    File buildDoc(String type, List<MonitorInfoVO> records);

    MonitorSimilarDto getSimiliar(String id);

    String duplicatedMonitor(MonitorDto dto);

    Boolean addEntName(Long monitInfoId, Long entId);

    List<Map<Object, Object>> getMonitorCount();

}
