package com.westcatr.emergency.business.docking.h3.pojo.dto.attachFileDto;

import lombok.Data;

/**
 * 附件文件信息Dto
 */
@Data
public class H3AttachFileInfoDto {
    private String ObjectID;
    private String BizObjectSchemaCode;
    private String CreatedBy;
    private String CreatedTime;
    private String Description;
    private String FileName;
    private String BizObjectId;
    private String ContentLength;
    private String ContentType;
    private String Content;
    private String DownloadUrl;
}