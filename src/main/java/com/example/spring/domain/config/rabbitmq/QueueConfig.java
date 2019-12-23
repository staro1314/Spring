package com.example.spring.domain.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Staro
 * @date: 2019/3/1614:08
 * @Description:
 */
@Configuration
public class QueueConfig {

    /**
     * durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
     * auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
     * exclusive  表示该消息队列是否只在当前connection生效,默认是false
     */
    @Bean
    public Queue queue_A() {
        return new Queue(RabbitMQConfig.QUEUE_A, true, false, false);
    }

    @Bean
    Queue queue_B() {
        return new Queue(RabbitMQConfig.QUEUE_B, true, false, false);
    }

    @Bean
    Queue queue_C() {
        return new Queue(RabbitMQConfig.QUEUE_C, true, false, false);
    }
}
