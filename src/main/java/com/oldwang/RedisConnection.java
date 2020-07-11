package com.oldwang;

import redis.clients.jedis.Jedis;


public class RedisConnection {
    public static void main(String[] args) {
        //1.new Jedis对象
        Jedis jedis = new Jedis("192.168.2.110",6379);
        //测试连接
        System.out.println(jedis.ping());
    }
}
