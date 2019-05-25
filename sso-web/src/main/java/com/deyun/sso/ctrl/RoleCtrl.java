package com.deyun.sso.ctrl;

import com.deyun.common.dto.Result;
import com.deyun.sso.service.RoleService;
import com.deyun.user.dto.AppRole;
import com.deyun.user.dto.AppUser;
import com.deyun.user.dto.AuthUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/5/23 16:59
 * @Version 1.0
 * @Description:
 */
@RestController
@RequestMapping("/role")
public class RoleCtrl {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value="获取角色列表", notes="获取角色列表")
    @GetMapping("/{id}")
    public Result selectForList(@PathVariable("id") String id){
        AppRole appRole = new AppRole();
        appRole.setId(id);
        List<AppRole> listRole= roleService.selectForList(appRole);
        Result result = new Result(listRole);
       return result;
    }

    @ApiOperation(value="添加角色", notes="添加角色")
    @PostMapping
    public Result addAppRole(@RequestBody AppRole appRole) throws Exception {
        String account = ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
        String name = ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
        appRole.setCreateAccount(account);
        appRole.setCreateName(name);
        appRole.setCreateDate(Instant.now());
        int i = roleService.addAppRole(appRole);
        Result result = new Result(i);
        return result;
    }

    @ApiOperation(value="更新角色信息", notes="更新角色信息")
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Result updateAppRole(HttpServletRequest request,@PathVariable("id") String id) throws Exception {
        String account = ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
        Map map = new HashMap();
        map.put("id", id);
        AppRole appRole = new AppRole();
        appRole.setRoleName(request.getParameter("roleName"));
        appRole.setRoleType(request.getParameter("roleType"));
        appRole.setModifyAccount(account);
        appRole.setModifyDate(Instant.now());
        int i = roleService.updateAppRole(appRole,map);
        Result result = new Result(i);
        return result;
    }

    @ApiOperation(value="删除角色信息", notes="删除角色信息")
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public Result deleteAppRole(@PathVariable("id") String id){
        Map map = new HashMap();
        map.put("id", id);
        int i = roleService.deleteAppRole(map);
        Result result = new Result(i);
        return result;
    }




}
