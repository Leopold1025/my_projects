package com.lipeng.test.rabbitmq.mq;


import com.lipeng.test.rabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: my_projects
 * @description:
 * @author: LiPeng
 * @create: 2020-10-02 16:39
 **/
@Component
public class RecieveHandler {

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})
    public void send_email(String message){
        System.out.println("Recieve a message : " + message);
    }


}
