package com.deyun.sso.ctrl;

import com.deyun.common.annotation.ParaNotNull;
import com.deyun.common.constants.Constants;
import com.deyun.common.dto.Result;
import com.deyun.common.util.CookieUtil;
import com.deyun.sso.service.LoginService;
import com.deyun.user.dto.AppUser;
import com.deyun.user.dto.AuthUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

/**
 * @Author: nieyy
 * @Date: 2019/5/25 18:29
 * @Version 1.0
 * @Description:
 */
@RestController
public class LoginCtrl {

    @Autowired
    LoginService loginService;

    @ApiOperation(value="用户登陆", notes="用户登陆")
    @PostMapping(value = "/login")
    public Result login(HttpServletResponse response,String account, String password){
        String token = loginService.login(response,account, password);
        return new Result(token);
    }

    @ApiOperation(value="注册用户", notes="注册用户")
    @ParaNotNull(ParaName = {"account","name"})
    @PostMapping(value = "/register")
    public int register(@RequestBody AppUser appUser) throws Exception {
        String account = ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
        String name = ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
        appUser.setCreateAccount(account);
        appUser.setCreateName(name);
        appUser.setCreateDate(Instant.now());
        return loginService.register(appUser);
    }


}
