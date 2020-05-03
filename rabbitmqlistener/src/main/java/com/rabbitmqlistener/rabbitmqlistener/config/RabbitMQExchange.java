package com.rabbitmqlistener.rabbitmqlistener.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchange {

    /*@Bean
    Exchange topicExchange(){
        return new TopicExchange("MyTopicExchange");
    }

    @Bean
    Exchange directExchange(){
        return  ExchangeBuilder.directExchange("DirectExchange")
                .autoDelete()
                .internal()
                .build();
    }*/
}
