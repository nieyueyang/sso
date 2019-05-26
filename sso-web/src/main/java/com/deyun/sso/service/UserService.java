package com.deyun.sso.service;

import com.deyun.common.domain.PageParameter;
import com.deyun.user.service.AppUserService;
import com.deyun.user.dto.AppUser;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @Author: nieyy
 * @Date: 2019/4/27 19:51
 * @Version 1.0
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    RedisTemplate redisTemplate;

    public AppUser selectByAccount(String account){
        AppUser appUser = (AppUser) redisTemplate.opsForValue().get("user");
        if (appUser == null){
            appUser = appUserService.selectByAccount(account);
            redisTemplate.opsForValue().set("user", appUser,30, TimeUnit.MINUTES);
        }
        return appUser;
    }

    public PageInfo<AppUser> selectForPage(PageParameter pageParameter){
        return appUserService.selectForPage(pageParameter);
    }

}
