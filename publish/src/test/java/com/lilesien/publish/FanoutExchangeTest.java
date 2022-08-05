package com.lilesien.publish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutExchangeTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "fanout.exchange";

    @Test
    public void fanoutPublish(){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"","hello fanout exchange");
    }

}
