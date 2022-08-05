package com.lilesien.publish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //前提在rabbitmq中队列已经存在,可以在rabbitmq的web界面创建，也可以在以bean的形式创建(参考QueueDeclare)
    private static final String WORK_QUEUE_NAME = "worker.queue";

    @Test
    public void workQueuePublish(){
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(WORK_QUEUE_NAME,"info_" + i);
        }
    }

}
