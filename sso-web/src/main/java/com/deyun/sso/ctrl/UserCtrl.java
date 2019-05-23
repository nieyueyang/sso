package com.deyun.sso.ctrl;

import com.deyun.common.annotation.ParaNotNull;
import com.deyun.sso.service.UserService;
import com.deyun.user.dto.AppUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation(value="用户登陆", notes="用户登陆")
    @PostMapping(value = "/login")
    public String login(String account,String password){
        return userService.login(account, password);
    }

    @ApiOperation(value="注册用户", notes="注册用户")
    @ParaNotNull(ParaName = {"account","name"})
    @PostMapping(value = "/register")
    public void register(@RequestBody AppUser appUser) throws Exception {
        userService.register(appUser);
    }

    /**
     * 单条查询
     * @return
     */
    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @ParaNotNull(ParaName = {"account"})
    @GetMapping(value = "/selectByAccount/{account}")
    public AppUser selectByAccount(HttpServletRequest request,@PathVariable("account") String account){
        return userService.selectByAccount(account);
    }
    


}
