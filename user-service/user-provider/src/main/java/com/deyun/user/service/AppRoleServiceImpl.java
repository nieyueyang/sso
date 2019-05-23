package com.deyun.user.service;

import com.deyun.user.dao.AppRoleDao;
import com.deyun.user.dto.AppRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<AppRole> selectAppRole(AppRole appRole) {
        return appRoleDao.selectAppRole(appRole);
    }

}
