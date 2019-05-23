package com.deyun.sso.service;

import com.deyun.user.dto.AppRole;
import com.deyun.user.service.AppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AppRole> roleService(AppRole appRole){
        return appRoleService.selectAppRole(appRole);
    }


}
