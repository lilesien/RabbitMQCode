package com.lilesien.publish.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PublishConfirmConfig {

    @Bean("confirmExchange")
    public DirectExchange directExchange() {
        return ExchangeBuilder.directExchange("confirm.exchange").durable(true).build();
    }

    @Bean("confirmQueue")
    public Queue declareQueue() {
        return QueueBuilder.durable("confirm.queue").build();
    }

    @Bean
    public Binding confirmBinding(@Qualifier("confirmExchange") DirectExchange exchange,
                                  @Qualifier("confirmQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("123");
    }


}
