package com.westcatr.emergency.business.docking.h3.service;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.westcatr.emergency.business.docking.h3.dto.h3RetuenDto.H3Result;
import com.westcatr.emergency.business.docking.h3.dto.h3RetuenDto.H3WorkItemsReturn;
import com.westcatr.emergency.business.docking.h3.query.H3WorkItemQuery;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
        if (size == null) {
            size=10;
        }
        if (page == null) {
            page=1;
        }
        h3WorkItemQuery.setSystemCode(H3_SYSTEM_CODE);
        h3WorkItemQuery.setSecret(H3_SECRET);
        h3WorkItemQuery.setStartIndex((page-1)*size+1);
        h3WorkItemQuery.setEndIndex(page*size);
        String url=h3bpmAddress+"/workitems/unfinish/"+h3WorkItemQuery.getUserId();
        JSONObject jsonObj= (JSONObject) JSONObject.toJSON(h3WorkItemQuery);
        String res = HttpUtil.get(url, jsonObj);
        H3Result h3Result = JSONObject.parseObject(res, H3Result.class);
        if (h3Result.getCode()!=0){
            log.info("获取待办任务失败！！！");
            return IResult.ok("获取待办任务失败!!");
        }

        List<H3WorkItemsReturn> list = new ArrayList<>();
        String data = h3Result.getData();
        if (data != null) {
            list = JSONArray.parseArray(data, H3WorkItemsReturn.class);
        }
        Page<H3WorkItemsReturn> pageObj = new Page(page,size,list.size());
        pageObj.setRecords(list);
        return IResult.ok(pageObj);

    }

    /**
     * 获取已办任务
     **/
    public IResult getFinishWorkItems(H3WorkItemQuery h3WorkItemQuery, Integer page, Integer size) {
        if (size == null) {
            size=10;
        }
        if (page == null) {
            page=1;
        }
        h3WorkItemQuery.setSystemCode(H3_SYSTEM_CODE);
        h3WorkItemQuery.setSecret(H3_SECRET);
        h3WorkItemQuery.setStartIndex((page-1)*size+1);
        h3WorkItemQuery.setEndIndex(page*size);
        String url=h3bpmAddress+"/workitems/finish/"+h3WorkItemQuery.getUserId();
        JSONObject jsonObj= (JSONObject) JSONObject.toJSON(h3WorkItemQuery);
        String res = HttpUtil.get(url, jsonObj);
        H3Result h3Result = JSONObject.parseObject(res, H3Result.class);
        if (h3Result.getCode()!=0){
            log.info("获取已办任务失败！！！");
            return IResult.ok("获取已办任务失败!!");
        }

        List<H3WorkItemsReturn> list = new ArrayList<>();
        String data = h3Result.getData();
        if (data != null) {
            list = JSONArray.parseArray(data, H3WorkItemsReturn.class);
        }
        Page<H3WorkItemsReturn> pageObj = new Page(page,size,list.size());
        pageObj.setRecords(list);
        return IResult.ok(pageObj);
    }
}
