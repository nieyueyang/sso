package com.deyun.sso.ctrl;

import com.deyun.redis.core.RedisService;
import com.deyun.sso.RedisTest.DistributedLockTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: nieyy
 * @Date: 2019/4/14 12:46
 * @Version 1.0
 * @Description:
 */
@RestController
@RequestMapping("/redis")
public class RedisCtrl {

    @Autowired
    RedisService redisService;

    @RequestMapping("/acquireLock")
    public void DistributedLockTest(){
        DistributedLockTest distributedLockTest = new DistributedLockTest(redisService);
        for(int i=0; i < 100; i++){
            new Thread(distributedLockTest,"tName"+ i).start();
        }


    }




}
