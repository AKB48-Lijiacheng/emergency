package com.westcatr.emergency.business.docking.h3.controller;

import com.westcatr.emergency.business.docking.h3.dto.formDto.H3PushFormDataDto;
import com.westcatr.emergency.business.docking.h3.dto.flowDto.H3FlowStartDto;
import com.westcatr.rd.base.acommon.vo.IResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

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


    // H3流程模板编码
    private final String H3_YJ_WORKFLOWS = "yjlc2";
    // 应急平台管理员userCode
    private final String YJADMIN_USERCODE = "yjadmin";

    /**
     * h3开启流程
     *
     * @author lijiacheng
     * @since 2021/4/14
     **/
    @ApiOperation(value = "h3预警流程开启接口")
    @PostMapping("/startFlow")
    public IResult startFlow(@RequestBody @Validated H3FlowStartDto flowStartDTO) {
        return h3ApiController.startFlow(flowStartDTO,H3_YJ_WORKFLOWS);

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
        IResult res = h3ApiController.saveFormDate(formDto);
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
    @GetMapping("/endFlow")
    public IResult endWorkflow(@NotNull String instanceId) {
        return h3ApiController.endWorkflow(instanceId);
    }


    /**
     * h3Yj获取组织
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    @ApiOperation(value = "h3Yj获取所有组织")
    @GetMapping("/getOrgs")
    public IResult getOrgs() {
        return h3ApiController.getOrgs();
    }

    /**
     * h3Yj根据组织Id获取用户列表
     *
     * @author lijiacheng
     * @since 2021/4/13
     **/
    @ApiOperation(value = "h3Yj获取用户列表，通过组织id")
    @GetMapping("/getUsersByOrgId")
    public IResult getUsersByOrgId(@RequestParam("id") String orgId) {
        return h3ApiController.getUsersByOrgId(orgId);
    }


    /**
     * todo
     * h3Yj获取流程表单信息
     * @author lijiacheng
     * @since 2021/4/13
     **/
    @ApiOperation(value = "h3Yj获取流程表单信息,通过表单流程id")
    @GetMapping("/getFlowFomData")
    public IResult getFlowFomDataById(@RequestParam("id") String id) {
        h3ApiController.getFlowFomDataById(id,H3_YJ_WORKFLOWS);
        return null;
    }

    //附件上传
    @ApiOperation(value = "h3Yj附件上传")
    @PostMapping("/fileUpload")
    public IResult fileUpload(@RequestParam(value = "file", required = true) MultipartFile file) throws Exception {
        return IResult.ok("成功", h3ApiController.uploadAttachFile(file));
    }

    //附件下载
    @ApiOperation(value = "h3Yj附件下载")
    @GetMapping("/fileDown")
    public void fileDown(@RequestParam(value = "attachmentId", required = true) String attachmentId, HttpServletResponse response) throws Exception {
        ResponseEntity<Resource> responseEntity = restTemplate
                .getForEntity(h3bpmAddress + "/file/down/Read?BizObjectSchemaCode=yjlc2&BizObjectID=&AttachmentID="
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


}
