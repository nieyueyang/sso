package com.deyun.sso.ctrl;

import com.deyun.common.annotation.ParaNotNull;
import com.deyun.common.dto.Result;
import com.deyun.sso.service.UserService;
import com.deyun.user.dto.AppUser;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;;import java.util.Map;

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

    /**
     * 分页查询
     * @return
     */
    @ApiOperation(value="分页查询", notes="分页查询")
    @ParaNotNull(ParaName = {"pageNum","pageSize"})
    @GetMapping(value = "/selectForPage/{pageNum}/{pageSize}")
    public Result selectForPage(HttpServletRequest request,@PathVariable("pageNum") int pageNum,
                                @PathVariable("pageSize") int pageSize,
                                @PathVariable("orderby") String orderby){

        String account = request.getParameter("account");
        String name = request.getParameter("name");
        AppUser appUser = new AppUser();
        appUser.setAccount(account);
        appUser.setName(name);
        PageInfo<AppUser> list = userService.selectForPage(pageNum,pageSize,orderby,appUser);
        return new Result(list);
    }
    


}
