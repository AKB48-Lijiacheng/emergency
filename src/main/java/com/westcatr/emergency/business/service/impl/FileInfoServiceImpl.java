package com.westcatr.emergency.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.rd.base.bmybatisplusbootstarter.dto.PageDTO;
import com.westcatr.emergency.business.pojo.query.FileInfoQuery;
import com.westcatr.emergency.business.entity.FileInfo;
import com.westcatr.emergency.business.mapper.FileInfoMapper;
import com.westcatr.emergency.business.service.FileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.westcatr.rd.base.bmybatisplusbootstarter.wrapper.WrapperFactory;

/**
 * <p>
 * 文件信息 服务实现类
 * </p>
 *
 * @author ls
 * @since 2021-03-10
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

    @Override
    public IPage<FileInfo> iPage(FileInfoQuery query) {
        return this.page(PageDTO.page(query), new WrapperFactory<FileInfo>().create(query));
    }

    @Override
    public boolean iSave(FileInfo param) {
        return this.save(param);
    }

    @Override
    public boolean iUpdate(FileInfo param) {
        return this.updateById(param);
    }

    @Override
    public FileInfo iGetById(Long id) {
        return this.getById(id);
    }

    @Override
    public boolean iRemove(Long id) {
        return this.removeById(id);
    }
}
