package com.lipeng.test.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/**
 * @program: my_projects
 * @description:
 * @author: LiPeng
 * @create: 2020-09-30 00:42
 **/
public class Producer01 {

    private static final String QUEUE = "helloworld";

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");  //设置虚拟机
        //创建连接
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            //创建会话通道
            channel = connection.createChannel();
            //声明队列
            channel.queueDeclare(QUEUE, true, false, false, null);
            //发送消息
            String message = "hello world ......";
            channel.basicPublish("", QUEUE, null, message.getBytes());
            System.out.println("sent to mq : " + message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

}
