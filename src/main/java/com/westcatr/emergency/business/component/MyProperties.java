package com.westcatr.emergency.business.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lijiacheng
 * @Date 2021/4/7
 */
@Component
public class MyProperties {

    @Value("${sso.getUserInfo.url}")
  public   String  SsoGetUserInfoAddr;
}
