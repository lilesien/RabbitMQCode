package com.lilesien.comsume;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectExchangeListener {

    //key相当于交换机与队列的bindingkey，交换机通过bindingkey发送消息给队列
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "exchange.direct", type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void direct1Listener(String msg){
        System.out.println("消费者1接收到消息为" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "exchange.direct", type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    ))
    public void direct2Listener(String msg){
        System.out.println("消费者2接收到消息为" + msg);
    }

}
