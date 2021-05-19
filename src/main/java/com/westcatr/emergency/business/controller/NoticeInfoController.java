package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.NoticeInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.NoticeInfoService;
import com.westcatr.emergency.business.entity.NoticeInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.NoticeInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  NoticeInfo 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="系统通知内容表接口", description = "noticeInfo")
@Slf4j
@RestController
@RequestMapping("//noticeInfo")
public class NoticeInfoController {

    @Autowired
    private NoticeInfoService noticeInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="系统通知内容表分页数据接口", module="系统通知内容表管理")
    @IPermissions(value="noticeInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="系统通知内容表分页数据接口", notes="noticeInfo:page")
    @GetMapping("/page")
    public IResult<IPage<NoticeInfo>> getNoticeInfoPage(NoticeInfoQuery query) {
        return IResult.ok(noticeInfoService.iPage(query));
    }

    /**
     * 通过id获取系统通知内容表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取系统通知内容表数据接口", module="系统通知内容表管理")
    @IPermissions(value="noticeInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取系统通知内容表数据接口", notes="noticeInfo:get")
    @GetMapping("/get")
    public IResult<NoticeInfo> getNoticeInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(noticeInfoService.iGetById(id));
    }

    /**
     * 新增系统通知内容表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增系统通知内容表数据接口", level = 2, module="系统通知内容表管理")
    @IPermissions(value="noticeInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增系统通知内容表数据接口", notes="noticeInfo:add")
    @PostMapping("/add")
    public IResult addNoticeInfo(@RequestBody @Validated(Insert.class) NoticeInfo param) {
        return IResult.auto(noticeInfoService.iSave(param));
    }

    /**
     * 更新系统通知内容表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新系统通知内容表数据接口", level = 2, module="系统通知内容表管理")
    @IPermissions(value="noticeInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新系统通知内容表数据接口", notes="noticeInfo:update")
    @PostMapping("/update")
    public IResult updateNoticeInfoById(@RequestBody @Validated(Update.class) NoticeInfo param) {
        return IResult.auto(noticeInfoService.iUpdate(param));
    }

    /**
     * 通过id删除系统通知内容表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除系统通知内容表数据接口", level = 3, module="系统通知内容表管理")
    @IPermissions(value="noticeInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除系统通知内容表数据接口", notes="noticeInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteNoticeInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            noticeInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="系统通知内容表VO分页数据接口", module="系统通知内容表管理")
    @IPermissions(value="noticeInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="系统通知内容表VO分页数据接口", notes="noticeInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<NoticeInfoVO>> getNoticeInfoVoPage(NoticeInfoQuery query) {
        AssociationQuery<NoticeInfoVO> associationQuery = new AssociationQuery<>(NoticeInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取系统通知内容表VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取系统通知内容表VO数据接口", module="系统通知内容表管理")
    @IPermissions(value="noticeInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取系统通知内容表VO数据接口", notes="noticeInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<NoticeInfoVO> getNoticeInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<NoticeInfoVO> associationQuery = new AssociationQuery<>(NoticeInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new NoticeInfoQuery()));
    }

}