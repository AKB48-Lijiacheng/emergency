package com.westcatr.emergency.business.docking.h3.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.westcatr.emergency.business.docking.h3.pojo.dto.DataItemParam;
import com.westcatr.emergency.business.docking.h3.pojo.dto.attachFileDto.H3AttachFileInfoDto;
import com.westcatr.emergency.business.docking.h3.pojo.dto.entityDto.H3Organizationunit;
import com.westcatr.emergency.business.docking.h3.pojo.dto.entityDto.H3User;
import com.westcatr.emergency.business.docking.h3.pojo.dto.flowDto.*;
import com.westcatr.emergency.business.docking.h3.pojo.dto.formDto.H3PushFormDataDto;
import com.westcatr.emergency.business.docking.h3.pojo.dto.h3RetuenDto.H3Result;
import com.westcatr.emergency.business.docking.h3.pojo.vo.H3CommentVo;
import com.westcatr.emergency.business.docking.h3.pojo.vo.YjFormVo;
import com.westcatr.emergency.business.entity.MonitorNext;
import com.westcatr.emergency.business.entity.MonitorNextSrcInfo;
import com.westcatr.emergency.business.service.MonitorNextService;
import com.westcatr.emergency.business.service.MonitorNextSrcInfoService;
import com.westcatr.rd.base.acommon.vo.IResult;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.*;

/**
 * h3??????Api?????????
 *
 * @author lijiacheng
 * @Date 2021/4/13
 */
@Controller
@Slf4j
public class H3ApiController {
    // H3????????????
    private final String H3_SYSTEM_CODE = "H3";
    // H3????????????
    private final String H3_SECRET = "Authine";
    // H3??????????????????????????????
    private final String H3_YJ_WORKFLOWSCODE = "yjlcjxw";
    // H3????????????????????????
    private final String H3_EVENT_WORKFLOWSCode = "EventFlow";


    @Value("${h3.portal.bpm.address}")
    private String h3bpmAddress;
    @Value("${h3.portal.address}")
    private String h3Address;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JdbcTemplate h3JdbcTemplate;
    @Autowired
    MonitorNextService monitorNextService;
    @Autowired
    MonitorNextSrcInfoService monitorNextSrcInfoService;


    /**
     * ????????????
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    public H3Result startFlow(H3FlowStartDto startDto,String workFlowTpye) {
       String url = h3bpmAddress + "/workflows/"+workFlowTpye;
        H3StartPushInfo starInfo = new H3StartPushInfo();
        starInfo.setSystemCode(H3_SYSTEM_CODE);
        starInfo.setSecret(H3_SECRET);
        starInfo.setWorkflowCode(workFlowTpye);
        starInfo.setUserCode(startDto.getUserCode());
        starInfo.setFinishStart(true);
        String monitorNextId = startDto.getMonitorNextId();

        MonitorNext monitorNextQ= monitorNextService.getById(monitorNextId);
        QueryWrapper<MonitorNextSrcInfo> qw = new QueryWrapper<MonitorNextSrcInfo>().eq("id",monitorNextQ.getSituMonitorSrcId());
        MonitorNextSrcInfo srcQuery = monitorNextSrcInfoService.getOne(qw);
        List<DataItemParam> dataItemParam = getDataItemParam(startDto.getFormDto());
        String value=srcQuery.getSrcProvince()+"-"+srcQuery.getEventName()+ DateUtil.now();
        DataItemParam dateItem = new DataItemParam("Title",value);
        dataItemParam.add(dateItem);
        starInfo.setParamValues(dataItemParam);
        String json = JSON.toJSONString(starInfo, SerializerFeature.WriteMapNullValue);
        //????????????
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        H3Result body = restTemplate.exchange(url, HttpMethod.POST, entity, H3Result.class).getBody();
        if (body.getCode()!=0){
            throw new MyRuntimeException("??????h3???????????????????????????????????????????????????");
        }
        //?????????????????????????????????
        Map<String,Object> data = (Map) body.getData();
        String instanceId = (String) data.get("instanceId");
        String sql="SELECT BizObjectId from ot_instancecontext where ObjectID=?";
        //????????????????????? ???????????????????????????????????????????????????
        List<String> attachIds = startDto.getFormDto().getAttachIds();
        Map<String, Object> InstanceParam = h3JdbcTemplate.queryForMap(sql,instanceId);
        Object bizObjectId = InstanceParam.get("BizObjectId");
        if (null!=attachIds&&attachIds.size()>0) {
            attachIds.forEach(fileId -> {
                String fileSql = "UPDATE ot_attachment o set o.BizObjectId =? where o.ObjectID=?";
                h3JdbcTemplate.update(fileSql, bizObjectId,fileId);
            });
        }

        //??????????????????id??????????????????
        int num = monitorNextService.count(new QueryWrapper<MonitorNext>().eq("id", monitorNextId));
    if (num<1){
        throw  new MyRuntimeException("????????????id??????????????????????????????");
    }else {
        MonitorNext monitorNext = new MonitorNext();
        monitorNext.setId(Long.parseLong(monitorNextId));
        monitorNext.setH3InstanceId(instanceId);
       monitorNextService.updateById(monitorNext);
    }
        return body;
    }

    /**
     * h3?????????????????????h3
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    public IResult saveFormDate(H3PushFormDataDto formDto,String bizObjectSchemaCode) {
        String url = h3bpmAddress + "/itemvalues";
        formDto.setSystemCode(H3_SYSTEM_CODE);
        formDto.setBizObjectSchemaCode(bizObjectSchemaCode);
        formDto.setSecret(H3_SECRET);

        // ?????????????????????H3????????????
        H3PushInfoDTO h3PushInfoDTO = new H3PushInfoDTO();
        h3PushInfoDTO.setBizObjectSchemaCode(bizObjectSchemaCode); // ??????typename???????????????BIZ_OBJECT_SCHEMA_CODE
        h3PushInfoDTO.setBizObjectId(formDto.getBizObjectId());
        h3PushInfoDTO.setSecret(H3_SECRET);
        h3PushInfoDTO.setSystemCode(H3_SYSTEM_CODE);
        h3PushInfoDTO.setUserId(formDto.getUserId());
        List<DataItemParam> dataItemParam=new LinkedList<>();
        if (h3PushInfoDTO.getBizObjectSchemaCode().equalsIgnoreCase(H3_EVENT_WORKFLOWSCode)){
        dataItemParam = getDataItemParam(formDto.getEventFormDto());//????????????????????????
        }
        if (h3PushInfoDTO.getBizObjectSchemaCode().equalsIgnoreCase(H3_YJ_WORKFLOWSCODE)){
        dataItemParam = getDataItemParam(formDto.getFormDto());//???????????????????????????
        }

        h3PushInfoDTO.setKeyValues(dataItemParam);
        String jsonStr = JSON.toJSONString(h3PushInfoDTO, SerializerFeature.WriteMapNullValue);
        log.info("?????????json???:  " + jsonStr);
        //????????????
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entitySubmit = new HttpEntity<String>(jsonStr, headers);
        H3Result body = restTemplate.exchange(url, HttpMethod.PUT, entitySubmit, H3Result.class).getBody();
        if (body.getCode() != 0) {
            return IResult.fail(body.getMsg());
        }
        //????????????????????? ???????????????????????????????????????????????????
        List<String> attachIds =new ArrayList<>();
        if (h3PushInfoDTO.getBizObjectSchemaCode().equalsIgnoreCase(H3_YJ_WORKFLOWSCODE)){
        attachIds = formDto.getFormDto().getAttachIds();
        }
        if (h3PushInfoDTO.getBizObjectSchemaCode().equalsIgnoreCase(H3_EVENT_WORKFLOWSCode)){
            List<String> miitAttachment = formDto.getEventFormDto().getMiitAttachment();
            if (!CollUtil.isEmpty(miitAttachment)){
                attachIds.addAll(miitAttachment);
            }
            List<String> relevantAttachmen = formDto.getEventFormDto().getRelevantAttachmen();
            if (!CollUtil.isEmpty(relevantAttachmen)){
                attachIds.addAll(relevantAttachmen);
            }

        }

        if (null!=attachIds&&attachIds.size()>0) {
            attachIds.forEach(fileId -> {
                String sql = "UPDATE ot_attachment o set o.BizObjectId =? where o.ObjectID=?";
                h3JdbcTemplate.update(sql, formDto.getBizObjectId(),fileId);
            });
        }

        return IResult.ok(body.getData());
    }


    /**
     * h3????????????
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    public IResult submitWorkflow(String userId, String workItemId) {
        String url = h3bpmAddress + "/workitems/submit/" + workItemId;
        H3FlowSubmitDTO flowSubmitDTO = new H3FlowSubmitDTO();
        flowSubmitDTO.setSystemCode(H3_SYSTEM_CODE);
        flowSubmitDTO.setSecret(H3_SECRET);
        flowSubmitDTO.setUserId(userId);
        flowSubmitDTO.setWorkItemId(workItemId);
        flowSubmitDTO.setCommentText("??????");
        //????????????
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entitySubmit = new HttpEntity<String>(JSON.toJSONString(flowSubmitDTO, SerializerFeature.WriteMapNullValue), headers);
        H3Result body = restTemplate.exchange(
                url, HttpMethod.PUT, entitySubmit, H3Result.class)
                .getBody();
        if (body.getCode() != 0) {
            return IResult.fail(body.getMsg());
        }
        return IResult.ok(body.getData());
    }


    /**
     * h3????????????
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    public Boolean endWorkflow(String instanceId) {
        String url = h3bpmAddress + "/instances/finish/" + instanceId;
        H3FlowEndDTO endDTO = new H3FlowEndDTO();
        endDTO.setSystemCode(H3_SYSTEM_CODE);
        endDTO.setSecret(H3_SECRET);
        endDTO.setInstanceId(instanceId);
        //????????????
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entitySubmit = new HttpEntity<String>(JSON.toJSONString(endDTO, SerializerFeature.WriteMapNullValue), headers);
        H3Result body = restTemplate.exchange(
                url, HttpMethod.PUT, entitySubmit, H3Result.class)
                .getBody();
        if (body.getCode() != 0) {
           throw new MyRuntimeException("h3??????????????????!!!");
        }

        return true;
    }

    /**
     * h3Yj????????????
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    public List<H3Organizationunit> getOrgs() {
        String sql = "SELECT * from  ot_organizationunit where name like '%????????????%' and name <> '????????????'";
        List<H3Organizationunit> list = h3JdbcTemplate.query(sql, new BeanPropertyRowMapper<>(H3Organizationunit.class));
        return list;
    }


    /**
     * h3Yj????????????Id??????????????????
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    public IResult getUsersByOrgId(String orgId) {
        String sql = "SELECT * from  ot_user where ParentID='" + orgId + "'";
        List<H3User> list = h3JdbcTemplate.query(sql, new BeanPropertyRowMapper<>(H3User.class));
        return IResult.ok(list);
    }


    private List<DataItemParam> getDataItemParam(Object formDto) {
        Field[] fields = ReflectUtil.getFields(formDto.getClass());
        ArrayList<DataItemParam> list = new ArrayList<>();
        for (Field field : fields) {
            Object fieldValue = ReflectUtil.getFieldValue(formDto, field);
            if (fieldValue != null&&!fieldValue.equals("")) {
                DataItemParam dataItemParam = new DataItemParam(field.getName(), fieldValue);
                list.add(dataItemParam);
            }
        }
        return list;
    }

    public YjFormVo getFlowFomDataById(String workItemId) {
        String sql = "SELECT i.ObjectID as instanceId,y.ObjectID AS bizId,o.name,i.StartTime,i.OriginatorName,i.SequenceNo,y.Title,y.TfDevCenter,y.EarlyWarnLevel,y.approved,w.SheetCode,w.WorkflowCode,i.InstanceName from ot_workitem w " +
                "LEFT JOIN ot_instancecontext i on w.InstanceId=i.ObjectID left JOIN i_yjlcjxw  y on i.BizObjectId=y.ObjectID " +
                "LEFT JOIN ot_organizationunit o on o.ObjectID=i.OrgUnit  where w.ObjectID=? ";
       Map<String, Object> map = h3JdbcTemplate.queryForMap(sql,workItemId);
        Object bizObjectId=  map.get("bizId");
        Object instanceId = map.get("instanceId");
        String workflowCode = (String) map.get("WorkflowCode");
        if (map==null){
           throw new MyRuntimeException("???????????????id?????????????????????????????????");
       }

        YjFormVo yjFormVo = new YjFormVo();
       yjFormVo.setBizObjectId(String.valueOf(bizObjectId));
        yjFormVo.setStartUserName((String) map.get("OriginatorName"));
        yjFormVo.setStartTime((Date) map.get("StartTime"));
        yjFormVo.setStartUserOrgan((String) map.get("Name"));
        yjFormVo.setSequenceNo((String) map.get("SequenceNo"));
        yjFormVo.setTfDevCenter( String.valueOf(map.get("TfDevCenter")));
        yjFormVo.setEarlyWarnLevel(String.valueOf(map.get("EarlyWarnLevel")));
        yjFormVo.setApproved((String) map.get("Approved"));
        yjFormVo.setTitle((String) map.get("Title"));

        //??????????????????
        String commentSql="SELECT ObjectID,BizObjectSchemaCode,InstanceId,Text,OUName,UserID,BizObjectId from ot_comment c where c.InstanceId=?  ORDER BY CreatedTime ";
        List<H3CommentVo> commentList = h3JdbcTemplate.query(sql, new BeanPropertyRowMapper<H3CommentVo>(H3CommentVo.class),instanceId);
        yjFormVo.setCommentTexts(commentList);

        //??????????????????
       String fileSql="SELECT ObjectID,BizObjectId,BizObjectSchemaCode,CreatedBy,CreatedTime,Description,FileName,ContentType,FileName,ContentLength,DownloadUrl " +
               "as Url from ot_attachment a where a.BizObjectId=?  ORDER BY CreatedTime";
        List<H3AttachFileInfoDto> listFiles = h3JdbcTemplate.query(fileSql,new BeanPropertyRowMapper<>(H3AttachFileInfoDto.class),bizObjectId);
        yjFormVo.setAttachFilesInfo(listFiles);

        //???????????????????????????
       /* MonitorNext monitorNext = monitorNextService.getOne(new QueryWrapper<MonitorNext>().eq("h3_instance_id", instanceId));
        if (monitorNext==null){
            throw  new MyRuntimeException("??????????????????????????????,????????????????????????????????????");
        }
        yjFormVo.setMonitorNext(monitorNext);*/

        return yjFormVo;
    }
/**
 * ????????????
 **/
    public String uploadAttachFile(MultipartFile file) {
        // ???????????????(???????????????????????????)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setConnection("Keep-Alive");
        headers.setCacheControl("no-cache");
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("MaxSize", "100");
        param.add("file", file.getResource());
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(param,
                headers);
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(h3Address + "/file/upload/UploadFile?IsMobile=false&fileid=" + UUID.randomUUID()
                        + "&MaxSize=1024000&SchemaCode=yjlc2", request, String.class);
        return JSON.parseObject(responseEntity.getBody()).getString("AttachmentId");
    }







}







