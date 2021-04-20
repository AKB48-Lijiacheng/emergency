package com.westcatr.emergency.business.docking.h3.dto;

public class H3TimeDTO extends BaseDTO {
    private long Days;
    private long Ticks;
    private int Hours;
    private int Minutes;
    private int Seconds;
    private int Milliseconds;

    public void setDays(long Days) {
        this.Days = Days;
    }

    public long getDays() {
        return Days;
    }

    public void setTicks(long Ticks) {
        this.Ticks = Ticks;
    }

    public long getTicks() {
        return Ticks;
    }

    public void setHours(int Hours) {
        this.Hours = Hours;
    }

    public int getHours() {
        return Hours;
    }

    public void setMinutes(int Minutes) {
        this.Minutes = Minutes;
    }

    public int getMinutes() {
        return Minutes;
    }

    public void setSeconds(int Seconds) {
        this.Seconds = Seconds;
    }

    public int getSeconds() {
        return Seconds;
    }

    public void setMilliseconds(int Milliseconds) {
        this.Milliseconds = Milliseconds;
    }

    public int getMilliseconds() {
        return Milliseconds;
    }

}
