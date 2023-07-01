package com.atwj.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author wj
 * @create_time 2023/7/1
 * @description 死信队列测试
 */
@SpringBootTest
public class DlxQueueTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void testDlx() {
        Message message = MessageBuilder.withBody("hello world".getBytes()).build();
        rabbitTemplate.convertAndSend("exchange.direct", "routingI", message);

    }

}
