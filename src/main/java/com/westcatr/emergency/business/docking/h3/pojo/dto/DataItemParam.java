package com.westcatr.emergency.business.docking.h3.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * DataItemParam
 */
@Data
public class DataItemParam implements Serializable {

    private static final long serialVersionUID = 1L;
    private String itemName;
    private Object itemValue;

    public DataItemParam() {
    }

    public DataItemParam(String itemName, Object itemValue) {
        this.itemName = itemName;
        this.itemValue = itemValue;
    }
}