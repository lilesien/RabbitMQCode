package com.lilesien.comsume;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleQueueListener {

    @RabbitListener(queues = {"simple.queue"})
    public void listenQueue(String msg){
        System.out.println("消费者接收到消息[" + msg +"]");
    }

}
