package com.atwj.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author wj
 * @create_time 2023/6/30
 * @description 测试单个消息过期
 */
@SpringBootTest
public class SingleMessageTTLTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void testTTL() {
        MessageProperties messageProperties = new MessageProperties();
        //设置消息过期毫秒数
        messageProperties.setExpiration("20000");
        Message message = new Message("hello world".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("exchange.direct", "routingG", message);
    }
}
