package com.deyun.sso.ctrl;

import com.deyun.common.annotation.ParamNotNull;
import com.deyun.sso.service.UserService;
import com.deyun.user.dto.AppUser;
import com.deyun.user.dto.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;;

/**
 * @Author: nieyy
 * @Date: 2019/4/27 19:49
 * @Version 1.0
 * @Description:
 */

@RestController
public class UserCtrl {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(String account,String password){
        return userService.login(account, password);
    }

    @ParamNotNull(ParaName = {"account","name"})
    @PostMapping("/register")
    public void register(@RequestBody AppUser appUser) throws Exception {
        userService.register(appUser);
    }

    /**
     * 单条查询
     * @return
     */

    @ParamNotNull(ParaName = {"account"})
    @RequestMapping("/selectByAccount")
    public AppUser selectByAccount(HttpServletRequest request, @RequestParam("account") String account){
        Authentication aa = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) aa.getPrincipal();
        return userService.selectByAccount(account);
    }

}
