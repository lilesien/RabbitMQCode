package com.lilesien.comsume;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class DeadMessageQueueListener {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "normal.exchange",type = ExchangeTypes.FANOUT),
            value = @Queue(name = "deadMessage.queue1", arguments = {@Argument(name = "x-dead-letter-exchange", value = "dead.exchange"),
                    @Argument(name = "x-message-ttl",value = "10", type = "java.lang.Integer"),
                    @Argument(name = "x-max-length", value = "3", type = "java.lang.Integer")
            })
    ))
    public void deadMessageListener1(String msg) throws InterruptedException {
        System.out.println("正常队列接收到的消息" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "deadMessage.queue2"),
            exchange = @Exchange(name = "dead.exchange",type = ExchangeTypes.FANOUT)
    ))
    public void deadMessageListener2(String msg){
        System.out.println("死信队列者接收到的消息" + msg);
    }
}
