package com.deyun.sso.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.deyun.common.annotation.ParaNotNull;
import com.deyun.common.dto.Result;
import com.deyun.sso.service.UserRoleService;
import com.deyun.user.dto.AppUserRole;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/6/18 18:51
 * @Version 1.0
 * @Description:
 */
@RestController
@RequestMapping("/userrole")
public class UserRoleCtrl {

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value="查询用户角色", notes="查询用户角色，distribution=0已分配的用户，distribution=1未分配的用户")
    @ParaNotNull(ParaName = {"id","pageNum","pageSize"})
    @GetMapping(value = "/{id}/{pageNum}/{pageSize}/{distribution}")
    public Result queryUserRoleForPage(String roleCode, String roleName,
                                       @PathVariable("id") String id,
                                       @PathVariable("pageNum") int pageNum,
                                       @PathVariable("pageSize") int pageSize,
                                       @PathVariable("distribution") int distribution){
        Map map = new HashMap();
        map.put("id", id);
        map.put("roleCode",roleCode);
        map.put("roleName",roleName);
        //map.put("orderBy","" );
        PageInfo<AppUserRole> list = userRoleService.queryUserRoleForPage(pageNum,pageSize,distribution,map);
        return new Result(list);
    }

    @ApiOperation(value="保存授予用户的角色", notes="保存授予用户的角色")
    @PostMapping
    public Result saveUserRole(@RequestParam("userRole") String userRole) throws Exception {
        List<AppUserRole> list = JSONObject.parseArray(userRole, AppUserRole.class);
        int i = userRoleService.saveUserRole(list);
        return new Result(i);
    }

    @ApiOperation(value="删除授权用户的角色", notes="删除授权用户的角色")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public Result deleteUserRole(@PathVariable String id){
        String[] strs = id.split(",");
        List<String> list = Arrays.asList(strs);
        int i = userRoleService.deleteUserRole(list);
        return new Result(i);
    }


}
