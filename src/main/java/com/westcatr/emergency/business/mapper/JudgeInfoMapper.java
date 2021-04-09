package com.westcatr.emergency.business.mapper;

import com.westcatr.emergency.business.entity.JudgeInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 研判信息表（1-4级，1：特别重大，红色；2：重大，橙色；3：较大，黄色；4：一般，蓝色;0：灰色 无 ） Mapper 接口
 * </p>
 *
 * @author ls
 * @since 2021-03-26
 */
public interface JudgeInfoMapper extends BaseMapper<JudgeInfo> {

}
