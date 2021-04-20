package com.westcatr.emergency;

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
    @Value("${h3.portal.bpm.address}")
    public   String h3bpmAddress;
    @Value("${h3.portal.address}")
    public String h3Address;





}
