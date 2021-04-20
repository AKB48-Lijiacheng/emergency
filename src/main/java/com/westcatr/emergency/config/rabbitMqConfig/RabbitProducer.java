package com.westcatr.emergency.config.rabbitMqConfig;

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
}
