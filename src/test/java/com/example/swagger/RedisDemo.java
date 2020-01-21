package com.example.swagger;
import com.example.swagger.redis.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;


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
    public void redisString2(){
        redisTemplate.opsForValue().set("name","czc");
        String name = redisTemplate.opsForValue().get("name");
        Boolean name1 = redisTemplate.expire("name", 60, TimeUnit.SECONDS);
        System.out.println("name1 = " + name1);
        System.out.println(name);
    }
    @Test
    public void redisString(){
        String name = redisTemplate.opsForValue().get("name");
        Boolean name1 = redisTemplate.expire("name", 60, TimeUnit.SECONDS);
        System.out.println("name1 = " + name1);
        System.out.println(name);
    }
    @Test
    public void redisHash(){
        redisTemplate.opsForHash().put("hash","key","hashDemo");
        String o = (String)redisTemplate.opsForHash().get("hash", "key");
        System.out.println("o = " + o);
    }
    @Test
    public void redisHash2(){
        BoundHashOperations<String, Object, Object> hashOps = redisTemplate.boundHashOps("user");
        // 操作hash数据
        hashOps.put("name", "jack");
        hashOps.put("age", "21");
        // 获取单个数据
        Object name = hashOps.get("name");
        System.out.println("name = " + name);
        //获取所有数据
        Map<Object, Object> entries = hashOps.entries();
        for (Map.Entry<Object, Object> entry : entries.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
    /**
     * 测试工具类
     */
    @Resource
    private RedisUtil redisUtil;
    @Test
    public void demoUtil(){
        redisUtil.set("time","timedemo");
        boolean bool = redisUtil.expire("time", 60);
        System.out.println("bool = " + bool);
        long name = redisUtil.getExpire("time");
        System.out.println("name = " + name);

        System.out.println(redisUtil.get("time"));
    }
}
