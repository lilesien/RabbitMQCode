package com.lilesien.publish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectExchangeTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "exchange.direct";

    @Test
    public void directPublish() {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "red", "hello direct Exchange red");
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "yellow", "hello direct Exchange yellow");
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "blue", "hello direct Exchange blue");
    }

}
