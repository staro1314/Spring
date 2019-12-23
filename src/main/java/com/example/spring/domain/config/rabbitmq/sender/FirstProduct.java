package com.example.spring.domain.config.rabbitmq.sender;

import com.example.spring.domain.config.rabbitmq.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author: Staro
 * @date: 2019/3/1612:07
 * @Description:
 */
@Slf4j
@Component
public class FirstProduct {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public FirstProduct(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void senMessage(String content){
        CorrelationData correlationData=new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_A,RabbitMQConfig.ROUTINGKEY_A,content,correlationData);
    }

}
