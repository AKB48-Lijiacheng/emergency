package com.westcatr.emergency.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.westcatr.emergency.business.entity.Country;
import com.westcatr.emergency.business.entity.EntInfo;
import com.westcatr.emergency.business.entity.MonitorNext;
import com.westcatr.emergency.business.mapper.MonitorNextMapper;
import com.westcatr.emergency.business.pojo.query.MonitorNextQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorNextVO;
import com.westcatr.emergency.business.service.CountryService;
import com.westcatr.emergency.business.service.EntInfoService;
import com.westcatr.emergency.business.service.MonitorNextService;
import com.westcatr.emergency.business.service.MonitorNextSrcInfoService;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private EntInfoService entInfoService;
    @Autowired
    private CountryService countryService;

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
    public Boolean setMonitorNextStatuByInstanceId(String instanceId,Integer statuNum) {
        Boolean bo = baseMapper.setMonitorNextStatuByInstanceId(instanceId,statuNum);
       return bo;
    }

    @Override
    public List<Map<Object, Object>> getMonitorNextCount() {
       return   this.baseMapper.getMonitorNextCount();

    }

    @Transactional
    @Override
    public Boolean addEntName(Long monitNextId, Long entId) {
        QueryWrapper<EntInfo> qw = new QueryWrapper<EntInfo>().eq("id", entId).select("id", "ent_name", "country_id");
        EntInfo entInfoQuery = entInfoService.getOne(qw);
        if (null==entInfoQuery) {
            throw new MyRuntimeException("没有此企业，请检查传入的企业是否正确！");
        }
        //吧相关企业添加到监测信息
        MonitorNext monitorNextSave = new MonitorNext();
        monitorNextSave.setId(monitNextId);
        monitorNextSave.setEnterpriseName(entInfoQuery.getEntName());
        monitorNextSave.setEntInfoId(entId);
        boolean b = this.updateById(monitorNextSave);
        if (!b) {
            throw new MyRuntimeException("相关企业添加失败，请联系管理员");
        }
        //去给县区增加攻击次数
        Long countryId = entInfoQuery.getCountryId();
        QueryWrapper<Country> countryQw = new QueryWrapper<Country>().eq("id", countryId).select("id", "warning_count");
        Country countryQuery = countryService.getOne(countryQw);
        if (null == countryQuery) {
            throw new MyRuntimeException("该企业没有绑定对应的区县");
        }
        countryQuery.setWarningCount(countryQuery.getWarningCount()+1);
        boolean update = countryService.updateById(countryQuery);
        if (!update) {
            throw new MyRuntimeException("区县增加告警次数失败！");
        }
        return true;
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
