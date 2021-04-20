package com.westcatr.emergency.business.docking.JingxinPlatform.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * ssotoken封装
 * @author lijiacheng
 * @since  2021/4/6
 **/
@Data
public class SsoToken implements Serializable {
  String  access_token;
    String  token_type;
    String  expires_in;
    String  scope;
}
