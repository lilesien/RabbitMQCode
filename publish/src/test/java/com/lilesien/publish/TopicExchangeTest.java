package com.lilesien.publish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicExchangeTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "topic.exchange";
    @Test
    public void topicPublish(){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"phone.iphone","iphone13");
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"phone.android","小米13");
    }

}
