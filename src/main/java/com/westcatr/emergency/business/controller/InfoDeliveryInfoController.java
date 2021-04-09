package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.entity.InfoDeliveryInfo;
import com.westcatr.emergency.business.pojo.query.InfoDeliveryInfoQuery;
import com.westcatr.emergency.business.pojo.vo.InfoDeliveryInfoVO;
import com.westcatr.emergency.business.service.InfoDeliveryInfoService;
import com.westcatr.emergency.business.utils.FileDownLoadUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.List;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  InfoDeliveryInfo 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="信息发布表接口", description = "infoDeliveryInfo")
@Slf4j
@RestController
@RequestMapping("//infoDeliveryInfo")
public class InfoDeliveryInfoController {

    @Autowired
    private InfoDeliveryInfoService infoDeliveryInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="信息发布表分页数据接口", module="信息发布表管理")
    @IPermissions(value="infoDeliveryInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="信息发布表分页数据接口", notes="infoDeliveryInfo:page")
    @GetMapping("/page")
    public IResult<IPage<InfoDeliveryInfo>> getInfoDeliveryInfoPage(InfoDeliveryInfoQuery query) {
        return IResult.ok(infoDeliveryInfoService.iPage(query));
    }

    /**
     * 通过id获取信息发布表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取信息发布表数据接口", module="信息发布表管理")
    @IPermissions(value="infoDeliveryInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取信息发布表数据接口", notes="infoDeliveryInfo:get")
    @GetMapping("/get")
    public IResult<InfoDeliveryInfo> getInfoDeliveryInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(infoDeliveryInfoService.iGetById(id));
    }

    /**
     * 新增信息发布表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增信息发布表数据接口", level = 2, module="信息发布表管理")
    @IPermissions(value="infoDeliveryInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增信息发布表数据接口", notes="infoDeliveryInfo:add")
    @PostMapping("/add")
    public IResult addInfoDeliveryInfo(@RequestBody @Validated(Insert.class) InfoDeliveryInfo param) {
        return IResult.auto(infoDeliveryInfoService.iSave(param));
    }

    /**
     * 更新信息发布表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新信息发布表数据接口", level = 2, module="信息发布表管理")
    @IPermissions(value="infoDeliveryInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新信息发布表数据接口", notes="infoDeliveryInfo:update")
    @PostMapping("/update")
    public IResult updateInfoDeliveryInfoById(@RequestBody @Validated(Update.class) InfoDeliveryInfo param) {
        return IResult.auto(infoDeliveryInfoService.iUpdate(param));
    }

    /**
     * 通过id删除信息发布表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除信息发布表数据接口", level = 3, module="信息发布表管理")
    @IPermissions(value="infoDeliveryInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除信息发布表数据接口", notes="infoDeliveryInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteInfoDeliveryInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            infoDeliveryInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="信息发布表VO分页数据接口", module="信息发布表管理")
    @IPermissions(value="infoDeliveryInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="信息发布表VO分页数据接口", notes="infoDeliveryInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<InfoDeliveryInfoVO>> getInfoDeliveryInfoVoPage(InfoDeliveryInfoQuery query) {
        AssociationQuery<InfoDeliveryInfoVO> associationQuery = new AssociationQuery<>(InfoDeliveryInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取信息发布表VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取信息发布表VO数据接口", module="信息发布表管理")
    @IPermissions(value="infoDeliveryInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取信息发布表VO数据接口", notes="infoDeliveryInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<InfoDeliveryInfoVO> getInfoDeliveryInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<InfoDeliveryInfoVO> associationQuery = new AssociationQuery<>(InfoDeliveryInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new InfoDeliveryInfoQuery()));
    }




    @SaveLog(value = "导出文档表格", module = "信息发布表管理")
    @ApiOperation(value = "导出文档表格", notes = "infoDeliveryInfo:export")
    @ApiOperationSupport(order = 8)
    @PostMapping("/export")
    public void export(InfoDeliveryInfoQuery query, @RequestParam("type") String type, HttpServletResponse response, HttpServletRequest request) {
        AssociationQuery<InfoDeliveryInfoVO> associationQuery = new AssociationQuery<>(InfoDeliveryInfoVO.class);
        query.setSize(9999);
        List<InfoDeliveryInfoVO> records = associationQuery.voPage(query).getRecords();
        File file = infoDeliveryInfoService.buildDoc(type,records);
        FileDownLoadUtil.downloadSingleFile(file, request, response);
    }

}