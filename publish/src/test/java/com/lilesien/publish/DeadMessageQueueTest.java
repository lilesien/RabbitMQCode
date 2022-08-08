package com.lilesien.publish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeadMessageQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "normal.exchange";

    @Test
    public void deadQueueTest(){
        String msg = "死信队列测试";
        Message message = MessageBuilder
                .withBody(msg.getBytes(StandardCharsets.UTF_8))
                .setExpiration("0")
                .setContentEncoding("utf-8")
                .build();
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"", message);
    }

}
