package com.oldwang;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * redis key相关操作
 */
public class RedisKeyOperation {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.2.110",6379);
        System.out.println(jedis.ping());
        System.out.println("清空数据" + jedis.flushDB());
        System.out.println("判断某个键是否存在" + jedis.exists("username"));
        System.out.println("新增<'username','oldwang'>的键值对" + jedis.set("username","oldwang"));
        System.out.println("新增<'password','password'>的键值对" + jedis.set("password","password"));
        System.out.println("系统中所有的键如下");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println("删除password" + jedis.del("password"));
        System.out.println("判断password是否存在" + jedis.exists("password"));
        System.out.println("查看username所存储的值的类型" + jedis.type("username"));
        System.out.println("随机返回key空间一个" + jedis.randomKey());
        System.out.println("重命名key" + jedis.rename("username","name"));
        System.out.println("取出改名后的name" + jedis.get("name"));
        System.out.println("按索引查询" + jedis.select(0));
        System.out.println("删除当前选中数据库所有的key" + jedis.flushDB());
        System.out.println("返回当前数据库中key的数目" + jedis.dbSize());
        System.out.println("删除所有数据库中的所有key" + jedis.flushAll());
    }
}
