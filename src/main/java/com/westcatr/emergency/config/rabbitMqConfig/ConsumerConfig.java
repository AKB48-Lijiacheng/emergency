package com.westcatr.emergency.config.rabbitMqConfig;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijiacheng
 * @Date 2021/4/16
 */
@Configuration
public class ConsumerConfig {

    /**
     * 找回密码
     **/
    @RabbitHandler
    @RabbitListener(queues = "sms-findPassword")
    public void findNextPersonSendMessage(String phoneNumber) {

    }


    /**
     * 注册验证
     **/
    @RabbitHandler
    @RabbitListener(queues = "sms-register")
    public void register(String phoneNumber) {

    }

}
