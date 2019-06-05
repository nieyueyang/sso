package com.deyun.sso.ctrl;

import com.deyun.common.annotation.ParaNotNull;
import com.deyun.common.domain.PageParameter;
import com.deyun.common.domain.PageParameter2;
import com.deyun.common.domain.QueryParameter;
import com.deyun.common.dto.Result;
import com.deyun.sso.service.RoleService;
import com.deyun.user.dto.AppRole;
import com.deyun.user.dto.AppUser;
import com.deyun.user.dto.AuthUser;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
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
    @ParaNotNull(ParaName = {"pageNum","pageSize"})
    @PostMapping("/selectForPage")
    public Result selectForPage(@RequestBody Map map){
        int pageNum = (int)map.get("pageNum");
        if (pageNum == 0){
            pageNum = 1;
        }
        int pageSize = (int)map.get("pageSize");
        if (pageSize == 0){
            pageSize = 10;
        }
        PageInfo <AppRole> list = roleService.selectForPage(pageNum,pageSize,map);
        return new Result(list);
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
        QueryParameter queryParameter = new QueryParameter();
        queryParameter.put("id", id);
        AppRole appRole = new AppRole();
        appRole.setRoleName(request.getParameter("roleName"));
        appRole.setRoleType(request.getParameter("roleType"));
        appRole.setModifyAccount(account);
        appRole.setModifyDate(Instant.now());
        int i = roleService.updateAppRole(appRole,queryParameter);
        Result result = new Result(i);
        return result;
    }

    @ApiOperation(value="删除角色信息", notes="删除角色信息")
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public Result deleteAppRole(@PathVariable("id") String id){
        QueryParameter queryParameter = new QueryParameter();
        queryParameter.put("id", id);
        int i = roleService.deleteAppRole(queryParameter);
        Result result = new Result(i);
        return result;
    }




}
