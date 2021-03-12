package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.ProjectManagementInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.ProjectManagementInfoService;
import com.westcatr.emergency.business.entity.ProjectManagementInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.ProjectManagementInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  ProjectManagementInfo 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="项目管理表接口", description = "projectManagementInfo")
@Slf4j
@RestController
@RequestMapping("//projectManagementInfo")
public class ProjectManagementInfoController {

    @Autowired
    private ProjectManagementInfoService projectManagementInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="项目管理表分页数据接口", module="项目管理表管理")
    @IPermissions(value="projectManagementInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="项目管理表分页数据接口", notes="projectManagementInfo:page")
    @GetMapping("/page")
    public IResult<IPage<ProjectManagementInfo>> getProjectManagementInfoPage(ProjectManagementInfoQuery query) {
        return IResult.ok(projectManagementInfoService.iPage(query));
    }

    /**
     * 通过id获取项目管理表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取项目管理表数据接口", module="项目管理表管理")
    @IPermissions(value="projectManagementInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取项目管理表数据接口", notes="projectManagementInfo:get")
    @GetMapping("/get")
    public IResult<ProjectManagementInfo> getProjectManagementInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(projectManagementInfoService.iGetById(id));
    }

    /**
     * 新增项目管理表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增项目管理表数据接口", level = 2, module="项目管理表管理")
    @IPermissions(value="projectManagementInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增项目管理表数据接口", notes="projectManagementInfo:add")
    @PostMapping("/add")
    public IResult addProjectManagementInfo(@RequestBody @Validated(Insert.class) ProjectManagementInfo param) {
        return IResult.auto(projectManagementInfoService.iSave(param));
    }

    /**
     * 更新项目管理表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新项目管理表数据接口", level = 2, module="项目管理表管理")
    @IPermissions(value="projectManagementInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新项目管理表数据接口", notes="projectManagementInfo:update")
    @PostMapping("/update")
    public IResult updateProjectManagementInfoById(@RequestBody @Validated(Update.class) ProjectManagementInfo param) {
        return IResult.auto(projectManagementInfoService.iUpdate(param));
    }

    /**
     * 通过id删除项目管理表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除项目管理表数据接口", level = 3, module="项目管理表管理")
    @IPermissions(value="projectManagementInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除项目管理表数据接口", notes="projectManagementInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteProjectManagementInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            projectManagementInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="项目管理表VO分页数据接口", module="项目管理表管理")
    @IPermissions(value="projectManagementInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="项目管理表VO分页数据接口", notes="projectManagementInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<ProjectManagementInfoVO>> getProjectManagementInfoVoPage(ProjectManagementInfoQuery query) {
        AssociationQuery<ProjectManagementInfoVO> associationQuery = new AssociationQuery<>(ProjectManagementInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取项目管理表VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取项目管理表VO数据接口", module="项目管理表管理")
    @IPermissions(value="projectManagementInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取项目管理表VO数据接口", notes="projectManagementInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<ProjectManagementInfoVO> getProjectManagementInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<ProjectManagementInfoVO> associationQuery = new AssociationQuery<>(ProjectManagementInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new ProjectManagementInfoQuery()));
    }

}