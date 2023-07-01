package com.atwj.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
    public Queue queueH() {
        Map<String, Object> arguments = new HashMap<>();
        //设置整个队列的过期时间 durable 表示是否持久化队列，即服务器重启后队列依然存在
        arguments.put("x-message-ttl", 20000);
        return new Queue("queue.direct.h", true, false, false, arguments);
    }


    @Bean
    public Queue queueI() {
        Map<String, Object> arguments = new HashMap<>();
        //设置整个队列的过期时间 durable 表示是否持久化队列，即服务器重启后队列依然存在
        arguments.put("x-message-ttl", 20000);
        //绑定死信交换机
        arguments.put("x-dead-letter-exchange", "exchange.dlx");
        //指定RoutingKey 要与死信队列与死信交换机之间的相同
        arguments.put("x-dead-letter-routing-key", "error");
        return new Queue("queue.direct.i", true, false, false, arguments);
    }

    @Bean
    public Binding bindingI(DirectExchange directExchange, Queue queueI) {
        return BindingBuilder.bind(queueI).to(directExchange).with("routingI");
    }


    @Bean
    public Binding bindingH(DirectExchange directExchange, Queue queueH) {
        return BindingBuilder.bind(queueH).to(directExchange).with("routingH");
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
