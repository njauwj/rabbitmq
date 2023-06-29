package com.atwj.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RabbitmqApplicationTests {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试扇形交换机发送消息
     */
    @Test
    void testFanoutExchange() {
        String msg = "this is a message";
        Message message = new Message(msg.getBytes());
        //扇形交换机不需要指定routingKey
        rabbitTemplate.convertAndSend("exchange.fanout", "", message);
    }

}
