package com.example.swagger.rabbitmq.simple;

import com.example.swagger.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 15:25
 * @version: 消费者
 * @modified By:
 */
public class Recv {
    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义队列的消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                //body即消息体
                String msg = new String(body);
                System.out.println("[x]--received '"+msg+"'!");
            }
        };
        //监听队列，第二个参数是否开启自动ack
        channel.basicConsume(QUEUE_NAME,true,defaultConsumer);
    }
}
