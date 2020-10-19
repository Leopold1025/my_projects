package com.lipeng.test.rabbitmq;

import com.lipeng.test.rabbitmq.config.RabbitmqConfig;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: my_projects
 * @description:
 * @author: LiPeng
 * @create: 2020-10-01 21:41
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class Producer05_topics_springboot {

    @Autowired
    RabbitTemplate rabbitTemplate;

    String message = "send email to user";

    /**
     * 1. 交换机
     * 2. 路由键
     * 3. 消息
     */
    @Test
    public void testSendEmail(){
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "inform.email", message);
    }


}
