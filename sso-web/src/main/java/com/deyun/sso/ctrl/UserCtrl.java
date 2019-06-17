package com.deyun.sso.ctrl;

import com.deyun.common.annotation.ParaNotNull;
import com.deyun.common.dto.Result;
import com.deyun.sso.service.UserService;
import com.deyun.user.dto.AppUser;
import com.deyun.user.dto.AuthUser;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/4/27 19:49
 * @Version 1.0
 * @Description:
 */

@RestController
@RequestMapping("/user")
public class UserCtrl {

    @Autowired
    private UserService userService;

    @ApiOperation(value="分页查询", notes="分页查询")
    @ParaNotNull(ParaName = {"pageNum","pageSize"})
    @PostMapping("/queryForPage")
    public Result queryForPage(@RequestBody Map map){

        int pageNum = (int)map.get("pageNum");
        int pageSize = (int)map.get("pageSize");
        PageInfo <AppUser> list = userService.queryForPage(pageNum,pageSize,map);
        return new Result(list);
    }

    /**
     * 单条查询
     * @return
     */
    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @ParaNotNull(ParaName = {"account"})
    @GetMapping(value = "/{account}")
    public AppUser selectByAccount(@PathVariable("account") String account){
        return userService.selectByAccount(account);
    }

    @ApiOperation(value="注册用户", notes="注册用户")
    @ParaNotNull(ParaName = {"account","name"})
    @PostMapping
    public Result addAppUser(@RequestBody AppUser appUser) throws Exception {
        String account = ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
        String name = ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
        appUser.setCreateAccount(account);
        appUser.setCreateName(name);
        appUser.setCreateDate(Instant.now());
        int i = userService.addAppUser(appUser);
        Result result = new Result(i);
        return result;
    }

    @ApiOperation(value="更新用户信息", notes="更新用户信息")
    @ParaNotNull(ParaName = {"id"})
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Result updateAppUser(@PathVariable("id") String id,@RequestBody AppUser appUser) throws Exception {
        String account = ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
        Map map = new HashMap();
        map.put("id", id);
        appUser.setId(id);
        appUser.setModifyAccount(account);
        appUser.setModifyDate(Instant.now());
        int i = userService.updateAppUser(appUser,map);
        Result result = new Result(i);
        return result;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public Result deleteAppUser(@PathVariable("id") String id){
        String[] strs = id.split(",");
        List<String> list= Arrays.asList(strs);
        int i = userService.deleteAppRole(list);
        Result result = new Result(i);
        return result;
    }


}
