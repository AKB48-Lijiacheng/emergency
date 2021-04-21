package com.westcatr.emergency.business.docking.h3.controller;

import com.westcatr.emergency.business.docking.h3.dto.flowDto.H3FlowStartDto;
import com.westcatr.emergency.business.docking.h3.dto.formDto.H3PushFormDataDto;
import com.westcatr.rd.base.acommon.vo.IResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * h3的流程接口
 *
 * @author lijiacheng
 * @Date 2021/4/12
 */
@RestController
@Api(tags = "H3事件流程器接口")
@RequestMapping("/h3/event")
public class H3EventController {
    @Autowired
    H3ApiController h3ApiController;
    // H3事件流程模板编码
    private final String H3_EVENT_WORKFLOWSCode = "EventFlow";
    // H3事件表单编码
    private final String H3_EVENT_FORMCODE = "SEventFlow";


    /**
     * h3提交流程
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    @ApiOperation(value = "h3事件流程提交接口")
    @PostMapping("/submitFlow")
    public IResult submitWorkflow(@RequestBody @Validated H3PushFormDataDto formDto) {
        IResult res = h3ApiController.saveFormDate(formDto,H3_EVENT_WORKFLOWSCode);
        if (res.getStatus() == 200) {
            return h3ApiController.submitWorkflow(formDto.getUserId(),formDto.getWorkItemId());
        }
        return res;
    }


}
