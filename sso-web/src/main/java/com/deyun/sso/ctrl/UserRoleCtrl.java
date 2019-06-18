package com.deyun.sso.ctrl;

import com.deyun.common.annotation.ParaNotNull;
import com.deyun.common.dto.Result;
import com.deyun.sso.service.UserRoleService;
import com.deyun.user.dto.AppUserRole;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @ApiOperation(value="查询用户拥有的角色", notes="查询用户拥有的角色")
    @ParaNotNull(ParaName = {"id","pageNum","pageSize"})
    @GetMapping(value = "/{id}/{pageNum}/{pageSize}")
    public Result queryUserRoleForPage(@PathVariable("id") String id,
                                       @PathVariable("pageNum") int pageNum,
                                       @PathVariable("pageSize") int pageSize){
        Map map = new HashMap();
        map.put("id", id);
        //map.put("orderBy","" );
        PageInfo<AppUserRole> list = userRoleService.queryUserRoleForPage(pageNum,pageSize,map);
        return new Result(list);
    }


}
