package com.deyun.sso.service;

import com.deyun.user.dto.AppUserRole;
import com.deyun.user.service.AppUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/6/18 18:52
 * @Version 1.0
 * @Description:
 */
@Service
public class UserRoleService {

    @Autowired
    AppUserRoleService appUserRoleService;

    public PageInfo<AppUserRole> queryUserRoleForPage(int pageNum, int pageSize, Map map){

        PageHelper.startPage(pageNum,pageSize, (String)map.get("orderBy"));
        List<AppUserRole> list = appUserRoleService.queryUserRoleForList(map);
        PageInfo<AppUserRole> PageUserRole = new PageInfo<>(list);
        return PageUserRole;
    }
}
