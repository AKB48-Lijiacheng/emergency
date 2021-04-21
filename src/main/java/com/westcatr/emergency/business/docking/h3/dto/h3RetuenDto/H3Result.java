
package com.westcatr.emergency.business.docking.h3.dto.h3RetuenDto;

import lombok.Data;

/**
 * H3调用API统一返回实体
 */
@Data
public class H3Result {

    private Integer code;
    private String msg;
    private Object data;
}