package com.westcatr.emergency.business.controller.componentController;

import cn.hutool.core.bean.BeanUtil;
import com.westcatr.emergency.business.entity.MonitorInfo;
import com.westcatr.emergency.business.pojo.Dto.MonitorExcelDto;
import com.westcatr.emergency.business.pojo.vo.MonitorInfoVO;
import com.westcatr.emergency.business.pojo.vo.ParamDto.DocDto;
import com.westcatr.emergency.business.service.MonitorInfoService;
import com.westcatr.rd.base.acommon.annotation.IPermissions;
import com.westcatr.rd.base.acommon.annotation.SaveLog;
import com.westcatr.rd.base.acommon.vo.IResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Validated
@Api(tags="文档表格导出导入控制", description = "accountInfo")
@Slf4j
@RestController
@RequestMapping("/doc")
public class DocController {
    @Autowired
    MonitorInfoService monitorInfoService;
    @Autowired
    JdbcTemplate jdbcTemplate;


//todo
    @SaveLog(value="批量导出文档表格", module="文档表格导出导入控制")
    @IPermissions(value="monitorInfo:get:vo")
    @ApiOperation(value="批量导出文档表格")
    @GetMapping("/exportDoc")
    public IResult<MonitorInfoVO> getMonitorInfoVoById(DocDto dto) {

        List<MonitorInfo> list = monitorInfoService.list();
        List<MonitorExcelDto> monitorExcelDtoLists = new ArrayList<>();
        for (MonitorInfo m : list) {
            MonitorExcelDto monitorExcelDto = new MonitorExcelDto();
            BeanUtil.copyProperties(m,monitorExcelDto);
            monitorExcelDtoLists.add(monitorExcelDto);
        }




        return IResult.ok();
    }




}
