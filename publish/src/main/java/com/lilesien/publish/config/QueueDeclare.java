package com.lilesien.publish.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueDeclare {

    @Bean(name = "workerQueue")
    public Queue declareWorkerQueue(){
        return new Queue("worker.queue");
    }

}
