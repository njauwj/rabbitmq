package com.atwj.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author wj
 * @create_time 2023/7/2
 * @description
 */
@SpringBootTest
public class DelayExchangeTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void testDelay() {
        MessageProperties messageProperties = new MessageProperties();
        //设置消息过期时间
        messageProperties.setHeader("x-delay", "20000");
        Message message = MessageBuilder.withBody("hello world".getBytes()).andProperties(messageProperties).build();
        rabbitTemplate.convertAndSend("exchange.delay", "delay", message);
    }


}
