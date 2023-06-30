package com.atwj.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wj
 * @create_time 2023/6/30
 * @description 主题交换机，特点是routingKey 可以使用通配符 * 表示一个字符 # 表示多个字符
 */
@Configuration
public class TopicExchangeConfig {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("exchange.topic");
    }

    @Bean
    public Queue queueE() {
        return new Queue("queue.topic.e");
    }

    @Bean
    public Queue queueF() {
        return new Queue("queue.topic.f");
    }

    @Bean
    public Binding bindingE(TopicExchange topicExchange, Queue queueE) {
        return BindingBuilder.bind(queueE).to(topicExchange).with("*.apple");
    }

    @Bean
    public Binding bindingF1(TopicExchange topicExchange, Queue queueF) {
        return BindingBuilder.bind(queueF).to(topicExchange).with("banana.*");
    }

    @Bean
    public Binding bindingF2(TopicExchange topicExchange, Queue queueF) {
        return BindingBuilder.bind(queueF).to(topicExchange).with("#.apple");
    }

}
