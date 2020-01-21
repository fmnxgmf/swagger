package com.example.swagger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 18:07
 * @version:
 * @modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwaggerApplication.class)
public class MqDemo {
    @Autowired
    private AmqpTemplate aq;
    @Test
    public void testSend() throws InterruptedException {
        String msg ="hello, Spring boot amqp";
        this.aq.convertAndSend("spring.test.exchange","a.b", msg);
        // 等待10秒后再结束
        Thread.sleep(10000);
    }
}
