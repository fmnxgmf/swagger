package com.example.swagger.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/20 20:26
 * @version:
 * @modified By:
 */
@RestController
public class RedisContoller {
    @Qualifier("stringRedisTemplate")
    @Autowired
    private StringRedisTemplate redisTemplate;
    @GetMapping ("/redis")
    public String demo(){
        //redisTemplate.opsForValue().set("name","czc");
        String name = (String) redisTemplate.opsForValue().get("name");
        return name;
    }
}
