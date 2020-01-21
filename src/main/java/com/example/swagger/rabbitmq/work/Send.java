package com.example.swagger.rabbitmq.work;

import com.example.swagger.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: work模型生产者
 * @author: gmf
 * @date: Created in 2020/1/21 15:56
 * @version:
 * @modified By:
 */
public class Send {
    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws Exception, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //循环发布任务
        for (int i = 0; i < 50 ; i++) {
            //消息内容
            String msg = "task..."+i;
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println(" [x] Sent '" + msg + "'");
            Thread.sleep(i*2);
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
