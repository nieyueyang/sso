package com.deyun.sso.service;

import com.deyun.user.dto.AppRole;
import com.deyun.user.service.AppRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/5/23 16:59
 * @Version 1.0
 * @Description:
 */
@Service
public class RoleService {

    @Autowired
    private AppRoleService appRoleService;

    public PageInfo<AppRole> selectForPage(int pageNum,int pageSize,Map map){

        PageHelper.startPage(pageNum,pageSize, (String)map.get("orderBy"));
        List <AppRole> list = appRoleService.selectForList(map);
        PageInfo<AppRole> PageUser = new PageInfo<>(list);
        return PageUser;
    }

    public int addAppRole(AppRole appRole) throws Exception {
        return appRoleService.addAppRole(appRole);
    }

    public int updateAppRole(AppRole appRole,Map map) throws Exception {
        return appRoleService.updateAppRole(appRole,map);
    }

    public int deleteAppRole(List<String> list){
        return appRoleService.deleteAppRole(list);
    }




}
