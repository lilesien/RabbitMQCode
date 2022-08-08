# RabbitMQCode

## 注意事项
1. 在进行交换机和队列绑定时，可以通过配置类进行声明绑定，也可以通过使用RabbitListener注解进行声明绑定
2. 对于ttl过期而造成消息变为死信，ttl既可以在声明队列时设置，也可以在发送消息时设置参数

## 死信队列的一些参数
1. x-message-ttl：指定消息过期TTL
2. x-dead-letter-exchange：指定队列关联的死信交换机
3. x-dead-letter-routing-key：指定队列死信交换机绑定的路由键（FanoutExchange不需要设置routingkey）

## 一些routingkey的知识
1. 在使用routingkey时既可以将值写死，也可以写成一个通配符的样式
2. 比如写成pohone.*或phone.#，这里的\*代表一个单词，\#代表0个或多个单词