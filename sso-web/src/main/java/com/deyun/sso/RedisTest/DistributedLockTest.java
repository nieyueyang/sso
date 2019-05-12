package com.deyun.sso.RedisTest;

import com.deyun.redis.core.RedisService;

import java.util.UUID;

/**
 * @Author: nieyy
 * @Date: 2019/4/14 15:36
 * @Version 1.0
 * @Description:
 */
public class DistributedLockTest implements Runnable {

    private RedisService redisService;

    public DistributedLockTest(RedisService redisService){
        this.redisService = redisService;
    }

    @Override
    public void run() {
        String identifier = UUID.randomUUID().toString();
        System.out.println(Thread.currentThread().getName() + "-> 尝试获取锁：" + identifier);
        boolean flag = redisService.tryLock("upateOrder",identifier , 200000, 5000);
        if(flag){
            System.out.println(Thread.currentThread().getName() + "-> 成功获得锁：" + identifier);
            try {
                Thread.sleep(1000);
                redisService.releaseLock("upateOrder", identifier);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
