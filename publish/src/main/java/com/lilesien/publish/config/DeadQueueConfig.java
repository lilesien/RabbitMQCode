package com.lilesien.publish.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Component,在消费者端使用注解声明后就不需要在客户端进行声明
public class DeadQueueConfig {

    //普通交换机
    private static final String NORMAL_EXCHANGE_NAME = "normal.exchange";
    private static final String DEAD_EXCHANGE_NAME = "dead.exchange";

    //声明普通交换机
    @Bean(name = "normalExchange")
    public FanoutExchange declareExchange1(){
        return new FanoutExchange("normal.exchange");
    }

    //声明死信交换机
    @Bean(name = "deadExchange")
    public FanoutExchange declareExchange2(){
        return new FanoutExchange("dead.exchange");
    }

    //声明普通队列
    @Bean(name = "deadQueue1")
    public Queue declareDeadQueue1(){
        //声明一些参数，表示当消息变为死信时，发送到哪个交换机
        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange",DEAD_EXCHANGE_NAME);
        //如果不是Fanout类型的交换机，还需要设置x-dead-letter-routing-key为对应的routingkey
        map.put("x-message-ttl", 10);
        return new Queue("deadMessage.queue1",true,false,false,map);

    }

    //声明死信队列
    @Bean(name = "deadQueue2")
    public Queue declareDeadQueue2(){
        return new Queue("deadMessage.queue2");
    }

    //普通队列与普通交换机绑定
    @Bean
    public Binding queue1BindExchange(@Qualifier("normalExchange") FanoutExchange fanoutExchange,
                                      @Qualifier("deadQueue1") Queue queue){
        return BindingBuilder.bind(queue).to(fanoutExchange);

    }


    //死信队列与死信交换机绑定
    @Bean
    public Binding queue2BindExchange(@Qualifier("deadExchange") FanoutExchange fanoutExchange,
                                      @Qualifier("deadQueue2") Queue queue){
        return BindingBuilder.bind(queue).to(fanoutExchange);

    }


}
