package com.atwj.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wj
 * @create_time 2023/6/29
 * @description 直连交换机 需要指定routingKey
 */
@Configuration
public class DirectExchangeConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("exchange.direct");
    }

    @Bean
    public Queue queueC() {
        return new Queue("queue.direct.c");
    }

    @Bean
    public Queue queueD() {
        return new Queue("queue.direct.d");
    }

    @Bean
    public Queue queueG() {
        return new Queue("queue.direct.g");
    }

    @Bean
    public Binding bindingG(DirectExchange directExchange, Queue queueG) {
        return BindingBuilder.bind(queueG).to(directExchange).with("routingG");
    }

    @Bean
    public Binding bindingC1(DirectExchange directExchange, Queue queueC) {
        return BindingBuilder.bind(queueC).to(directExchange).with("routingA");
    }

    @Bean
    public Binding bindingC2(DirectExchange directExchange, Queue queueC) {
        return BindingBuilder.bind(queueC).to(directExchange).with("routingB");
    }

    @Bean
    public Binding bindingC3(DirectExchange directExchange, Queue queueC) {
        return BindingBuilder.bind(queueC).to(directExchange).with("routingC");
    }

    @Bean
    public Binding bindingD1(DirectExchange directExchange, Queue queueD) {
        return BindingBuilder.bind(queueD).to(directExchange).with("routingB");
    }
}
