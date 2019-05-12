package com.deyun.redis.core;

import com.deyun.redis.util.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.UUID;

/**
 * @Author: nieyy
 * @Date: 2019/4/13 20:25
 * @Version 1.0
 * @Description:
 */
public class DistributedLockMic {

    /**
     * 获取Redis分布式锁
     * @param lockName  锁的名称
     * @param acquireTimeout  获取锁的过期时间
     * @param lockTimeout  锁本身的过期时间
     * @return
     */
    public String acquireLock(String lockName,long acquireTimeout,long lockTimeout){
        //保证释放锁的时候是同一个持有锁的人
        String identifier = UUID.randomUUID().toString().replace("-","");
        String lockKey = "lock:" + lockName;
        int lockExpire = (int) (lockTimeout/1000);
        Jedis jedis = null;
        try{
            jedis = RedisUtil.getJedis();
            long endTime = System.currentTimeMillis() + acquireTimeout;
            while(System.currentTimeMillis() < endTime){
                if (jedis.setnx(lockKey, identifier) == 1){
                    jedis.expire(lockKey, lockExpire);
                    return identifier;  //或得锁成功
                }
                //如果锁没有超时时间，则设置一个超时时间
                if(jedis.ttl(lockKey) == -1){
                    jedis.expire(lockKey, lockExpire);
                }
                try{
                    Thread.sleep(100);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }finally{
            jedis.close();
        }

        return null;
    }

    public boolean releaseLock(String lockName,String identifier){
        System.out.println(lockName + "开始释放锁：" + identifier);
        String lockKey = "lock:" + lockName;
        boolean isRelease = false;
        Jedis jedis = null;
        try{
            jedis = RedisUtil.getJedis();
            while(true){
                jedis.watch(lockKey);
                if(identifier.equals(jedis.get(lockKey))){
                    Transaction transaction = jedis.multi();
                    transaction.del(lockKey);
                    if(transaction.exec().isEmpty()){
                        continue;
                    }
                    isRelease =true;
                }
                //  TODO 异常处理
                jedis.unwatch();
                break;
            }


        }finally {
            jedis.close();
        }

        return isRelease;
    }
}
