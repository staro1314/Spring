package com.example.spring.domain.config.rabbitmq;


import com.example.spring.domain.config.rabbitmq.callback.MsgSendConfirmCallBack;
import com.example.spring.domain.config.rabbitmq.callback.MsgSendReturnCallBack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Staro
 * @date: 2019/3/1611:50
 * @Description:
 */
@Slf4j
@Configuration
public class RabbitMQConfig {
    /**
     * Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
     * Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
     * Queue:消息的载体,每个消息都会被投到一个或多个队列。
     * Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
     * Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
     * vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
     * Producer:消息生产者,就是投递消息的程序.
     * Consumer:消息消费者,就是接受消息的程序.
     * Channel:消息通道,在客户端的每个连接里,可建立多个channel.
     */

    @Autowired
    private QueueConfig queueConfig;

    @Autowired
    private ExchangeConfig exchangeConfig;

    @Autowired
    private ConnectionFactory connectionFactory;

    //队列
    public static final String QUEUE_A = "queue_A";
    public static final String QUEUE_B = "queue_B";
    public static final String QUEUE_C = "queue_C";

    //消息交换机
    public static final String EXCHANGE_A = "mq_exchange_A";
    public static final String EXCHANGE_B = "mq_exchange_B";
    public static final String EXCHANGE_C = "mq_exchange_C";

    //路由关键字
    public static final String ROUTINGKEY_A = "routingKey_A";
    public static final String ROUTINGKEY_B = "routingKey_B";
    public static final String ROUTINGKEY_C = "routingKey_C";


    @Bean
    public Binding bindingA() {
        return BindingBuilder.bind(queueConfig.queue_A()).to(exchangeConfig.directExchangeA()).with(ROUTINGKEY_A);
    }

    @Bean
    public Binding bindingB() {
        return BindingBuilder.bind(queueConfig.queue_B()).to(exchangeConfig.directExchangeA()).with(ROUTINGKEY_B);
    }

    /**
     * queue listener  观察 监听模式
     * 当有消息到达时会通知监听在对应的队列上的监听对象
     *
     * @return
     */
//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
//        simpleMessageListenerContainer.addQueues(queueConfig.queue_A());
//        simpleMessageListenerContainer.setExposeListenerChannel(true);
//        simpleMessageListenerContainer.setMaxConcurrentConsumers(5);
//        simpleMessageListenerContainer.setConcurrentConsumers(1);
//        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        return simpleMessageListenerContainer;
//    }
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // template.setMessageConverter(); 可以自定义消息转换器  默认使用的JDK的，所以消息对象需要实现Serializable

        /**若使用confirm-callback或return-callback，
         * 必须要配置publisherConfirms或publisherReturns为true
         * 每个rabbitTemplate只能有一个confirm-callback和return-callback
         */
        rabbitTemplate.setConfirmCallback(msgSendConfirmCallBack());

        /**
         * 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true，
         * 可针对每次请求的消息去确定’mandatory’的boolean值，
         * 只能在提供’return -callback’时使用，与mandatory互斥
         */
        rabbitTemplate.setReturnCallback(msgSendReturnCallBack());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }


    /**  关于 msgSendConfirmCallBack 和 msgSendReturnCallback 的回调说明：
     1.如果消息没有到exchange,则confirm回调,ack=false
     2.如果消息到达exchange,则confirm回调,ack=true
     3.exchange到queue成功,则不回调return
     4.exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
     */

    /**
     * 消息确认机制
     * Confirms给客户端一种轻量级的方式，能够跟踪哪些消息被broker处理，
     * 哪些可能因为broker宕掉或者网络失败的情况而重新发布。
     * 确认并且保证消息被送达，提供了两种方式：发布确认和事务。(两者不可同时使用)
     * 在channel为事务时，不可引入确认模式；同样channel为确认模式下，不可使用事务。
     *
     * @return
     */
    @Bean
    public MsgSendConfirmCallBack msgSendConfirmCallBack() {
        return new MsgSendConfirmCallBack();
    }

    @Bean
    public MsgSendReturnCallBack msgSendReturnCallBack() {
        return new MsgSendReturnCallBack();
    }
}
