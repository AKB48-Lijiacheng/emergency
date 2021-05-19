package com.westcatr.emergency.business.docking.h3.controller;

import com.westcatr.emergency.business.docking.h3.pojo.dto.FileDto;
import com.westcatr.emergency.business.docking.h3.pojo.dto.flowDto.H3FlowStartDto;
import com.westcatr.emergency.business.docking.h3.pojo.dto.formDto.H3PushFormDataDto;
import com.westcatr.emergency.business.docking.h3.pojo.dto.h3RetuenDto.H3Result;
import com.westcatr.emergency.business.docking.h3.service.H3YjService;
import com.westcatr.emergency.business.service.MonitorNextService;
import com.westcatr.rd.base.acommon.vo.IResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * h3的流程接口
 *
 * @author lijiacheng
 * @Date 2021/4/12
 */
@RestController
@Validated
@Api(tags = "H3预警流程接口")
@RequestMapping("/h3/yj")
@Slf4j
public class H3YJController {
    @Autowired
    H3ApiController h3ApiController;
    @Autowired
    RestTemplate restTemplate;
    @Value("${h3.portal.bpm.address}")
    private String h3bpmAddress;
    @Value("${h3.portal.address}")
    private String h3Address;
    // H3预警表单编码
    private final String H3_YJ_FORMCODE = "Syjlcjxw";
    // H3业务对象模式编码
    private final String BizObjectSchemaCode = "yjlcjxw";
    @Autowired
    MonitorNextService monitorNextService;
    @Autowired
    H3YjService h3YjService;
    @Autowired
    JdbcTemplate h3JdbcTemplate;


    /**
     * h3开启流程
     *
     * @author lijiacheng
     * @since 2021/4/14
     **/
    @ApiOperation(value = "h3预警流程开启接口")
    @PostMapping("/startFlow")
    public IResult startFlow(@RequestBody @Validated H3FlowStartDto flowStartDTO) {

        H3Result h3Result = h3ApiController.startFlow(flowStartDTO, BizObjectSchemaCode);
        Map<String,Object> data = (Map) h3Result.getData();
        String instanceId = (String) data.get("instanceId");
        monitorNextService.setMonitorNextStatuByInstanceId(instanceId,1);
        return IResult.ok(h3Result);
    }

    /**
     * h3提交流程
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    @ApiOperation(value = "h3预警流程提交接口")
    @PostMapping("/submitFlow")
    public IResult submitWorkflow(@RequestBody @Validated H3PushFormDataDto formDto) {
        IResult res = h3ApiController.saveFormDate(formDto, BizObjectSchemaCode);
        if (res.getStatus() == 200) {
            return h3ApiController.submitWorkflow(formDto.getUserId(),formDto.getWorkItemId());
        }

        return res;
    }

    /**
     * h3结束流程
     * @author lijiacheng
     * @since 2021/4/13
     **/
    @ApiOperation(value = "h3预警流程结束接口")
    @GetMapping("/endFlow/{instanceId}")
    public IResult endWorkflow(@PathVariable(value = "instanceId")String instanceId) {
        Boolean flag = h3ApiController.endWorkflow(instanceId);
        monitorNextService.setMonitorNextStatuByInstanceId(instanceId,2);
        return IResult.ok("结束流程成功:instanceId:"+instanceId);
    }





    //附件上传
    @ApiOperation(value = "h3附件上传")
    @PostMapping("/fileUpload")
    public IResult fileUpload(@RequestParam(value = "file", required = true) MultipartFile file) throws Exception {
        String id = h3ApiController.uploadAttachFile(file);
        String type = MimeTypeUtils.parseMimeType(file.getContentType()).getType();
        FileDto fileDto = new FileDto();
        fileDto.setId(id);
        fileDto.setFileName(file.getName());
        fileDto.setFileType(type);
        fileDto.setFileSize(file.getSize()/1024);
        fileDto.setUrl(id);
        return IResult.ok(fileDto);
    }

    //附件下载
    @ApiOperation(value = "h3附件下载")
    @GetMapping("/fileDown")
    public void fileDown(@RequestParam(value = "attachmentId", required = true) String attachmentId, HttpServletResponse response) throws Exception {
        ResponseEntity<Resource> responseEntity = restTemplate
                .getForEntity(h3Address+ "/file/down/Read?BizObjectSchemaCode=yjlc2&BizObjectID=&AttachmentID="
                        + attachmentId + "&OpenMethod=0", Resource.class);
        Resource resource = responseEntity.getBody();
        InputStream inputStream = resource.getInputStream();
        // 配置文件下载
        response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + resource.getFilename() + ";filename*=utf-8''"
                + URLEncoder.encode(URLDecoder.decode(resource.getFilename(), "utf-8"), "utf-8"));
        // 实现文件下载
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(inputStream);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    //同步用户数据到H3
    @ApiOperation(value = "同步用户数据到H3")
    @GetMapping("/synUsersToH3")
    public IResult synUserToH3(@RequestParam(value = "username",required = false)@ApiParam(value = "传username就同步单个，否则同步所有") String userName){
      boolean b= h3YjService.synUsersToH3(userName);
      return IResult.auto(b);

    }


}
