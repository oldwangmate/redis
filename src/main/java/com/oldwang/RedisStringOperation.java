package com.oldwang;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * String相关操作
 */
public class RedisStringOperation {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.2.110",6379);
        jedis.flushDB();
        System.out.println("================增加数据===============");
        System.out.println(jedis.set("key1","value1"));
        System.out.println(jedis.set("key2","value2"));
        System.out.println(jedis.set("key3","value3"));
        System.out.println("删除key2" + jedis.del("key2"));
        System.out.println("获取key2" + jedis.get("key2"));
        System.out.println("修改key1的值" + jedis.set("key1","oldwangchange"));
        System.out.println("在key3后边加入值"+ jedis.append("key3","append"));
        System.out.println("key3的值"+jedis.get("key3"));
        System.out.println("增加多个键值对" + jedis.mset("key01","value01","key02","value02"));
        System.out.println("获取多个键值对" + jedis.mget("key01","key02"));
        System.out.println("删除多个键值对" + jedis.del("key01","key02"));
        System.out.println("获取多个键值对" + jedis.mget("key01","key02"));

        jedis.flushDB();
        System.out.println("====================新增键值对防止覆盖原来值===============");
        System.out.println(jedis.setnx("key1","value1"));
        System.out.println(jedis.setnx("key2","value2"));
        System.out.println(jedis.setnx("key2","value-new"));
        System.out.println(jedis.get("key1"));
        System.out.println(jedis.get("key2"));

        System.out.println("====================新增键值设置有效时间===============");
        System.out.println(jedis.setex("key3",2,"value3"));
        System.out.println(jedis.get("key3"));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis.get("key3"));

        System.out.println("====================获取原值，更新新值===============");
        System.out.println(jedis.getSet("key2","key2GetSet"));
        System.out.println(jedis.get("key2"));
        System.out.println("获取key的字符串" + jedis.getrange("key2",2,4));
    }
}
