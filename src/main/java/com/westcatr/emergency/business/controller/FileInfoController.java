package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.FileInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.FileInfoService;
import com.westcatr.emergency.business.entity.FileInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.FileInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  FileInfo 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="文件信息接口", description = "fileInfo")
@Slf4j
@RestController
@RequestMapping("//fileInfo")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="文件信息分页数据接口", module="文件信息管理")
    @IPermissions(value="fileInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="文件信息分页数据接口", notes="fileInfo:page")
    @GetMapping("/page")
    public IResult<IPage<FileInfo>> getFileInfoPage(FileInfoQuery query) {
        return IResult.ok(fileInfoService.iPage(query));
    }

    /**
     * 通过id获取文件信息
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取文件信息数据接口", module="文件信息管理")
    @IPermissions(value="fileInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取文件信息数据接口", notes="fileInfo:get")
    @GetMapping("/get")
    public IResult<FileInfo> getFileInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(fileInfoService.iGetById(id));
    }

    /**
     * 新增文件信息
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增文件信息数据接口", level = 2, module="文件信息管理")
    @IPermissions(value="fileInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增文件信息数据接口", notes="fileInfo:add")
    @PostMapping("/add")
    public IResult addFileInfo(@RequestBody @Validated(Insert.class) FileInfo param) {
        return IResult.auto(fileInfoService.iSave(param));
    }

    /**
     * 更新文件信息
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新文件信息数据接口", level = 2, module="文件信息管理")
    @IPermissions(value="fileInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新文件信息数据接口", notes="fileInfo:update")
    @PostMapping("/update")
    public IResult updateFileInfoById(@RequestBody @Validated(Update.class) FileInfo param) {
        return IResult.auto(fileInfoService.iUpdate(param));
    }

    /**
     * 通过id删除文件信息
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除文件信息数据接口", level = 3, module="文件信息管理")
    @IPermissions(value="fileInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除文件信息数据接口", notes="fileInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteFileInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            fileInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="文件信息VO分页数据接口", module="文件信息管理")
    @IPermissions(value="fileInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="文件信息VO分页数据接口", notes="fileInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<FileInfoVO>> getFileInfoVoPage(FileInfoQuery query) {
        AssociationQuery<FileInfoVO> associationQuery = new AssociationQuery<>(FileInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取文件信息VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取文件信息VO数据接口", module="文件信息管理")
    @IPermissions(value="fileInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取文件信息VO数据接口", notes="fileInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<FileInfoVO> getFileInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<FileInfoVO> associationQuery = new AssociationQuery<>(FileInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new FileInfoQuery()));
    }

}