package com.westcatr.emergency.config;

import com.westcatr.emergency.business.component.intercepter.LoginIntercepter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author lijiacheng
 * @Date 2021/4/2
 */
public class WebMvcConfig  extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
