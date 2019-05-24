package com.deyun.user.service;

import com.deyun.mybatis.mapper.BaseDaoService;
import com.deyun.user.dao.AppRoleDao;
import com.deyun.user.dto.AppRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/5/23 17:29
 * @Version 1.0
 * @Description:
 */
@Service
public class AppRoleServiceImpl implements AppRoleService {

    @Autowired
    AppRoleDao appRoleDao;
    @Autowired
    BaseDaoService baseDaoService;

    @Override
    public List<AppRole> selectForList(AppRole appRole) {
        return appRoleDao.selectForList(appRole);
    }

    @Override
    public int addAppRole(AppRole appRole) throws Exception {
        return baseDaoService.insert(appRole);
    }

    @Override
    public int updateAppRole(AppRole appRole,Map map) throws Exception {
        return baseDaoService.update(appRole, map);
    }

    @Override
    public int deleteAppRole(Map map) {
        return baseDaoService.delete("app_role", map);
    }

}
