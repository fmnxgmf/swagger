package com.example.swagger.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 14:57
 * @version: rabbitMq连接工具
 * @modified By:
 */
public class ConnectionUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("120.79.23.95");
        //端口
        factory.setPort(5672);;
        //设置账号信息，用户名，密码，vhost
        factory.setVirtualHost("/gmf");
        factory.setUsername("gmf");
        factory.setPassword("gmf123");
        //通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
