package com.deyun.sso.service;

import com.deyun.user.AuthService;
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
    private AuthService authService;

    public String login(String account,String password){
        return authService.login(account, password);
    }

//    public AppUser selectAppUserByAccount(String account){
//        return appUserService.selectAppUserByAccount(account);
//    }

//    public AppUser selectByAccount(String account){
//        return appUserService.selectByAccount(account);
//    }




}
