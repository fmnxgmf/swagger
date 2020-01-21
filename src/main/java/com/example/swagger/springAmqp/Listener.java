package com.example.swagger.springAmqp;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 18:02
 * @version:
 * @modified By:
 */
@Component
public class Listener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "spring.test.queue",durable = "true"),
            exchange = @Exchange(
                    value = "spring.test.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"#.#"}
    ))
    public void listener(String msg){
        System.out.println("接收到消息: "+msg);
    }
}
