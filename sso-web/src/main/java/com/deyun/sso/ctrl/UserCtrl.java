package com.deyun.sso.ctrl;

import com.deyun.mybatis.mapper.BaseDaoService;
import com.deyun.sso.service.UserService;
import com.deyun.user.dto.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/register")
    public void register(@RequestBody AppUser appUser) throws Exception {
        userService.register(appUser);
    }

    /**
     * 单条查询
     * @return
     */
    @RequestMapping("/selectByAccount")
    public AppUser selectByAccount(HttpServletRequest request, @RequestParam("account") String account ){
        return userService.selectByAccount(account);
    }

}
