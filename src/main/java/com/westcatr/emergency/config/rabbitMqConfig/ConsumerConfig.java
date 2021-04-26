package com.westcatr.emergency.config.rabbitMqConfig;

import com.alibaba.fastjson.JSONObject;
import com.westcatr.emergency.business.controller.componentController.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijiacheng
 * @Date 2021/4/16
 */
@Configuration
public class ConsumerConfig {
    @Autowired
    MailService mailService;

    /**
     * 找回密码
     **/
    @RabbitHandler
    @RabbitListener(queues = "sms-findPassword")
    public void findNextPersonSendMessage(String phoneNumber) {

    }

    /**
     * 注册用户发送邮箱
     **/
    @RabbitHandler
    @RabbitListener(queues = "email-register")
    public void emailCheck(String phoneNumber) {

    }


    /**
     * 注册发送邮箱验证
     **/
    @RabbitHandler
    @RabbitListener(queues = "sms-register")
    public void register(JSONObject jsonObject) {
        Object email = jsonObject.get("email");
        Object username = jsonObject.get("username");
        Object activCode = jsonObject.get("activCode");
       String url="localhost:7700/checkEmail?username="+username+"&activCode="+activCode;
        String str="请点击一下链接激活用户："+username+"！\n "+url+"\n";
        mailService.sendSimpleMail((String) email,"应急平台邮箱验证激活用户",str);
    }

}
