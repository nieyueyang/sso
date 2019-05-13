package com.deyun.sso.service;

import com.deyun.user.AppUserService;
import com.deyun.user.dto.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String login(String account,String password){
        return appUserService.login(account, password);
    }

    public int register(AppUser appUser) throws Exception {
        return appUserService.register(appUser);
    }

    public AppUser selectByAccount(String account){
        return appUserService.selectByAccount(account);
    }



}
