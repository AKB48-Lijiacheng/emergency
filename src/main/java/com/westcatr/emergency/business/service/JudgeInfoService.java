package com.westcatr.emergency.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westcatr.emergency.business.pojo.query.JudgeInfoQuery;
import com.westcatr.emergency.business.entity.JudgeInfo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 研判信息表（1-4级，1：特别重大，红色；2：重大，橙色；3：较大，黄色；4：一般，蓝色;0：灰色 无 ） 服务类
 * </p>
 *
 * @author ls
 * @since 2021-03-26
 */
public interface JudgeInfoService extends IService<JudgeInfo> {

    IPage<JudgeInfo> iPage(JudgeInfoQuery query);

    boolean iSave(JudgeInfo param);

    boolean iUpdate(JudgeInfo param);

    JudgeInfo iGetById(Long id);

    boolean iRemove(Long id);
}
