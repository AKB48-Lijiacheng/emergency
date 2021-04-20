package com.westcatr.emergency.config.rabbitMqConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijiacheng
 * @Date 2021/4/6
 */
@Configuration
public class TopicExchangeAndBingdingConfig {

    @Bean
    public TopicExchange topicExchange() {
        TopicExchange topicExchange = new TopicExchange("sms-ex");
        return topicExchange;
    }


    @Bean
    Binding bindingTopic() {
        return BindingBuilder.bind(findPassword()).to(topicExchange()).with("findPassword");
    }
    @Bean
    Binding bindingTopic2() {
        return BindingBuilder.bind(UserRegister()).to(topicExchange()).with("register");
    }


    /**
     * 找回密码短信通知
     *
     * @author lijiacheng
     * @since 2021/4/16
     **/
    @Bean
    public Queue findPassword() {
        return new Queue("sms-findPassword");
    }

    /**
     * 注册短信验证
     *
     * @author lijiacheng
     * @since 2021/4/16
     **/
    @Bean
    public Queue UserRegister() {
        return new Queue("sms-register");
    }
}

