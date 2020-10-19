package com.lipeng.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: my_projects
 * @description:
 * @author: LiPeng
 * @create: 2020-10-01 00:54
 **/
public class Consumer03_routing_sms {
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_ROUTING_INFORM = "exchange_routing_inform";
    private static final String ROUTINGKEY_SMS = "inform_sms";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");  //设置虚拟机
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //创建会话通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_ROUTING_INFORM, BuiltinExchangeType.DIRECT);

        //交换机和队列绑定
        channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTING_INFORM, ROUTINGKEY_SMS);

        //实现消费方法
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            //当消费者接收到消息后，此方法将被调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String exchange = envelope.getExchange();
                long deliveryTag = envelope.getDeliveryTag();
                String message = new String(body, "utf-8");
                System.out.println("receive message: " + message);
            }
        };

        //监听队列
        channel.basicConsume(QUEUE_INFORM_SMS, true, defaultConsumer);

    }
}
