package com.deyun.sso.service;

import com.deyun.user.dto.AppUser;
import com.deyun.user.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: nieyy
 * @Date: 2019/5/25 18:36
 * @Version 1.0
 * @Description:
 */
@Service
public class LoginService {

    @Autowired
    private AppUserService appUserService;

    public String login(String account,String password){
        return appUserService.login(account, password);
    }

    public int register(AppUser appUser) throws Exception {
        return appUserService.register(appUser);
    }

}
