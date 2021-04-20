package com.westcatr.emergency.business.docking.h3.controller;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.westcatr.emergency.business.docking.h3.dto.DataItemParam;
import com.westcatr.emergency.business.docking.h3.dto.attachFileDto.H3AttachFileInfoDto;
import com.westcatr.emergency.business.docking.h3.dto.entityDto.H3Organizationunit;
import com.westcatr.emergency.business.docking.h3.dto.entityDto.H3User;
import com.westcatr.emergency.business.docking.h3.dto.flowDto.H3BPMServiceResult;
import com.westcatr.emergency.business.docking.h3.dto.flowDto.H3FlowEndDTO;
import com.westcatr.emergency.business.docking.h3.dto.flowDto.H3FlowStartDto;
import com.westcatr.emergency.business.docking.h3.dto.flowDto.H3FlowSubmitDTO;
import com.westcatr.emergency.business.docking.h3.dto.formDto.H3PushFormDataDto;
import com.westcatr.emergency.business.docking.h3.dto.h3RetuenDto.H3Result;
import com.westcatr.emergency.business.docking.h3.vo.YjFormVo;
import com.westcatr.rd.base.acommon.vo.IResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * h3直接Api的接口
 *
 * @author lijiacheng
 * @Date 2021/4/13
 */
@Controller
public class H3ApiController {
    // H3系统编码
    private final String H3_SYSTEM_CODE = "H3";
    // H3系统密钥
    private final String H3_SECRET = "Authine";
    // H3流程模板编码
    private final String H3_YJ_WORKFLOWS = "yjlc2";
    // 应急平台管理员userCode
    private final String YJADMIN_USERCODE = "yjadmin";

    @Value("${h3.portal.bpm.address}")
    private String h3bpmAddress;
    @Value("${h3.portal.address}")
    private String h3Address;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JdbcTemplate h3JdbcTemplate;


    /**
     * 开启流程
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    public IResult startFlow(H3FlowStartDto startDto,String flowModelType) {
       String url = h3bpmAddress + "/west/newStartWorkFlow";
//        String url = h3bpmAddress + "/workflows/yjlc2//;
        Map<String, Object> postParameters = new HashMap<>();
        postParameters.put("systemCode",H3_SYSTEM_CODE);
       postParameters.put("secret",H3_SECRET);
        postParameters.put("workflowCode", flowModelType);
        postParameters.put("userCode", startDto.getUserCode());
        postParameters.put("finishStart", startDto.isFinishStart());
        postParameters.put("paramValues", startDto.getParamValues());
        Object json = JSONObject.parse(JSON.toJSONString(postParameters));
        //设置请求
        H3BPMServiceResult body = restTemplate.postForEntity(url, json, H3BPMServiceResult.class).getBody();
        return IResult.ok(body);
    }

    /**
     * h3提交表单内容在h3
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    public IResult saveFormDate(H3PushFormDataDto formDto) {
        String url = h3bpmAddress + "/bpm-api/itemvalues";
        formDto.setSystemCode(H3_SYSTEM_CODE);
        formDto.setBizObjectSchemaCode(H3_YJ_WORKFLOWS);
        formDto.setSecret(H3_SECRET);
        List<DataItemParam> dataItemParam = getDataItemParam(formDto.getFormDto());
        formDto.setKeyValues(dataItemParam);
        //设置请求
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entitySubmit = new HttpEntity<String>(JSON.toJSONString(formDto, SerializerFeature.WriteMapNullValue), headers);
        H3Result body = restTemplate.exchange(
                url, HttpMethod.PUT, entitySubmit, H3Result.class)
                .getBody();
        if (body.getCode() != 0) {
            return IResult.fail(body.getMsg());
        }
        //数据保存成功了 那么就去把表单模板和附件表进行绑定
        formDto.getFormDto().getAttachIds().forEach(fileId->{
            String sql="UPDATE ot_attachment o set o.BizObjectId ='"+ formDto.getBizObjectId()+"' where o.ObjectID='"+fileId+"'";
            h3JdbcTemplate.update(sql);
        });
        return IResult.ok(body.getData());
    }


    /**
     * h3提交流程
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    public IResult submitWorkflow(String userId, String workItemId) {
        String url = h3bpmAddress + "/bpm-api/workitems/submit/" + workItemId;
        H3FlowSubmitDTO flowSubmitDTO = new H3FlowSubmitDTO();
        flowSubmitDTO.setSystemCode(H3_SYSTEM_CODE);
        flowSubmitDTO.setSecret(H3_SECRET);
        flowSubmitDTO.setUserId(userId);
        flowSubmitDTO.setWorkItemId(workItemId);
        flowSubmitDTO.setCommentText("同意");
        //设置请求
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
     * h3结束流程
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    public IResult endWorkflow(String instanceId) {
        String url = h3bpmAddress + "/bpm-api/instances/finish/" + instanceId;
        H3FlowEndDTO endDTO = new H3FlowEndDTO();
        endDTO.setSystemCode(H3_SYSTEM_CODE);
        endDTO.setSecret(H3_SECRET);
        endDTO.setInstanceId(instanceId);
        //设置请求
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entitySubmit = new HttpEntity<String>(JSON.toJSONString(endDTO, SerializerFeature.WriteMapNullValue), headers);
        H3Result body = restTemplate.exchange(
                url, HttpMethod.PUT, entitySubmit, H3Result.class)
                .getBody();
        if (body.getCode() != 0) {
            return IResult.fail(body.getMsg());
        }
        return IResult.ok(body.getData());
    }

    /**
     * h3Yj获取组织
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    public IResult getOrgs() {
        String sql = "SELECT * from  ot_organizationunit where name like '%应急平台%' and name <> '应急平台'";
        List<H3Organizationunit> list = h3JdbcTemplate.query(sql, new BeanPropertyRowMapper<>(H3Organizationunit.class));
        return IResult.ok(list);
    }


    /**
     * h3Yj根据组织Id获取用户列表
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
            if (fieldValue != null) {
                DataItemParam dataItemParam = new DataItemParam(field.getName(), fieldValue);
                list.add(dataItemParam);
            }
        }
        return list;
    }

    public void getFlowFomDataById(String id,String flowModelType) {
        String sql = "SELECT ,i.OriginatorName ,y.CreatedTime ,o.`Name`,i.SequenceNo,y.TfDevCenter,y.EarlyWarnLevel,y.Approved,y.attachFileIds from i_yjlc y " +
                "LEFT JOIN ot_instancecontext i   on y.RunningInstanceId=i.ObjectID " +
                "LEFT JOIN ot_organizationunit o on o.ObjectID=i.OrgUnit\n where y.ObjectID='" + id + "'";
        Map<String, Object> map = h3JdbcTemplate.queryForMap(sql);
        YjFormVo yjFormVo = new YjFormVo();
        yjFormVo.setStartUserName((String) map.get("OriginatorName"));
        yjFormVo.setStartTime((Date) map.get("CreatedTime"));
        yjFormVo.setStartUserOrgan((String) map.get("Name"));
        yjFormVo.setSequenceNo((String) map.get("SequenceNo"));
        yjFormVo.setTfDevCenter((String) map.get("TfDevCenter"));
        yjFormVo.setEarlyWarnLevel((String) map.get("EarlyWarnLevel"));
        yjFormVo.setApproved((String) map.get("Approved"));
        yjFormVo.setRemakeInfos(null);
        //设置附件信息
        String fileSql = "SELECT ContentType,FileName,ContentLength,CONCAT( '/Portal/MyReadAttachment/Read?BizObjectSchemaCode="+flowModelType+ "&BizObjectID=',BizObjectId, '&AttachmentID=', ObjectID, '&OpenMethod=0&userName="
                + "admin" + "' ) Url FROM ot_attachment WHERE BizObjectId = '" +id+ "'";//username先写死 admin
        List<H3AttachFileInfoDto> listFiles = h3JdbcTemplate.query(fileSql, new RowMapper<H3AttachFileInfoDto>() {
            @Override
            public H3AttachFileInfoDto mapRow(ResultSet rs, int i) throws SQLException {
                H3AttachFileInfoDto fileInfoDto = new H3AttachFileInfoDto();
                fileInfoDto.setContentType(rs.getString("ContentType"));
                fileInfoDto.setName(rs.getString("FileName"));
                fileInfoDto.setSize(rs.getInt("ContentLength"));
                fileInfoDto.setUrl(rs.getString("Url"));
                return fileInfoDto;
            }
        });
        yjFormVo.setAttachFilesInfo(listFiles);


    }
/**
 * 上传附件
 **/
    public String uploadAttachFile(MultipartFile file) {
        // 设置请求头(注意会产生中文乱码)
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







