package com.westcatr.emergency.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value="FileInfo对象", description="文件信息")
public class FileInfo extends Model<FileInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "文件名称")
    @TableField("file_name")
    private String fileName;

    @ApiModelProperty(value = "文件地址")
    @TableField("file_url")
    private String fileUrl;

    @ApiModelProperty(value = "文件存放地址")
    @TableField("file_path")
    private String filePath;

    @ApiModelProperty(value = "文件编号")
    @TableField("file_code")
    private String fileCode;

    @ApiModelProperty(value = "文件类型")
    @TableField("file_type")
    private Integer fileType;

    @ApiModelProperty(value = "文件长度")
    @TableField("file_length")
    private Long fileLength;

    @ApiModelProperty(value = "文件大小（中文）")
    @TableField("file_size")
    private String fileSize;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "0，不可见（删除），1，不限制，2本人，3登录，4其他")
    @TableField("show_state")
    private Integer showState;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "文件后缀")
    @TableField("file_postfix")
    private String filePostfix;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
