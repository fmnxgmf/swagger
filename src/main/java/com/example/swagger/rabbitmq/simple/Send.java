package com.example.swagger.rabbitmq.simple;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 14:53
 * @version:
 * @modified By:
 */

import com.example.swagger.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 */
public class Send {
    private final static  String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中获取通道，使用通道完成消息相关操作
        Channel channel = connection.createChannel();
        //声明创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //消息内容
        String mes = "Hello2 word2 RabbitMq2";
        //向指定队列中发送消息
        channel.basicPublish("",QUEUE_NAME,null,mes.getBytes());
        System.out.println("[x]--send '"+mes+"'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
