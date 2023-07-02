package com.atwj.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author wj
 * @create_time 2023/7/2
 * @description 发送者确认
 */
@SpringBootTest
@Slf4j
public class PublishConfirmTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        //构造方法执行之后执行,用于初始化一些信息
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    log.info("消息成功到达交换机{}", correlationData.getId());
                    return;
                }
                //未到达交换机可以采取一系列措施保证消息不会丢失
                log.error("消息未发送到交换机{}", cause);
            }
        });
    }


    @Test
    void testConfirm() {
        Message message = MessageBuilder.withBody("hello world".getBytes()).build();
        CorrelationData correlationData = new CorrelationData();//相关消息
        //设置相关消息的唯一ID
        correlationData.setId("12345");
        rabbitTemplate.convertAndSend("exchange.direct", "routingA", message, correlationData);
    }


}
