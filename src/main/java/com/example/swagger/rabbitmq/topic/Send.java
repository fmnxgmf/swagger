package com.example.swagger.rabbitmq.topic;

import com.example.swagger.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 17:33
 * @version:
 * @modified By:
 */
public class Send {
    private final static String EXCHANGE_NAME = "topic_exchange_test2";

    public static void main(String[] args) throws Exception{
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 声明exchange，指定类型为topic,true(交换机持久化)durable:true
        channel.exchangeDeclare(EXCHANGE_NAME,"topic",true);
        // 消息内容
        String message = "新增商品 : id = 1001";
        // 发送消息，并且指定routing key 为：insert ,代表新增商品 //MessageProperties.PERSISTENT_TEXT_PLAIN消息持久化
        channel.basicPublish(EXCHANGE_NAME,"item.insert", MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        System.out.println(" [商品服务：] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
