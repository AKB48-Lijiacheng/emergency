package com.westcatr.emergency.config.rabbitMqConfig;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author lijiacheng
 * @Date 2021/4/6
 */
@Component
public class TopicExchangeAndBingdingConfig {

    @Bean
    public TopicExchange topicExchange(){
        TopicExchange topicExchange=new TopicExchange("sms-ex");
        return topicExchange;
    }

}
