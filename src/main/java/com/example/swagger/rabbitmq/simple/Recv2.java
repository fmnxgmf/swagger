package com.example.swagger.rabbitmq.simple;

import com.example.swagger.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 15:34
 * @version: 手动进行ack
 * @modified By:
 */
public class Recv2 {
    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取到连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println("[x2]--received2 '"+msg+"'!");
                //手动ack异常回回滚
                System.out.println(1 / 0);
                //手动进行ack
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //监听队列，第二个参数false，手动进行ACK
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
