package com.deyun.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: nieyy
 * @Date: 2019/4/13 20:33
 * @Version 1.0
 * @Description:
 */
public class RedisUtil {

    private static JedisPool jedisPool = null;
    static{
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        jedisPool = new JedisPool(config,"127.0.0.1",6379,10000,"deyun123");
    }

    public static Jedis getJedis (){
        return jedisPool.getResource();
    }


}
