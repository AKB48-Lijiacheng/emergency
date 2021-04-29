package com.westcatr.emergency.config.rabbitMqConfig;

import com.alibaba.fastjson.JSONObject;
import com.westcatr.emergency.business.controller.componentController.MailService;
import com.westcatr.emergency.config.ThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijiacheng
 * @Date 2021/4/16
 */
@Configuration
@Slf4j
public class ConsumerConfig {
    @Autowired
    MailService mailService;
    String addr="localhost:7700";

    /**
     * 找回密码
     **/
    @RabbitHandler
    @RabbitListener(queues = "email-findPassword")
    public void findNextPersonSendMessage(JSONObject json) {
        Object email = json.get("email");
        Object username = json.get("username");
        Object activCode = json.get("activCode");
        String content="正在找回 "+username+" 用户的密码 \n 您的邮箱验证码为： "+activCode+"\n 有效时间为5分钟"  ;
        log.info("向"+email+"发送邮件,内容："+content);
        ThreadFactory.excutor(()-> mailService.sendSimpleMail((String) email,"应急平台找回密码",content));


    }

    /**
     * 注册用户发送邮箱
     **/
    @RabbitHandler
    @RabbitListener(queues = "email-register")
    public void emailCheck(JSONObject json) {
        Object email = json.get("email");
        Object activCode = json.get("activCode");
        String content="您的邮箱验证码为： "+activCode +"\n 有效时间为5分钟" ;
        log.info("向"+email+"发送邮件,内容："+content);
        ThreadFactory.excutor(()-> mailService.sendSimpleMail((String) email,"应急平台邮箱验证激活用户",content));
    }


    /**
     * 注册发送邮箱验证
     **/
    @RabbitHandler
    @RabbitListener(queues = "sms-register")
    public void register(String phoneNumber) {

    }

}
