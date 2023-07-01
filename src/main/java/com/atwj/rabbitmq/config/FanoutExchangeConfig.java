package com.atwj.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wj
 * @create_time 2023/6/29
 * @description 扇形交换机 不需要指定routingKey
 */
@Configuration
public class FanoutExchangeConfig {

    /*
    1. 定义交换机
    2. 定义消息队列
    3. 绑定交换机和消息队列
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        //创建扇形交换机，特点不需要路由key
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
    public Queue queueA() {
        return new Queue("queue.fanout.a");
    }

    @Bean
    public Queue queueB() {
        return new Queue("queue.fanout.b");
    }


    @Bean
    public Binding bindingA(FanoutExchange fanoutExchange, Queue queueA) {
        //绑定交换机与队列
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingB(FanoutExchange fanoutExchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }


}
