package com.rabbitmqlistener.rabbitmqlistener.config;

import com.rabbitmqlistener.rabbitmqlistener.SimpleMessageListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String MY_QUEUE = "MyQueue";
    private static final String ROUTING_KEY = "topic";

    @Bean
    Queue myQueue() {
        return new Queue(MY_QUEUE, true);
    }

    @Bean
    Exchange topicExchange() {
        return ExchangeBuilder.topicExchange("MyTopicExchange")
                .durable(true)
                .build();
    }

    @Bean
    Binding binding() {
        //return new Binding(MY_QUEUE, Binding.DestinationType.QUEUE, "TopicExchange", ROUTING_KEY,null);

        return BindingBuilder
                .bind(myQueue())
                .to(topicExchange())
                .with(ROUTING_KEY)
                .noargs();
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
       // factory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
        return factory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueues(myQueue());
        container.setMessageListener(new SimpleMessageListener());
        return container;
    }


}
