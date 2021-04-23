package com.westcatr.emergency.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.westcatr.emergency.business.entity.MonitorNext;
import com.westcatr.emergency.business.mapper.MonitorNextMapper;
import com.westcatr.emergency.business.pojo.query.MonitorNextQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorNextVO;
import com.westcatr.emergency.business.service.MonitorNextService;
import com.westcatr.emergency.business.service.MonitorNextSrcInfoService;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 监测信息表---去重后 等待开启流程的检测信息表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-04-21
 */
@Service
public class MonitorNextServiceImpl extends ServiceImpl<MonitorNextMapper, MonitorNext> implements MonitorNextService {
    @Autowired
    MonitorNextSrcInfoService monitorNextSrcInfoService;
    @Override
    public IPage<MonitorNext> iPage(MonitorNextQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<MonitorNext>().create(query));
    }

    @Override
    public boolean iSave(MonitorNext param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(MonitorNext param) {
        return this.updateById(param);
    }

    @Override
    public MonitorNext iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }

    @Override
    public Boolean setMonitorNextStatuByInstanceId(String instanceId) {
        Boolean bo = baseMapper.setMonitorNextStatuByInstanceId(instanceId);
       return bo;
    }

    @Override
    public MonitorNextVO getInfoByInstanceId(String instanceId) {
        QueryWrapper<MonitorNext> qw = new QueryWrapper<MonitorNext>().eq("h3_instance_id", instanceId);
        MonitorNext monitorNext = this.getOne(qw);
        if (null==monitorNext){
            throw  new MyRuntimeException("没有查到对应监测信息!!");
        }
        MonitorNextVO vo = new MonitorNextVO();
        BeanUtil.copyProperties(monitorNext,vo);
        return vo;
    }


}
