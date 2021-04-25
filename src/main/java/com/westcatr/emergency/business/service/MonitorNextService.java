package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.westcatr.emergency.business.entity.MonitorNext;
import com.westcatr.emergency.business.pojo.query.MonitorNextQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorNextVO;

/**
 * <p>
 * 监测信息表---去重后 等待开启流程的检测信息表 服务类
 * </p>
 *
 * @author ls
 * @since 2021-04-21
 */
public interface MonitorNextService extends IService<MonitorNext> {

    IPage<MonitorNext> iPage(MonitorNextQuery query);

    boolean iSave(MonitorNext param);

    boolean iUpdate(MonitorNext param);

    MonitorNext iGetById(Long id);

    boolean iRemove(Long id);

    Boolean setMonitorNextStatuByInstanceId(String instanceId,Integer statNum);

    MonitorNextVO getInfoByInstanceId(String instanceId);
}
