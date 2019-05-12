package com.deyun.redis.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @Author: nieyy
 * @Date: 2019/4/13 20:21
 * @Version 1.0
 * @Description:
 */

public class DistributedLock {
    private Logger logger = LoggerFactory.getLogger(DistributedLock.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 获取分布式锁
     * @param key      设置的KEY
     * @param value    设置线程的ID
     * @param acquireTimeout  获取锁的过期时间
     * @param lockTimeout   锁本身的过期时间
     * @return
     */
    public boolean acquireLock(String key, String value, long acquireTimeout,long lockTimeout){
        boolean result = false;
        key = "lock:" + key;
        long endTime = System.currentTimeMillis() + acquireTimeout;
        while(System.currentTimeMillis() < endTime){
            result = stringRedisTemplate.opsForValue().setIfAbsent(key, value, lockTimeout, TimeUnit.MILLISECONDS);
            if(result){
                return result;
            }

            if (stringRedisTemplate.getExpire(key) == -1){
                stringRedisTemplate.expire(key, lockTimeout, TimeUnit.MILLISECONDS);
            }
            try{
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        return result;
    }

    public boolean releaseLock(String key,String identifier){
        key = "lock:" + key;
        boolean isRelease = false;
        try{
            while(true){
                stringRedisTemplate.watch(key);
                String value = stringRedisTemplate.opsForValue().get("key");
                if (identifier.equals(value)){
                    isRelease = stringRedisTemplate.opsForValue().getOperations().delete(key);
                }else{
                    isRelease = false;
                }
            }
        }catch(Exception e){
            logger.info("分布式锁解锁失败！",e);
        }

        return isRelease;
    }


}
