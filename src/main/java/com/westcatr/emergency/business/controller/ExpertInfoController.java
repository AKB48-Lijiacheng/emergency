package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.ExpertInfoQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.rd.base.acommon.annotation.Insert;
import com.westcatr.rd.base.acommon.annotation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.westcatr.emergency.business.service.ExpertInfoService;
import com.westcatr.emergency.business.entity.ExpertInfo;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.westcatr.emergency.business.pojo.vo.ExpertInfoVO;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.AssociationQuery;
import io.swagger.annotations.ApiOperation;
import com.westcatr.rd.base.acommon.vo.IResult;
import lombok.extern.slf4j.Slf4j;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  ExpertInfo 控制器
 *   @author ls
 *  @since 2021-03-10
 */
@Validated
@Api(tags="专家库信息表接口", description = "expertInfo")
@Slf4j
@RestController
@RequestMapping("//expertInfo")
public class ExpertInfoController {

    @Autowired
    private ExpertInfoService expertInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="专家库信息表分页数据接口", module="专家库信息表管理")
    @IPermissions(value="expertInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="专家库信息表分页数据接口", notes="expertInfo:page")
    @GetMapping("/page")
    public IResult<IPage<ExpertInfo>> getExpertInfoPage(ExpertInfoQuery query) {
        return IResult.ok(expertInfoService.iPage(query));
    }

    /**
     * 通过id获取专家库信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取专家库信息表数据接口", module="专家库信息表管理")
    @IPermissions(value="expertInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取专家库信息表数据接口", notes="expertInfo:get")
    @GetMapping("/get")
    public IResult<ExpertInfo> getExpertInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(expertInfoService.iGetById(id));
    }

    /**
     * 新增专家库信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="新增专家库信息表数据接口", level = 2, module="专家库信息表管理")
    @IPermissions(value="expertInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增专家库信息表数据接口", notes="expertInfo:add")
    @PostMapping("/add")
    public IResult addExpertInfo(@RequestBody @Validated(Insert.class) ExpertInfo param) {
        return IResult.auto(expertInfoService.iSave(param));
    }

    /**
     * 更新专家库信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="更新专家库信息表数据接口", level = 2, module="专家库信息表管理")
    @IPermissions(value="expertInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新专家库信息表数据接口", notes="expertInfo:update")
    @PostMapping("/update")
    public IResult updateExpertInfoById(@RequestBody @Validated(Update.class) ExpertInfo param) {
        return IResult.auto(expertInfoService.iUpdate(param));
    }

    /**
     * 通过id删除专家库信息表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="删除专家库信息表数据接口", level = 3, module="专家库信息表管理")
    @IPermissions(value="expertInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除专家库信息表数据接口", notes="expertInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteExpertInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            expertInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="专家库信息表VO分页数据接口", module="专家库信息表管理")
    @IPermissions(value="expertInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="专家库信息表VO分页数据接口", notes="expertInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<ExpertInfoVO>> getExpertInfoVoPage(ExpertInfoQuery query) {
        AssociationQuery<ExpertInfoVO> associationQuery = new AssociationQuery<>(ExpertInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取专家库信息表VO
     * @author : ls
     * @since : Create in 2021-03-10
     */
    @SaveLog(value="获取专家库信息表VO数据接口", module="专家库信息表管理")
    @IPermissions(value="expertInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取专家库信息表VO数据接口", notes="expertInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<ExpertInfoVO> getExpertInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<ExpertInfoVO> associationQuery = new AssociationQuery<>(ExpertInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new ExpertInfoQuery()));
    }

}