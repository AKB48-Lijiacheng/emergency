package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.FileInfoQuery;
import com.westcatr.emergency.business.entity.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 文件信息 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
public interface FileInfoService extends IService<FileInfo> {

    IPage<FileInfo> iPage(FileInfoQuery query);

    boolean iSave(FileInfo param);

    boolean iUpdate(FileInfo param);

    FileInfo iGetById(Long id);

    boolean iRemove(Long id);
}
