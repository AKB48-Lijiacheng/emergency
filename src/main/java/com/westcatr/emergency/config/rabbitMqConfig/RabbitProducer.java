package com.westcatr.emergency.config.rabbitMqConfig;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijiacheng
 * @Date 2021/4/16
 */
@Configuration
public class RabbitProducer {
    @Autowired
    private AmqpTemplate rabbitTemplate;



    /**
     * 找密码通知
     */
    public void findPasswordSms(String phone) {
        this.rabbitTemplate.convertAndSend("sms-ex","findPasswprd",phone);
    }


    /**
     * 注册短信通知
     */
    public void registerSms(String phone) {
        this.rabbitTemplate.convertAndSend("sms-ex","register",phone);
    }


    /**
     * 注册时发送验证邮箱
     */
    public void emailRegister(String email,String userName,String activCode) {
        JSONObject json = new JSONObject();
        json.put("email",email);
        json.put("username",userName);
        json.put("activCode",activCode);
        this.rabbitTemplate.convertAndSend("email-ex","register",json);
    }
}
