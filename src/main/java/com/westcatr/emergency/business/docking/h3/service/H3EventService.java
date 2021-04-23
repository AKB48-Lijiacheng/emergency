package com.westcatr.emergency.business.docking.h3.service;

import com.westcatr.emergency.business.docking.h3.pojo.dto.attachFileDto.H3AttachFileInfoDto;
import com.westcatr.emergency.business.docking.h3.pojo.vo.EventFormVo;
import com.westcatr.emergency.business.docking.h3.pojo.vo.H3CommentVo;
import com.westcatr.emergency.business.service.MonitorNextService;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lijiacheng
 * @Date 2021/4/21
 */
@Service
@Slf4j
public class H3EventService {
    @Autowired
    JdbcTemplate h3JdbcTemplate;
    @Autowired
    MonitorNextService monitorNextService;


    /**
     * 获取事件流程的表单信息
     **/
    public EventFormVo getFlowFomDataByWorkItemId(String workItemId) {
        String sql = "SELECT i.ObjectID as instanceId,y.ObjectID AS bizId,o.name,i.StartTime,i.OriginatorName,i.SequenceNo,y.detailInfo,y.disposalTime,y.personCharge,y.disposalMethod,y.supporMechan " +
                "from ot_workitem w LEFT JOIN ot_instancecontext i on w.InstanceId=i.ObjectID " +
                "left JOIN i_eventflow  y on i.BizObjectId=y.ObjectID LEFT JOIN ot_organizationunit o on o.ObjectID=i.OrgUnit  where w.ObjectID=? ";
        Map<String, Object> map = h3JdbcTemplate.queryForMap(sql,workItemId);
        Object bizObjectId=  map.get("bizId");
        Object instanceId = map.get("instanceId");
        if (map==null){
            throw new MyRuntimeException("该待办流程id绑定的表单信息不存在！");
        }
        EventFormVo eventFormVo = new EventFormVo();
        eventFormVo.setBizObjectId(String.valueOf(bizObjectId));
        eventFormVo.setStartUserName((String) map.get("OriginatorName"));
        eventFormVo.setStartTime((Date) map.get("StartTime"));
        eventFormVo.setStartUserOrgan((String) map.get("Name"));
        eventFormVo.setSequenceNo((String) map.get("SequenceNo"));
        eventFormVo.setDetailInfo((String) map.get("detailInfo"));
        eventFormVo.setDisposalTime((Date) map.get("disposalTime"));
        eventFormVo.setPersonCharge((String) map.get("personCharge"));
        eventFormVo.setDisposalMethod((String) map.get("disposalMethod"));
        eventFormVo.setSupporMechan((String) map.get("supporMechan"));

        //设置审批意见
        String str="RemakeInfo";
        String commentSql="SELECT * from ot_comment c where c.InstanceId=? and DataField  IN ('"+str+"') ORDER BY CreatedTime ";
        List<H3CommentVo> commentList = h3JdbcTemplate.query(sql, new BeanPropertyRowMapper<>(H3CommentVo.class), instanceId);
        eventFormVo.setCommentTexts(commentList);


        //设置附件信息
        String fileSql="SELECT ObjectID,BizObjectId,BizObjectSchemaCode,CreatedBy,CreatedTime,Description,FileName,ContentType,FileName,ContentLength,DownloadUrl " +
                "as Url from ot_attachment a where a.BizObjectId=?  ORDER BY CreatedTime";
        List<H3AttachFileInfoDto> listFiles = h3JdbcTemplate.query(fileSql,new BeanPropertyRowMapper<>(H3AttachFileInfoDto.class),bizObjectId);
        eventFormVo.setAttachFilesInfo(listFiles);

        //设置绑定的监测信息
     /*   MonitorNext monitorNext = monitorNextService.getOne(new QueryWrapper<MonitorNext>().eq("h3_instance_id", instanceId));
        if (monitorNext==null){
            throw  new MyRuntimeException("获取对应监测信息失败,没有绑定该流程的监测信息");
        }
        eventFormVo.setMonitorNext(monitorNext);*/
        return eventFormVo;


    }
}
