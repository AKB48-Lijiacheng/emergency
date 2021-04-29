package com.westcatr.emergency.business.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.entity.EventInfo;
import com.westcatr.emergency.business.pojo.query.EventInfoQuery;
import com.westcatr.emergency.business.pojo.vo.EventInfoVO;
import com.westcatr.emergency.business.service.EventInfoService;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import com.westcatr.rd.base.acommon.annotation.Update;
import com.westcatr.rd.base.acommon.vo.IResult;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  EventInfo 控制器
 *   @author ls
 *  @since 2021-04-23
 */
@Validated
@Api(tags="事件信息表接口", description = "eventInfo")
@Slf4j
@RestController
@RequestMapping("//eventInfo")
public class EventInfoController {

    @Autowired
    private EventInfoService eventInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="事件信息表分页数据接口", module="事件信息表管理")
    @IPermissions(value="eventInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="事件信息表分页数据接口", notes="eventInfo:page")
    @GetMapping("/page")
    public IResult<IPage<EventInfo>> getEventInfoPage(EventInfoQuery query) {
        return IResult.ok(eventInfoService.iPage(query));
    }

    /**
     * 通过id获取事件信息表
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="获取事件信息表数据接口", module="事件信息表管理")
    @IPermissions(value="eventInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取事件信息表数据接口", notes="eventInfo:get")
    @GetMapping("/get")
    public IResult<EventInfo> getEventInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(eventInfoService.iGetById(id));
    }

    /**
     * 新增事件信息表
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="新增事件信息表数据接口", level = 2, module="事件信息表管理")
    @IPermissions(value="eventInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增事件信息表数据接口", notes="eventInfo:add")
    @PostMapping("/add")
    public IResult addEventInfo(@RequestBody @Validated(Insert.class) EventInfo param) {
        return IResult.auto(eventInfoService.iSave(param));
    }

    /**
     * 更新事件信息表
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="更新事件信息表数据接口", level = 2, module="事件信息表管理")
    @IPermissions(value="eventInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新事件信息表数据接口", notes="eventInfo:update")
    @PostMapping("/update")
    public IResult updateEventInfoById(@RequestBody @Validated(Update.class) EventInfo param) {
        return IResult.auto(eventInfoService.iUpdate(param));
    }

    /**
     * 通过id删除事件信息表
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="删除事件信息表数据接口", level = 3, module="事件信息表管理")
    @IPermissions(value="eventInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除事件信息表数据接口", notes="eventInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteEventInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            eventInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="事件信息表VO分页数据接口", module="事件信息表管理")
    @IPermissions(value="eventInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="事件信息表VO分页数据接口", notes="eventInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<EventInfoVO>> getEventInfoVoPage(EventInfoQuery query) {
        AssociationQuery<EventInfoVO> associationQuery = new AssociationQuery<>(EventInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取事件信息表VO
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="获取事件信息表VO数据接口", module="事件信息表管理")
    @IPermissions(value="eventInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取事件信息表VO数据接口", notes="eventInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<EventInfoVO> getEventInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<EventInfoVO> associationQuery = new AssociationQuery<>(EventInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new EventInfoQuery()));
    }


    /**
     * 事件图标查询接口
     * @author : ls
     * @since : Create in 2021-04-23
     */
    @SaveLog(value="事件月统计图查询接口", module="事件信息表管理")
    @IPermissions(value="eventInfo:get:vo")
    @ApiOperationSupport(order=8)
    @ApiOperation(value="事件月统计图查询接口", notes="eventInfo:get:vo")
    @GetMapping("/getEventsCountByMonth")
    public IResult getEventInfoVoById() {
        List<Map<Object,Object>> list = new LinkedList<>();
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            DateTime dateTime = DateUtil.offsetMonth(DateUtil.date(), -i);
            String format = DateUtil.format(dateTime, "yyyy-MM");
            map.put(format,null);
        }
  List<Map<Object,Object>> queryList  =eventInfoService.getEventsCount();
        for (Map<Object, Object> objectObjectMap : queryList) {
            Object months = objectObjectMap.get("months");
            Object num = objectObjectMap.get("num");
        map.replace(months,num);
        }
        for (Object o : map.keySet()) {
            Object value = map.get(o);
            Map<Object, Object> mapParam = new HashMap<>();
            mapParam.put(o,value);
            list.add(mapParam);
        }
        return IResult.ok(list);
        //todo
    }




}