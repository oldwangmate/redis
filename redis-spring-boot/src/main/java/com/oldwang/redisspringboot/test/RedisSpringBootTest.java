package com.oldwang.redisspringboot.test;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.oldwang.redisspringboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisSpringBootTest {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    public void test(){



        //操作字符串 相当于Spring
        //opsForList 操作List
        //opsForSet 操作Set
        //opsForHash 操作hash
        //opsForZSet  操作Zset
        //opsForGeo 操作Geo
        //opsForHyperLogLog 操作hyperLogLog

        //除了基本操作 我们常用的方法都可以直接通过redisTemplate操作，比如事务和基本的CRUD

        //获取连接
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();

        ValueOperations forValue = redisTemplate.opsForValue();
        forValue.set("key","oldwang");
        System.out.println(forValue.get("key"));
    }


    @Test
    public void test1() throws JsonProcessingException {
        User oldwang = new User("oldwang王", 2);
//        String string = new ObjectMapper().writeValueAsString(oldwang);
        //注意所有的对象必须序列化,否则会报错
        redisTemplate.opsForValue().set("user",oldwang);
        System.out.println( redisTemplate.opsForValue().get("user"));
    }
}
