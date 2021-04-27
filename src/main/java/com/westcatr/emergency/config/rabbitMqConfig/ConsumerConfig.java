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
    @RabbitListener(queues = "sms-findPassword")
    public void findNextPersonSendMessage(String phoneNumber) {

    }

    /**
     * 注册用户发送邮箱
     **/
    @RabbitHandler
    @RabbitListener(queues = "email-register")
    public void emailCheck(JSONObject json) {
        Object email = json.get("email");
        Object username = json.get("username");
        Object activCode = json.get("activCode");
        String content="正在激活 "+username+" 用户 \n 您的邮箱验证码为： "+activCode;
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
