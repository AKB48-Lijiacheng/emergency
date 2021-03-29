package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.Util.FileDownLoadUtil;
import com.westcatr.emergency.business.entity.MonitorInfo;
import com.westcatr.emergency.business.pojo.Dto.ParamDto.DocDto;
import com.westcatr.emergency.business.pojo.query.MonitorInfoQuery;
import com.westcatr.emergency.business.pojo.vo.MonitorInfoVO;
import com.westcatr.emergency.business.service.MonitorInfoService;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 * MonitorInfo 控制器
 *
 * @author ls
 * @since 2021-03-26
 */
@Validated
@Api(tags = "监测信息表接口", description = "monitorInfo")
@Slf4j
@RestController
@EnableTransactionManagement
@RequestMapping("//monitorInfo")
public class MonitorInfoController {

    @Autowired
    private MonitorInfoService monitorInfoService;
    @Autowired
    JdbcTemplate jdbcTemplate;



    /**
     * 获取分页列表
     *
     * @author : ls
     * @since : Create in 2021-03-26
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
     * @since : Create in 2021-03-26
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
     * @since : Create in 2021-03-26
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
     * @since : Create in 2021-03-26
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
     * @since : Create in 2021-03-26
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
     * @since : Create in 2021-03-26
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
     * @since : Create in 2021-03-26
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
    @SaveLog(value = "分类管理界面", module = "监测信息表管理")
    @IPermissions(value = "monitorInfo:TypeManagement")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "分类管理界面", notes = "monitorInfo:TypeManagement")
    @GetMapping("/TypeManagement")
    public IResult<Page<MonitorInfo>> getTypeManagementVoPage(PageDTO pageDTO) {
        QueryWrapper<MonitorInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("judge_info_id").eq("is_handle", "1");
        return IResult.ok(monitorInfoService.page(pageDTO.page(), queryWrapper));
    }



    /**
     * @author lijiacheng
     * @since 2021/3/29
     **/
    @SaveLog(value = "导出文档表格", module = "监测信息表管理")
    @ApiOperation(value = "导出文档表格", notes = "monitorInfo:export")
    @ApiOperationSupport(order = 9)
    @PostMapping("/export")
    public void export(DocDto dto, HttpServletResponse response, HttpServletRequest request) {
        File file = monitorInfoService.buildDoc(dto);
        FileDownLoadUtil.downloadSingleFile(file, request, response);
    }
}