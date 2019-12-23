package com.example.spring.domain.config.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: Staro
 * @date: 2019/3/1614:44
 * @Description:
 */
@Component
public class FirstConsumer {

    @RabbitListener(queues = {"queue_A","queue_B"})
    public void handleMessage(String message)throws Exception{
        System.out.println("FirstConsumer {} handleMessage :"+message);
    }
}
