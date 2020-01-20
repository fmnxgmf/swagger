package com.example.swagger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/20 19:43
 * @version:
 * @modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class RedisDemo {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    public void redisString(){
        String name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
    @Test
    public void redisHash(){
        redisTemplate.opsForHash().put("hash","key","hashDemo");
        String o = (String)redisTemplate.opsForHash().get("hash", "key");
        System.out.println("o = " + o);
    }

}
