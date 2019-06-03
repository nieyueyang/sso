package com.deyun.sso.service;

import com.deyun.common.constants.Constants;
import com.deyun.common.util.CookieUtil;
import com.deyun.user.dto.AppUser;
import com.deyun.user.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

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

    public String login(HttpServletResponse response, String account, String password){
        String token = appUserService.login(account, password);
        CookieUtil.save(Constants.HEADER_STRING,token , (int)Constants.EXPIRATION_TIME,"localhost" ,response);
        return token;
    }

    public int register(AppUser appUser) throws Exception {
        return appUserService.register(appUser);
    }

}
