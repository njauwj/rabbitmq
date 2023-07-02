package com.atwj.rabbitmq.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author wj
 * @create_time 2023/7/2
 * @description 延迟交换机的创建
 */
@Configuration
public class DelayExchangeConfig {

    //custom：adj 定制的
    @Bean
    public CustomExchange customExchange() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-delayed-type", "direct");
        return new CustomExchange("exchange.delay", "x-delayed-message", true, false, map);
    }

    @Bean
    public Queue queueDelay() {
        return QueueBuilder.durable("queue.delay").build();
    }

    @Bean
    public Binding bindingDelay(CustomExchange customExchange, Queue queueDelay) {
        return BindingBuilder.bind(queueDelay).to(customExchange).with("delay").noargs();
    }


}
