package com.lilesien.comsume;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleQueueListener {

    @RabbitListener(queues = {"simple.queue"})
    public void listen1Queue(String msg){
        System.out.println("消费者1接收到消息[" + msg +"]");
    }

}
