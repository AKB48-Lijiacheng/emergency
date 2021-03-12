package com.westcatr.emergency.business.pojo.query;

import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.QueryCondition;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.TimeDTO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="FileInfo查询对象", description="文件信息查询对象")
public class FileInfoQuery extends TimeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @QueryCondition
    private Long id;

    @ApiModelProperty(value = "文件名称")
    @QueryCondition
    private String fileName;

    @ApiModelProperty(value = "文件地址")
    @QueryCondition
    private String fileUrl;

    @ApiModelProperty(value = "文件存放地址")
    @QueryCondition
    private String filePath;

    @ApiModelProperty(value = "文件编号")
    @QueryCondition
    private String fileCode;

    @ApiModelProperty(value = "文件类型")
    @QueryCondition
    private Integer fileType;

    @ApiModelProperty(value = "文件长度")
    @QueryCondition
    private Long fileLength;

    @ApiModelProperty(value = "文件大小（中文）")
    @QueryCondition
    private String fileSize;

    @ApiModelProperty(value = "用户id")
    @QueryCondition
    private Long userId;

    @ApiModelProperty(value = "0，不可见（删除），1，不限制，2本人，3登录，4其他")
    @QueryCondition
    private Integer showState;

    @QueryCondition
    private Date createTime;

    @QueryCondition
    private Date updateTime;

    @ApiModelProperty(value = "文件后缀")
    @QueryCondition
    private String filePostfix;
}
