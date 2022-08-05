package com.lilesien.comsume;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkQueueListener {

    /*两个消费者消费能力不同，在没有使用预取机制时，队列会将消息全部发送给两个消费者，两个消费者处理的信息量相同，
    而当使用预取机制时，通过对消费者的限制，规定其最多只有n个未被处理的信息，当达到n时，就不能再获取数据，直到未
    处理的数据小于n
     */
    @RabbitListener(queues = {"worker.queue"})
    public void Queue1Listener(String msg) throws InterruptedException {
        System.out.println("消费者1接收到消息[" + msg +"]");
        Thread.sleep(1000);
    }

    @RabbitListener(queues = {"worker.queue"})
    public void Queue2Listener(String msg) throws InterruptedException {
        System.err.println("消费者2接收到消息[" + msg +"]");
        Thread.sleep(100);
    }

}
