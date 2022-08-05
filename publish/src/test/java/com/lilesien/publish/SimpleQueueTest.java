package com.lilesien.publish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String QUEUE_NAME = "simple.queue";

    @Test
    public void SimpleQueuePublish(){
        //使用SpringAMQP实现简单队列前提是队列已经存在，应为api中的方法不声明队列
        rabbitTemplate.convertAndSend(QUEUE_NAME,"hello world!!!");
    }
}
