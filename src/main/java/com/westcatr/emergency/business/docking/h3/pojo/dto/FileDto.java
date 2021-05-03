package com.westcatr.emergency.business.docking.h3.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 文件封装
 * </p>
 *
 * @author admin
 * @since 2020-05-15
 */
@Data

public class FileDto  {

    @ApiModelProperty(value = "返回的文件id")
    private String id;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件大小（kb）")
    private Long fileSize;

    @ApiModelProperty(value = "下载地址")
    private String url;



}
