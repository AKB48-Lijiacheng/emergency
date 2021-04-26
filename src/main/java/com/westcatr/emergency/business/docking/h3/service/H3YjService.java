package com.westcatr.emergency.business.docking.h3.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.westcatr.emergency.business.docking.h3.controller.H3ApiController;
import com.westcatr.emergency.business.docking.h3.entity.H3User;
import com.westcatr.emergency.business.docking.h3.pojo.dto.entityDto.H3Organizationunit;
import com.westcatr.emergency.business.docking.h3.pojo.dto.h3RetuenDto.H3Result;
import com.westcatr.emergency.business.entity.OrgConstruct;
import com.westcatr.emergency.business.entity.User;
import com.westcatr.emergency.business.service.OrgConstructService;
import com.westcatr.emergency.business.service.UserService;
import com.westcatr.rd.base.bweb.exception.MyRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author lijiacheng
 * @Date 2021/4/26
 */
@Service
@Slf4j
public class H3YjService {
    @Autowired
    UserService userService;
    @Autowired
    RestTemplate restTemplate;
    @Value("${h3.portal.bpm.address}")
    public String h3bpmAddress;
    @Value("${h3.portal.address}")
    public String h3Address;
    @Autowired
    H3YjService h3YjService;
    @Autowired
    JdbcTemplate h3JdbcTemplate;
    @Autowired
    H3ApiController h3ApiController;
    @Autowired
    OrgConstructService orgConstructService;


    //合成方法：传username就走单个，否者同步所有
    public Boolean synUsersToH3(String userName) {
        if (StrUtil.isEmptyIfStr(userName)) {//如果空就同步所有
             synAllUsersToH3();
        } else {//如果否则同步单个用户
            QueryWrapper<User> qw = new QueryWrapper<User>().eq("user_name", userName);
            User one = userService.getOne(qw);
            if (one == null) {
                throw new MyRuntimeException("没有此用户，同步用户到H3失败");
            }
             synUserToH3(one);
        }
        return true;
    }




    //同步所有用户数据到h3,返回了H3userId并绑定到user
    private void synAllUsersToH3() {
        int up = 0;
        List<User> userList = userService.list();
        for (User user : userList) {
            synUserToH3(user);
            up++;
        }
        log.info("共更新了 "+up+" 个用户信息");
    }


    /**
     * 同步单个用户到H3，返回了H3userId并绑定到user
     **/
    private void synUserToH3(User user) {
        String username = user.getUsername();
        String sql = "SELECT  ObjectID,CODE from ot_user where  code=?";
        List<H3User> query = h3JdbcTemplate.query(sql, new BeanPropertyRowMapper(H3User.class), username);//为了避免异常用查List
        List<H3Organizationunit> h3Orgs = h3ApiController.getOrgs();
        String parentId = null;
        for (H3Organizationunit h3Org : h3Orgs) {
            if (h3Org.getName().contains(user.getUserType())) {
                parentId = h3Org.getObjectID();
            }
        }

        QueryWrapper<OrgConstruct> qw = new QueryWrapper<OrgConstruct>().eq("id", user.getOrgConstructId()).select("org_name");

        if (CollUtil.isEmpty(query)) {
            //插入
            H3User h3User = new H3User();
            h3User.setCode(username);
            h3User.setPassword("westcatr110,./");
            h3User.setParentID(parentId);
            h3User.setEmployeeRank(0);
            h3User.setModifier("9c342292-db6d-4236-867b-1450064845b0");//yjadmin的id
            h3User.setName(user.getFullName());
            h3User.setMobile(user.getPhone());
            h3User.setEmail(user.getEmail());
            String jsonStr = JSONObject.toJSONString(h3User);
            String url = h3Address + "/org-api/users";
            String body = HttpRequest.post(url).body(jsonStr, "application/json").execute().body();
            H3Result result = JSONObject.parseObject(body, H3Result.class);
            Object h3Userid = result.getData();
            User saveUser = new User();
            saveUser.setH3UserId(((String) h3Userid));
            saveUser.setId(user.getId());
            userService.updateById(saveUser);
            log.info("插入:" + jsonStr);
        } else {
            //更新
            String userId = query.get(0).getObjectID();
            H3User h3User = new H3User();
            h3User.setUserId(String.valueOf(userId));
            h3User.setEmployeeRank(0);
            h3User.setCode(username);
            h3User.setParentID(parentId);
            h3User.setName(user.getFullName());
            h3User.setMobile(user.getPhone());
            h3User.setEmail(user.getEmail());
            String jsonStr = JSONObject.toJSONString(h3User);
            String url = h3Address + "/org-api/users/" + userId;
            String body = HttpRequest.put(url).body(jsonStr, "application/json").execute().body();
            H3Result result = JSONObject.parseObject(body, H3Result.class);
            Object h3Userid = result.getData();
            User saveUser = new User();
            saveUser.setH3UserId(((String) h3Userid));
            saveUser.setId(user.getId());
            userService.updateById(saveUser);
            log.info("更新:" + jsonStr);
        }
    }
}
