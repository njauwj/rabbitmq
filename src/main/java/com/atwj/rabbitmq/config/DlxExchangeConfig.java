package com.atwj.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author wj
 * @create_time 2023/7/1
 * @description 死信交换机与死信队列
 */
@Configuration
public class DlxExchangeConfig {


    @Bean
    public DirectExchange dlxExchange() {
        return ExchangeBuilder.directExchange("exchange.dlx").build();
    }

    @Bean
    public Queue dlxQueue() {
        return QueueBuilder.durable("queue.dlx").build();
    }

    @Bean
    public Binding bindingDlx(DirectExchange dlxExchange, Queue dlxQueue) {
        return BindingBuilder.bind(dlxQueue).to(dlxExchange).with("error");
    }

}
