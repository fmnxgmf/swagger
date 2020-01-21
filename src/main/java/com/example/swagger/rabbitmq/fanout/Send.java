package com.example.swagger.rabbitmq.fanout;

import com.example.swagger.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @description: Fanout，也称为广播。
 * @author: gmf
 * @date: Created in 2020/1/21 16:25
 * @version:
 * @modified By:
 */
public class Send {
    private final static String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明exchange,指定类型为fanout
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //消息内容
        String msg = "hello everone";
        //发布消息到exchange
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
        System.out.println(" [生产者] Sent '" + msg + "'");
        channel.close();
        connection.close();
    }
}
