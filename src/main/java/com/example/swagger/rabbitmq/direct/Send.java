package com.example.swagger.rabbitmq.direct;

import com.example.swagger.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 17:16
 * @version:
 * @modified By:
 */
public class Send {
    private final static String EXCHANGE_NAME = "direct_exchange_test";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        String msg = "商品删除了, id = 1001";
        //发送消息指定routing key: 为insert,代表新增商品
        channel.basicPublish(EXCHANGE_NAME,"delete",null,msg.getBytes());
        System.out.println(" [商品服务：] Sent '" + msg + "'");
        channel.close();
        connection.close();

    }
}
