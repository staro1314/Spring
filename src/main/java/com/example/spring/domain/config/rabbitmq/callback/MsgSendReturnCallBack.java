package com.example.spring.domain.config.rabbitmq.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author: Staro
 * @date: 2019/3/1615:14
 * @Description:
 */
public class MsgSendReturnCallBack implements RabbitTemplate.ReturnCallback {

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("回馈消息："+message);
    }
}
