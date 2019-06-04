package com.deyun.sso.service;

import com.deyun.common.domain.PageParameter;
import com.deyun.common.domain.PageParameter2;
import com.deyun.common.domain.QueryParameter;
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

    public PageInfo<AppRole> selectForPage(Map map){
        PageHelper.startPage((int)map.get("pageNum"),(int)map.get("pageSize"), (String)map.get("orderBy"));
        List <AppRole> list = appRoleService.selectForList(map);
        PageInfo<AppRole> PageUser = new PageInfo<>(list);
        return PageUser;
    }

    public int addAppRole(AppRole appRole) throws Exception {
        return appRoleService.addAppRole(appRole);
    }

    public int updateAppRole(AppRole appRole,QueryParameter queryParameter) throws Exception {
        return appRoleService.updateAppRole(appRole,queryParameter);
    }

    public int deleteAppRole(QueryParameter queryParameter){
        return appRoleService.deleteAppRole(queryParameter);
    }


}
