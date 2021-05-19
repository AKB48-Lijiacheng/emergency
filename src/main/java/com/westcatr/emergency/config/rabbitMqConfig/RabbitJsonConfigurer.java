package com.westcatr.emergency.config.rabbitMqConfig;

import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
@ConditionalOnClass(RabbitTemplate.class)
public class RabbitJsonConfigurer implements RabbitListenerConfigurer {

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate out = new RabbitTemplate(connectionFactory);
        out.setMessageConverter(jackson2JsonMessageConverter);
        return out;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        return defaultMessageHandlerMethodFactory;
    }
    private final MappingJackson2MessageConverter mappingJackson2MessageConverter=new MappingJackson2MessageConverter();
    private final DefaultMessageHandlerMethodFactory defaultMessageHandlerMethodFactory
            = new DefaultMessageHandlerMethodFactory();
    private final Jackson2JsonMessageConverter jackson2JsonMessageConverter
            =new Jackson2JsonMessageConverter();
    {
        defaultMessageHandlerMethodFactory.setMessageConverter(mappingJackson2MessageConverter);
    }
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return mappingJackson2MessageConverter;
    }
}
