package com.westcatr.emergency.business.docking.h3.dto;

import lombok.Data;

@Data
public class H3TimeDTO extends BaseDTO {
    private long Days;
    private long Ticks;
    private int Hours;
    private int Minutes;
    private int Seconds;
    private int Milliseconds;

}
