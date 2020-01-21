package com.example.swagger.rabbitmq.work;

import com.example.swagger.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 16:05
 * @version: 消费者
 * @modified By:
 */
public class Recv2 {
    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg = new String(body);
                System.out.println(" [消费者2] received : " + msg + "!");
                //手动ack
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
