package com.example.swagger.rabbitmq.fanout;

import com.example.swagger.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 16:25
 * @version:
 * @modified By:
 */
//消费者1
public class Recv {
    private final static String QUEUE_NAME = "fanout_exchange_queue_1";
    private final static String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        //定义消费者队列
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
                String msg = new String(body);
                System.out.println(" [消费者1] received : " + msg + "!");
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
