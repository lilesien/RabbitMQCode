package com.lilesien.comsume;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutExchangeListener {

    @RabbitListener(queues = {"fanout.queue1"})
    public void fanoutListener1(String msg) {
        System.out.println("消费者1接收到消息[" + msg + "]");
    }

    @RabbitListener(queues = {"fanout.queue2"})
    public void fanoutListener2(String msg) {
        System.out.println("消费者2接收到消息[" + msg + "]");
    }

}
