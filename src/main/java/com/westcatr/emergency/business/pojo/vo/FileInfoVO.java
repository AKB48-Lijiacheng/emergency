package com.westcatr.emergency.business.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.westcatr.rd.base.bmybatisplusbootstarter.association.annotation.Select;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件信息
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_file_info")
@ApiModel(value="FileInfoVO对象", description="文件信息VO对象")
public class FileInfoVO implements Serializable {

    @Select(exist = false)
    private static final long serialVersionUID=1L;

    private Long id;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件地址")
    private String fileUrl;

    @ApiModelProperty(value = "文件存放地址")
    private String filePath;

    @ApiModelProperty(value = "文件编号")
    private String fileCode;

    @ApiModelProperty(value = "文件类型")
    private Integer fileType;

    @ApiModelProperty(value = "文件长度")
    private Long fileLength;

    @ApiModelProperty(value = "文件大小（中文）")
    private String fileSize;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "0，不可见（删除），1，不限制，2本人，3登录，4其他")
    private Integer showState;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "文件后缀")
    private String filePostfix;


}
