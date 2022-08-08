package com.lilesien.comsume;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class TopicExchangeListener {

    //消费者1接收所有手机
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "topic.exchange",type = ExchangeTypes.TOPIC),
            key = {"phone.#"}
    ))
    public void topic1Listener(String msg){
        System.out.println("消费者1接收到的手机为" + msg);
    }

    //消费者2只接收苹果类型的手机
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "topic.exchange",type = ExchangeTypes.TOPIC),
            key = {"#.iphone"}
    ))
    public void topic2Listener(String msg){
        System.out.println("消费者2接收到到的手机为" + msg);
    }

}
