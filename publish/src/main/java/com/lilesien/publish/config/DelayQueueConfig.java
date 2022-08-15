package com.lilesien.publish.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DelayQueueConfig {

    //使用插件后的交换机和消息队列配置,配置好队列和交换机后接下来就是正常的使用了

    private static final String ROUTING_KEY = "";

    @Bean("delayExchange")
    public CustomExchange exchange() {

        Map<String, Object> arguments = new HashMap<>();
        //声明交换机的类型，fanout/direct/topic
        arguments.put("x-delayed-type", "direct");
        //返回CustomExchange时需要设置type为x-delay-message，这样才能声明是一个延时队列
        return new CustomExchange("delay.exchange", "x-delayed-message", true, false, arguments);
    }

    @Bean("delayQueue")
    public Queue queue() {
        return new Queue("delay.queue");
    }

    @Bean
    public Binding queueBindExchange(@Qualifier("delayQueue") Queue queue,
                                     @Qualifier("delayExchange") CustomExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }

}
