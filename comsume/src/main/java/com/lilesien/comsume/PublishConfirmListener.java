package com.lilesien.comsume;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PublishConfirmListener {

    @RabbitListener(queues = "confirm.queue")
    public void confirmListener(Message message, Channel channel) throws IOException {
        channel.exchangeDelete("confirm.exchange");
        System.out.println("consumer接收到的消息为" + new String(message.getBody()) +
                ",路由信息为" + message.getMessageProperties().getReceivedRoutingKey());
    }
}
