package com.lilesien.publish.config;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     *
     * @param correlationData 保存消息相关的信息，包括id等,这个信息需要在发送端封装号的
     * @param b 用来表示消息是否被成功接收到
     * @param s 消息被拒绝接收的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (b) {
            System.out.println("交换机已经收到id为" + id + "的消息");
        } else {
            System.out.println("交换机没有收到id为" + id + "的消息，具体原因是：" + s);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        //被退回消息
        String message = new String(returnedMessage.getMessage().getBody());
        //接收消息的交换机
        String exchange = returnedMessage.getExchange();
        //错误编码
        int replyCode = returnedMessage.getReplyCode();
        //错误原因
        String replyText = returnedMessage.getReplyText();
        //路由的key
        String routingKey = returnedMessage.getRoutingKey();
        System.out.println("发送的消息为：" + message);
        System.out.println("被交换机" + exchange + "退回，路由为" + routingKey + "，退回原因为" + replyText);
    }
}
