package com.atwj.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author wj
 * @create_time 2023/6/30
 * @description 队列设置过期时间，则在这个队列的所有消息都有一个过期时间，如果队列有过期时间，消息也有过期时间，则以小的为准
 */
@SpringBootTest
public class QueueTTLTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void testQueueTTL() {
        Message message = new Message("hello world".getBytes());
        rabbitTemplate.convertAndSend("exchange.direct", "routingH", message);
    }

}
