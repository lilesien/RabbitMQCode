package com.lilesien.publish.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FanoutConfig {

    @Bean(name = "fanoutExchange")
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout.exchange");
    }

    @Bean(name = "fanoutQueue1")
    public Queue queue1(){
        return new Queue("fanout.queue1");
    }

    @Bean(name = "fanoutQueue2")
    public Queue queue2(){
        return new Queue("fanout.queue2");
    }

    @Bean
    public Binding queue1BindingExchange(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,
                                        @Qualifier("fanoutQueue1") Queue queue){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public Binding queue2BindingExchange(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,
                                        @Qualifier("fanoutQueue2") Queue queue){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}
