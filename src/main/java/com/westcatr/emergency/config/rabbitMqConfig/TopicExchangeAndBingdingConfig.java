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
    public TopicExchange EmailTopicExchange() {
        TopicExchange topicExchange = new TopicExchange("email-ex");
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
    @Bean
    Binding bindingTopic3() {
        return BindingBuilder.bind(emailRegister()).to(EmailTopicExchange()).with("emailRegister");
    }

    /**
     * 注册时发送邮箱验证
     * @author lijiacheng
     * @since 2021/4/16
     **/
    @Bean
    public Queue emailRegister() {
        return new Queue("email-register");
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

