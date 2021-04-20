package com.westcatr.emergency.business.docking.h3.dto.attachFileDto;

import lombok.Data;

/**
 * 附件文件信息Dto
 */
@Data
public class H3AttachFileInfoDto {

    private String contentType;

    private String name;

    private int size;
    
    private String url;
}