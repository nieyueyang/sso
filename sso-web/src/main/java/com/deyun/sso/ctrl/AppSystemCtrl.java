package com.deyun.sso.ctrl;

import com.deyun.common.annotation.ParaNotNull;
import com.deyun.common.dto.Result;
import com.deyun.sso.pojo.AppSystem;
import com.deyun.sso.service.AppSystemService;
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
 * @Date: 2019/6/24 22:35
 * @Version 1.0
 * @Description:
 */
@RestController
@RequestMapping("/appsystem")
public class AppSystemCtrl {

    @Autowired
    private AppSystemService appSystemService;

    @ApiOperation(value="注册系统分页查询", notes="注册系统分页查询")
    @ParaNotNull(ParaName = {"pageNum","pageSize"})
    @PostMapping("/queryForPage")
    public Result queryForPage(@RequestBody Map map){

        int pageNum = (int)map.get("pageNum");
        int pageSize = (int)map.get("pageSize");
        PageInfo <AppSystem> list = appSystemService.queryFroPage(pageNum,pageSize,map);
        return new Result(list);
    }

    @ApiOperation(value="注册系统全部数据", notes="注册系统全部数据")
    @GetMapping
    public Result queryForList(Map map){

        List <AppSystem> list = appSystemService.queryFroList(map);
        return new Result(list);
    }

    @ApiOperation(value="注册系统", notes="注册系统")
    @ParaNotNull(ParaName = {"systemCode","systemName","domain"})
    @PostMapping
    public Result addAppSystem(@RequestBody AppSystem appSystem) throws Exception {
        String account = ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
        String name = ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
        appSystem.setCreateAccount(account);
        appSystem.setCreateName(name);
        appSystem.setCreateDate(Instant.now());
        int i = appSystemService.addAppSystem(appSystem);
        Result result = new Result(i);
        return result;
    }

    @ApiOperation(value="更新系统信息", notes="更新系统信息")
    @ParaNotNull(ParaName = {"id"})
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Result updateAppSystem(@PathVariable("id") String id, @RequestBody AppSystem appSystem ) throws Exception {
        String account = ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
        Map map = new HashMap();
        map.put("id", id);
        appSystem.setId(id);
        appSystem.setModifyAccount(account);
        appSystem.setModifyDate(Instant.now());
        int i = appSystemService.updateAppSystem(appSystem,map);
        Result result = new Result(i);
        return result;
    }

    @ApiOperation(value="删除角色信息", notes="删除角色信息")
    @ParaNotNull(ParaName = {"id"})
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public Result deleteAppSystem(@PathVariable("id") String id){
        String[] strs = id.split(",");
        List<String> list= Arrays.asList(strs);
        int i = appSystemService.deleteAppSystem(list);
        Result result = new Result(i);
        return result;
    }



}
