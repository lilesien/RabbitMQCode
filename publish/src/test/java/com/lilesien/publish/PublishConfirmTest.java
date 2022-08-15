package com.lilesien.publish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublishConfirmTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void confirmPublish() {
        //在CorrelationData中设置消息的id
        CorrelationData data = new CorrelationData("1");
        //通过修改交换机名称，分别给存在和不存在的交换机发送消息，在控制台就可以看到不同的结果
        //1. consumer接收到的消息为是否触发发布确认回调,路由信息为
        //2.交换机没有收到id为1的消息，具体原因是：channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'confirm.exchange1' in vhost '/', class-id=60, method-id=40)
        rabbitTemplate.convertAndSend("confirm.exchange", "345", "是否触发发布确认回调", data);
        //当交换机存在时，通过修改routingkey可以决定队列是否存在，然后查看控制台信息
        //发送的消息为：是否触发发布确认回调
        //被交换机confirm.exchange退回，路由为345，退回原因为NO_ROUTE
        //交换机已经收到id为1的消息
    }
}
