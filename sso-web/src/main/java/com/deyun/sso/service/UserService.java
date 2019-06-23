package com.deyun.sso.service;

import com.deyun.common.domain.PageParameter;
import com.deyun.user.dto.AppRole;
import com.deyun.user.service.AppUserService;
import com.deyun.user.dto.AppUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public PageInfo<AppUser> queryForPage(int pageNum, int pageSize, Map map){
        PageHelper.startPage(pageNum,pageSize, (String)map.get("orderBy"));
        List<AppUser> list = appUserService.queryForList(map);
        PageInfo<AppUser> PageUser = new PageInfo<>(list);
        return PageUser;
    }

    public AppUser queryByAccount(String account){
        AppUser appUser = (AppUser) redisTemplate.opsForValue().get("user");
        if (appUser == null){
            appUser = appUserService.queryByAccount(account);
            redisTemplate.opsForValue().set("user", appUser,30, TimeUnit.MINUTES);
        }
        return appUser;
    }

    public int addAppUser(AppUser appUser) throws Exception {
        return appUserService.register(appUser);
    }

    public int updateAppUser(AppUser appUser, Map map) throws Exception {
        return appUserService.updateAppUser(appUser,map);
    }

    public int deleteAppRole(List<String> list){
        return appUserService.deleteAppUser(list);
    }


}
