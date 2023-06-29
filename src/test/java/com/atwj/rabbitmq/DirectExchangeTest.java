package com.atwj.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author wj
 * @create_time 2023/6/29
 * @description
 */
@SpringBootTest
public class DirectExchangeTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void testDirectExchange() {
        String msg = "hello world";
        Message message = new Message(msg.getBytes());
        rabbitTemplate.convertAndSend("exchange.direct", "routingC", message);
    }

}
