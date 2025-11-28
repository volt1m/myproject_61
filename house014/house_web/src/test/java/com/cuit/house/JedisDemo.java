package com.cuit.house;

import redis.clients.jedis.Jedis;

public class JedisDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.select(1);
        jedis.set("weclome","欢迎redis limeng");
        jedis.hset("user:1001","username","laowang");
        jedis.hset("user:1001","age","11");
        jedis.hset("user:1001","gender","男");

        jedis.lpush("teadName","中国","韩国","美国");
        jedis.sadd("NBA","骑士","勇士","火箭");
        jedis.zadd("english:scoreboard",97,"laowang1");
    }
}
