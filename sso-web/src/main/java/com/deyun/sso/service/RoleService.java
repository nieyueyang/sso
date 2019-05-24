package com.deyun.sso.service;

import com.deyun.user.dto.AppRole;
import com.deyun.user.service.AppRoleService;
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

    public List<AppRole> selectForList(AppRole appRole){
        return appRoleService.selectForList(appRole);
    }

    public int addAppRole(AppRole appRole) throws Exception {
        return appRoleService.addAppRole(appRole);
    }

    public int updateAppRole(AppRole appRole,Map map) throws Exception {
        return appRoleService.updateAppRole(appRole,map);
    }

    public int deleteAppRole(Map map){
        return appRoleService.deleteAppRole(map);
    }


}
