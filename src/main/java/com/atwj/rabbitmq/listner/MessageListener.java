package com.atwj.rabbitmq.listner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wj
 * @create_time 2023/6/29
 * @description
 */
@Component
@Slf4j
public class MessageListener {

    @RabbitListener(queues = {"queue.fanout.a", "queue.fanout.b"})
    public void receiveMessage(Message message) {
        byte[] body = message.getBody();
        log.info("接收到的消息为{}", new String(body));
    }

}
