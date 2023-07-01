package com.atwj.rabbitmq.listner;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author wj
 * @create_time 2023/6/29
 * @description 消费者
 */
@Component
@Slf4j
public class MessageListener {
    @RabbitListener(queues = {"queue.fanout.a", "queue.fanout.b", "queue.direct.c", "queue.direct.d", "queue.topic.e", "queue.topic.f"})
    public void receiveMessage(Message message) {
        byte[] body = message.getBody();
        log.info("接收到的消息为{}", new String(body));
    }

    @RabbitListener(queues = {"queue.direct.i"})
    public void receiveMessage2(Message message, Channel channel) {
        MessageProperties messageProperties = message.getMessageProperties();
        //消息的唯一标识
        long deliveryTag = messageProperties.getDeliveryTag();
        try {
            byte[] body = message.getBody();
            log.info("接收到的消息为{}", new String(body));
            //multiple false 表示只确认当前消息 true 确认所有消息
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("处理过程出错{}", e.getMessage());
            try {
                //requeue true 重新入队 false 进入死信队列，如果没有死信队列则直接删除
                channel.basicNack(deliveryTag, false, false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }


}
