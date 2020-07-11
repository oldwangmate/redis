package com.oldwang;

import redis.clients.jedis.Jedis;

/**
 * List相关操作
 */
public class RedisListOperation {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.2.110",6379);
        jedis.flushDB();

        System.out.println("======添加List=====");
        jedis.lpush("collections","ArrayList","Vector","stack");
        jedis.lpush("collections","HashSet");
        jedis.lpush("collections","TreeSet");
        jedis.lpush("collections","TreeMap");
        System.out.println("collections的内容" + jedis.lrange("collections",0,-1));
        System.out.println("collections区间0-3的元素" + jedis.lrange("collections",0,3));
        System.out.println("==============================");
        //删除列表指定的值，第二个参数为删除的个数（有重复时） 后add进去的元素先被删除，类似于出栈
        System.out.println("删除指定元素个数" + jedis.lrem("collections",2,"HashSet"));
        System.out.println("删除0-3之间之外的元素" + jedis.ltrim("collections",0,3));
        System.out.println("collections列表出栈(左端)" + jedis.lpop("collections"));
    }
}
