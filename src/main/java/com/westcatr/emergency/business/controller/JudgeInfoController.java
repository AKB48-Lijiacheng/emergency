package com.westcatr.emergency.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.westcatr.emergency.business.entity.JudgeInfo;
import com.westcatr.emergency.business.pojo.query.JudgeInfoQuery;
import com.westcatr.emergency.business.pojo.vo.JudgeInfoVO;
import com.westcatr.emergency.business.service.JudgeInfoService;
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

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 *  JudgeInfo 控制器
 *   @author ls
 *  @since 2021-03-26
 */
@Validated
@Api(tags="研判信息表接口", description = "judgeInfo")
@Slf4j
@RestController
@RequestMapping("//judgeInfo")
public class JudgeInfoController {

    @Autowired
    private JudgeInfoService judgeInfoService;

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-26
     */
    @SaveLog(value="研判信息表分页数据接口", module="研判信息表管理")
    @IPermissions(value="judgeInfo:page")
    @ApiOperationSupport(order=1)
    @ApiOperation(value="研判信息表分页数据接口", notes="judgeInfo:page")
    @GetMapping("/page")
    public IResult<IPage<JudgeInfo>> getJudgeInfoPage(JudgeInfoQuery query) {
        return IResult.ok(judgeInfoService.iPage(query));
    }

    /**
     * 通过id获取研判信息表（1-4级，1：特别重大，红色；2：重大，橙色；3：较大，黄色；4：一般，蓝色;0：灰色 无 ）
     * @author : ls
     * @since : Create in 2021-03-26
     */
    @SaveLog(value="获取研判信息表数据接口", module="研判信息表管理")
    @IPermissions(value="judgeInfo:get")
    @ApiOperationSupport(order=2)
    @ApiOperation(value="获取研判信息表数据接口", notes="judgeInfo:get")
    @GetMapping("/get")
    public IResult<JudgeInfo> getJudgeInfoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        return IResult.ok(judgeInfoService.iGetById(id));
    }

    /**
     * 新增研判信息表（1-4级，1：特别重大，红色；2：重大，橙色；3：较大，黄色；4：一般，蓝色;0：灰色 无 ）
     * @author : ls
     * @since : Create in 2021-03-26
     */
    @SaveLog(value="新增研判信息表数据接口", level = 2, module="研判信息表管理")
    @IPermissions(value="judgeInfo:add")
    @ApiOperationSupport(order=3)
    @ApiOperation(value="新增研判信息表数据接口", notes="judgeInfo:add")
    @PostMapping("/add")
    public IResult addJudgeInfo(@RequestBody @Validated(Insert.class) JudgeInfo param) {
        return IResult.auto(judgeInfoService.iSave(param));
    }

    /**
     * 更新研判信息表（1-4级，1：特别重大，红色；2：重大，橙色；3：较大，黄色；4：一般，蓝色;0：灰色 无 ）
     * @author : ls
     * @since : Create in 2021-03-26
     */
    @SaveLog(value="更新研判信息表数据接口", level = 2, module="研判信息表管理")
    @IPermissions(value="judgeInfo:update")
    @ApiOperationSupport(order=4)
    @ApiOperation(value="更新研判信息表数据接口", notes="judgeInfo:update")
    @PostMapping("/update")
    public IResult updateJudgeInfoById(@RequestBody @Validated(Update.class) JudgeInfo param) {
        return IResult.auto(judgeInfoService.iUpdate(param));
    }

    /**
     * 通过id删除研判信息表（1-4级，1：特别重大，红色；2：重大，橙色；3：较大，黄色；4：一般，蓝色;0：灰色 无 ）
     * @author : ls
     * @since : Create in 2021-03-26
     */
    @SaveLog(value="删除研判信息表数据接口", level = 3, module="研判信息表管理")
    @IPermissions(value="judgeInfo:del")
    @ApiOperationSupport(order=5)
    @ApiOperation(value="删除研判信息表数据接口", notes="judgeInfo:del")
    @DeleteMapping("/delete")
    public IResult deleteJudgeInfoById(@NotBlank(message = "id不能为空") @RequestParam(value = "id") String id) {
        for (String s : id.split(COMMA)) {
            judgeInfoService.iRemove(Long.valueOf(s));
        }
        return IResult.ok();
    }

    /**
     * 获取分页列表
     * @author : ls
     * @since : Create in 2021-03-26
     */
    @SaveLog(value="研判信息表VO分页数据接口", module="研判信息表管理")
    @IPermissions(value="judgeInfo:page:vo")
    @ApiOperationSupport(order=6)
    @ApiOperation(value="研判信息表VO分页数据接口", notes="judgeInfo:page:vo")
    @GetMapping("/voPage")
    public IResult<IPage<JudgeInfoVO>> getJudgeInfoVoPage(JudgeInfoQuery query) {
        AssociationQuery<JudgeInfoVO> associationQuery = new AssociationQuery<>(JudgeInfoVO.class);
        return IResult.ok(associationQuery.voPage(query));
    }

    /**
     * 通过id获取研判信息表（1-4级，1：特别重大，红色；2：重大，橙色；3：较大，黄色；4：一般，蓝色;0：灰色 无 ）VO
     * @author : ls
     * @since : Create in 2021-03-26
     */
    @SaveLog(value="获取研判信息表VO数据接口", module="研判信息表管理")
    @IPermissions(value="judgeInfo:get:vo")
    @ApiOperationSupport(order=7)
    @ApiOperation(value="获取研判信息表VO数据接口", notes="judgeInfo:get:vo")
    @GetMapping("/getVo")
    public IResult<JudgeInfoVO> getJudgeInfoVoById(@NotNull(message = "id不能为空") @RequestParam(value = "id") Long id) {
        AssociationQuery<JudgeInfoVO> associationQuery = new AssociationQuery<>(JudgeInfoVO.class);
        return IResult.ok(associationQuery.getVo(id, new JudgeInfoQuery()));
    }

}