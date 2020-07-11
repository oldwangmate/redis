package com.oldwang;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class Transactional {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.2.110",6379);
        jedis.flushDB();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello" ,"oldwang");
        jsonObject.put("name","old");
        //开启事务
        Transaction multi = jedis.multi();
        String string = jsonObject.toJSONString();
        jedis.watch(string);
        //事务操作
        try{
            multi.set("user1",string);
            int i = 1 /0; //抛出异常 事务执行失败
            multi.set("user2",string);
            multi.exec();
        }catch (Exception e){
            multi.discard(); // 放弃事务
            e.printStackTrace();
        }finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            //关闭连接
            jedis.close();
        }
    }
}
