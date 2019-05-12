package com.deyun.redis;

import com.deyun.redis.core.DistributedLockMic;

/**
 * @Author: nieyy
 * @Date: 2019/4/13 21:27
 * @Version 1.0
 * @Description:
 */
public class RedisUnitTest extends Thread {

    @Override
    public void run() {
        while(true){
            DistributedLockMic distributedLock = new DistributedLockMic();
            String rs = distributedLock.acquireLock("upateOrder", 2000,5000);
            if(rs !=null){
                System.out.println(Thread.currentThread().getName() + "-> 成功获得锁：" + rs);
                try {
                    Thread.sleep(1000);
                    distributedLock.releaseLock("upateOrder", rs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        RedisUnitTest redisUnitTest = new RedisUnitTest();
        for(int i=0; i < 10 ; i++){
            new Thread(redisUnitTest,"tName" + i).start();

        }


    }
}
