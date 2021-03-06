package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.entity.MonitorInfo;
import com.westcatr.emergency.business.entity.SituMonitorSrcInfo;
import com.westcatr.emergency.business.pojo.dto.AddEntNameDto;
import com.westcatr.emergency.business.pojo.dto.MonitorDto;
import com.westcatr.emergency.business.pojo.query.MonitorInfoQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorInfoVO;
import com.westcatr.emergency.business.pojo.vo.MonitorSimilarDto;
import com.westcatr.emergency.business.service.MonitorInfoService;
import com.westcatr.emergency.business.service.SituMonitorSrcInfoService;
import com.westcatr.emergency.business.utils.CommonUtil;
import com.westcatr.emergency.business.utils.FileDownLoadUtil;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import com.westcatr.rd.base.acommon.annotation.Update;
import com.westcatr.rd.base.acommon.vo.IResult;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 * MonitorInfo 控制器
 *
 * @author ls
 * @since 2021-04-07
 */
@Validated
@Api(tags = "监测信息表接口", description = "monitorInfo")
@Slf4j
@RestController
@RequestMapping("//monitorInfo")
public class MonitorInfoController {

    @Autowired
    private MonitorInfoService monitorInfoService;
    @Autowired
    private SituMonitorSrcInfoService situMonitorSrcInfoService;


    /**
     * 获取分页列表
     *
     * @author : ls
     * @since : Create in 2021-04-07
     */
    @SaveLog(value = "监测信息表分页数据接口", module = "监测信息表管理")
    @IPermissions(value = "monitorInfo:page")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "监测信息表分页数据接口", notes = "monitorInfo:page")
    @GetMapping("/page")
    public IResult<IPage<MonitorInfo>> getMonitorInfoPage(MonitorInfoQuery query) {
        return IResult.ok(monitorInfoService.iPage(query));
    }

    /**
     * 通过id获取监测信息表
     *
     * @author : ls
     * @since : Create in 2021-04-07
     */
    @SaveLog(value = "获取监测信息表数据接口", module = "监测信息表管理")
    @IPermissions(value = "monitorInfo:get")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "获取监测信息表数据接口", notes = "monitorInfo:get")
    @GetMapping("/get")
    public IResult<MonitorInfo> getMonitorInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(monitorInfoService.iGetById(id));
    }

    /**
     * 新增监测信息表
     *
     * @author : ls
     * @since : Create in 2021-04-07
     */
    @SaveLog(value = "新增监测信息表数据接口", level = 2, module = "监测信息表管理")
    @IPermissions(value = "monitorInfo:add")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "新增监测信息表数据接口", notes = "monitorInfo:add")
    @PostMapping("/add")
    public IResult addMonitorInfo(@RequestBody @Validated(Insert.class) MonitorInfo param) {
        return IResult.auto(monitorInfoService.iSave(param));
    }

    /**
     * 更新监测信息表
     *
     * @author : ls
     * @since : Create in 2021-04-07
     */
    @SaveLog(value = "更新监测信息表数据接口", level = 2, module = "监测信息表管理")
    @IPermissions(value = "monitorInfo:update")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "更新监测信息表数据接口", notes = "monitorInfo:update")
    @PostMapping("/update")
    public IResult updateMonitorInfoById(@RequestBody @Validated(Update.class) MonitorInfo param) {
        return IResult.auto(monitorInfoService.iUpdate(param));
    }

    /**
     * 通过id删除监测信息表
     *
     * @author : ls
     * @since : Create in 2021-04-07
     */
    @SaveLog(value = "删除监测信息表数据接口", level = 3, module = "监测信息表管理")
    @IPermissions(value = "monitorInfo:del")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除监测信息表数据接口", notes = "monitorInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteMonitorInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            monitorInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     *
     * @author : ls
     * @since : Create in 2021-04-07
     */
    @SaveLog(value = "监测信息表VO分页数据接口", module = "监测信息表管理")
    @IPermissions(value = "monitorInfo:page:vo")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "监测信息表VO分页数据接口", notes = "monitorInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<MonitorInfoVO>> getMonitorInfoVoPage(MonitorInfoQuery query) {
        AssociationQuery<MonitorInfoVO> associationQuery = new AssociationQuery<>(MonitorInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取监测信息表VO
     *
     * @author : ls
     * @since : Create in 2021-04-07
     */
    @SaveLog(value = "获取监测信息表VO数据接口", module = "监测信息表管理")
    @IPermissions(value = "monitorInfo:get:vo")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "获取监测信息表VO数据接口", notes = "monitorInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<MonitorInfoVO> getMonitorInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<MonitorInfoVO> associationQuery = new AssociationQuery<>(MonitorInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new MonitorInfoQuery()));
    }


    /**
     * 分类管理界面
     *
     * @author : ls
     * @since : Create in 2021-03-26
     */
    @SaveLog(value = "分类管理查询接口", module = "监测信息表管理")
    @IPermissions(value = "monitorInfo:TypeManagement")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "分类管理", notes = "monitorInfo:TypeManagement")
    @GetMapping("/TypeManagement")
    public IResult<Page<MonitorInfo>> getTypeManagementVoPage(PageDTO pageDTO) {
        QueryWrapper<MonitorInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("judge_info_id").eq("is_handle", "1");
        return IResult.ok(monitorInfoService.page(pageDTO.page(), queryWrapper));
    }


//todo

    /**
     * @author lijiacheng
     * @since 2021/3/29
     **/
    @SaveLog(value = "导出文档表格", module = "监测信息表管理")
    @ApiOperation(value = "导出文档表格", notes = "monitorInfo:export")
    @ApiOperationSupport(order = 9)
    @PostMapping("/export")
    public void export(MonitorInfoQuery query, @RequestParam("type") String type, HttpServletResponse response, HttpServletRequest request) {
        AssociationQuery<MonitorInfoVO> associationQuery = new AssociationQuery<>(MonitorInfoVO.class);
        query.setSize(9999);
        List<MonitorInfoVO> records = associationQuery.voPage(query).getRecords();
        File file = monitorInfoService.buildDoc(type, records);
        FileDownLoadUtil.downloadSingleFile(file, request, response);
    }

    @SaveLog(value = "监测信息去重", module = "监测信息表管理")
    @ApiOperation(value = "监测信息去重", notes = "monitorInfo:duplicated")
    @ApiOperationSupport(order = 9)
    @PostMapping("/duplicated")
    public IResult duplicatedMonitor(@RequestBody @Validated MonitorDto dto) {
        String monitorNextId = monitorInfoService.duplicatedMonitor(dto);
        if (monitorNextId == null) {
            return IResult.fail("去重失败");
        }
        return IResult.ok("", monitorNextId);
    }

    @SaveLog(value = "查找可能重复的数据", module = "监测信息表管理")
    @ApiOperation(value = "查找可能重复的数据", notes = "monitorInfo:getSimiliar")
    @ApiOperationSupport(order = 9)
    @GetMapping("/getSimiliar/{monitorId}")
    public IResult getSimiliar(@PathVariable("monitorId") String id) {
        MonitorSimilarDto res = monitorInfoService.getSimiliar(id);
        return IResult.ok(res);
    }


    @SaveLog(value = "获取监测信息数据源", module = "监测信息表管理")
    @ApiOperation(value = "获取监测信息数据源", notes = "monitorInfo:srcDetail")
    @ApiOperationSupport(order = 9)
    @GetMapping("/srcDetail/{monitorId}")
    public IResult getMonitorSrc(@PathVariable("monitorId") String id) {
        MonitorInfo monitor = monitorInfoService.getById(id);
        SituMonitorSrcInfo monitorSrc = situMonitorSrcInfoService.getOne(new QueryWrapper<SituMonitorSrcInfo>().eq("src_id", monitor.getSituEventId()));
        return IResult.ok(monitorSrc);
    }


    @SaveLog(value = "监测信息添加相关企业字段", module = "监测信息表管理")
    @ApiOperation(value = "监测信息添加相关企业字段", notes = "monitorInfo:addEntName")
    @ApiOperationSupport(order = 9)
    @PostMapping("/addEntName")
    public IResult addEntName(@RequestBody@Validated AddEntNameDto addEntNameDto) {
        Long monitNextId = addEntNameDto.getId();
        Long entId = addEntNameDto.getId();
    Boolean b = monitorInfoService.addEntName(monitNextId,entId);
        return IResult.auto(b);
    }




    @SaveLog(value="预警信息处理月统计图查询接口", module="事件信息表管理")
    @IPermissions(value="eventInfo:get:getMonitorNextCountByMonth")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="预警信息处理月统计图查询接口", notes="eventInfo:get:getMonitorNextCountByMonth")
    @GetMapping("/getMonitorCountByMonth")
    public IResult getMonitorCountByMonth() {
        List<Map<Object, Object>> timeList = CommonUtil.getYearAgoMonthMap();
        List<Map<Object,Object>> queryList  =monitorInfoService.getMonitorCount();
        for (Map<Object, Object> mao : queryList) {
            Object time = mao.get("time");
            Object count = mao.get("count");
            for (Map<Object, Object> timeMap : timeList) {
                if (timeMap.get("time").equals(time)) {
                    timeMap.put("count", count);
                    break;
                }
            }

        }
        return IResult.ok(timeList);
        //todo
    }

}