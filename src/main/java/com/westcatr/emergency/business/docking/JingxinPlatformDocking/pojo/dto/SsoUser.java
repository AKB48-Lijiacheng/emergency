package com.westcatr.emergency.business.docking.JingxinPlatformDocking.pojo.dto;

import lombok.Data;

/**
 * sso用户信息
 * @author lijiacheng
 * @Date 2021/4/7
 */
@Data
public class SsoUser {
    private String id;
    private String name;
    private String account;
    private String mobile;
    private String email;
    private String user_type;
    private String zoning_code;
    private String zoning_name;
    private String office;
    private String organization;
    private String user_icon;
    private String identity;
    private String legal_person;
    private String legal_person_mobile;
    private String address;
}
