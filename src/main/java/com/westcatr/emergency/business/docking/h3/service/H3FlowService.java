package com.westcatr.emergency.business.docking.h3.service;


import cn.hutool.core.util.ReflectUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.westcatr.emergency.business.docking.h3.pojo.dto.attachFileDto.H3AttachFileInfoDto;
import com.westcatr.emergency.business.docking.h3.pojo.dto.h3RetuenDto.H3Result;
import com.westcatr.emergency.business.docking.h3.pojo.dto.h3RetuenDto.H3WorkItems;
import com.westcatr.emergency.business.docking.h3.pojo.query.H3WorkItemQuery;
import com.westcatr.emergency.business.docking.h3.pojo.vo.H3CommentVo;
import com.westcatr.emergency.business.docking.h3.pojo.vo.YjFormVo;
import com.westcatr.rd.base.acommon.vo.IResult;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lijiacheng
 * @Date 2021/4/17
 */
@Service
@Slf4j
public class H3FlowService {
    // H3系统编码
    private final String H3_SYSTEM_CODE = "H3";
    // H3系统密钥
    private final String H3_SECRET = "Authine";
    @Value("${h3.portal.bpm.address}")
    private String h3bpmAddress;
    @Value("${h3.portal.address}")
    private String h3Address;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JdbcTemplate h3JdbcTemplate;

    /**
     * 获取待办任务
     **/
    public IResult getUnFinishWorkItems(H3WorkItemQuery h3WorkItemQuery, Integer page, Integer size) {
        if (size == null || size < 1) {
            size = 10;
        }
        if (page == null || page < 1) {
            page = 1;
        }
        h3WorkItemQuery.setSystemCode(H3_SYSTEM_CODE);
        h3WorkItemQuery.setSecret(H3_SECRET);
        h3WorkItemQuery.setStartIndex((page - 1) * size + 1);
        h3WorkItemQuery.setEndIndex(page * size);
        //拼接查询条件
        StringBuilder sb = new StringBuilder();
        Field[] fields = ReflectUtil.getFields(h3WorkItemQuery.getClass());
        for (Field field : fields) {
            Object value = ReflectUtil.getFieldValue(h3WorkItemQuery, field);
            if (null != value && !value.equals("")) {
                sb.append(field.getName() + "=" + value + "&");
            }
        }
        String queryParam = sb.substring(0, sb.length() - 2);
        String url = h3bpmAddress + "/workitems/unfinish/" + h3WorkItemQuery.getUserId() + "?" + queryParam;
        String res = HttpUtil.get(url);
        H3Result h3Result = JSONObject.parseObject(res, H3Result.class);
        if (h3Result.getCode() != 0) {
            throw new MyRuntimeException("获取待办任务失败！！");
        }

        Object data = h3Result.getData();
        List<H3WorkItems> list = new ArrayList<>();
        if (data != null) {
            list = JSONArray.parseArray(data.toString(), H3WorkItems.class);
        }
        Page<H3WorkItems> pageObj = new Page(page, size, list.size());
        pageObj.setRecords(list);
        return IResult.ok(pageObj);

    }

    /**
     * 获取已办任务
     **/
    public IResult getFinishWorkItems(H3WorkItemQuery h3WorkItemQuery, Integer page, Integer size) {
        if (size == null || size < 1) {
            size = 10;
        }
        if (page == null || page < 1) {
            page = 1;
        }
        h3WorkItemQuery.setSystemCode(H3_SYSTEM_CODE);
        h3WorkItemQuery.setSecret(H3_SECRET);
        h3WorkItemQuery.setStartIndex((page - 1) * size + 1);
        h3WorkItemQuery.setEndIndex(page * size);
        //拼接查询条件
        StringBuilder sb = new StringBuilder();
        Field[] fields = ReflectUtil.getFields(h3WorkItemQuery.getClass());
        for (Field field : fields) {
            Object value = ReflectUtil.getFieldValue(h3WorkItemQuery, field);
            if (null != value && !value.equals("")) {
                sb.append(field.getName() + "=" + value + "&");
            }
        }
        String queryParam = sb.substring(0, sb.length() - 2);
        String url = h3bpmAddress + "/workitems/finish/" + h3WorkItemQuery.getUserId() + "?" + queryParam;
        String res = HttpUtil.get(url);
        H3Result h3Result = JSONObject.parseObject(res, H3Result.class);
        if (h3Result.getCode() != 0) {
            throw new MyRuntimeException("获取已办任务失败！！");
        }

        Object data = h3Result.getData();
        List<H3WorkItems> list = new ArrayList<>();
        if (data != null) {
            list = JSONArray.parseArray(data.toString(), H3WorkItems.class);
        }
        Page<H3WorkItems> pageObj = new Page(page, size, list.size());
        pageObj.setRecords(list);
        return IResult.ok(pageObj);
    }

    public String getParentInstanceId(String instanceId) {
        String sql = "SELECT ObjectID,ParentInstanceID from ot_instancecontext i WHERE i.ObjectID=? ";
        Map<String, Object> map = h3JdbcTemplate.queryForMap(sql, instanceId);
        String yjInstanceId = (String) map.get("ParentInstanceID");
        if (null == yjInstanceId) {
            throw new MyRuntimeException("没有查询到事件流程父流程预警实例Id");
        }
        return yjInstanceId;
    }

    public String getWorkItemsIdByInstanceId(String instanceId) {
        String  sql ="SELECT ObjectID,InstanceId from ot_workitem o where InstanceId=?";
        Map<String, Object> map = h3JdbcTemplate.queryForMap(sql,instanceId);
        String workItemsId = (String) map.get("ObjectID");//获取
        if (workItemsId==null){
            throw new MyRuntimeException("没有查到对应的待办任务id");
        }
        return workItemsId;
    }

    public YjFormVo getYjFormDataByInstanceId(String instanceId) {
        String sql = "SELECT  i.ObjectID as instanceId,y.ObjectID AS bizId,i.StartTime,o.name,i.OriginatorName,i.SequenceNo,y.TfDevCenter,y.EarlyWarnLevel,y.approved,i.InstanceName " +
                "from ot_instancecontext i LEFT JOIN i_yjlcjxw y on i.BizObjectId=y.ObjectID LEFT JOIN ot_organizationunit o on o.ObjectID=i.OrgUnit where i.ObjectID=?";
        Map<String, Object> map = h3JdbcTemplate.queryForMap(sql,instanceId);
        Object bizObjectId=  map.get("bizId");
        String workflowCode = (String) map.get("WorkflowCode");
        if (map==null){
            throw new MyRuntimeException("该待办流程id绑定的表单信息不存在！");
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

        //设置审批意见
        String str="RemakeInfo";
        String commentSql="SELECT * from ot_comment c where c.InstanceId=? and DataField  IN ('"+str+"') ORDER BY CreatedTime ";
        List<H3CommentVo> commentList = h3JdbcTemplate.query(sql, new BeanPropertyRowMapper<>(H3CommentVo.class), instanceId);
        if (commentList!=null){
            yjFormVo.setCommentTexts(commentList);
        }
        //设置附件信息
        String fileSql="SELECT ObjectID,BizObjectId,BizObjectSchemaCode,CreatedBy,CreatedTime,Description,FileName,ContentType,FileName,ContentLength,DownloadUrl " +
                "as Url from ot_attachment a where a.BizObjectId=?  ORDER BY CreatedTime";
        List<H3AttachFileInfoDto> listFiles = h3JdbcTemplate.query(fileSql,new BeanPropertyRowMapper<>(H3AttachFileInfoDto.class),bizObjectId);
        if (listFiles!=null){
            yjFormVo.setAttachFilesInfo(listFiles);
        }
        return yjFormVo;
    }
}
