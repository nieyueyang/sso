package com.deyun.user.service;

import com.deyun.mybatis.mapper.BaseDaoService;
import com.deyun.user.dao.AppUserRoleDao;
import com.deyun.user.dto.AppUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/6/18 18:44
 * @Version 1.0
 * @Description:
 */
@Service
public class AppUserRoleServiceImpl implements AppUserRoleService {

    @Autowired
    AppUserRoleDao appUserRoleDao;
    @Autowired
    BaseDaoService baseDaoService;

    @Override
    public List<AppUserRole> queryUserRoleForList(Map map) {
        return appUserRoleDao.queryUserRoleForList(map);
    }

    @Override
    public List<AppUserRole> queryRoleForList(Map map) {
        return appUserRoleDao.queryRoleForList(map);
    }

    @Override
    public int saveUserRole(List<AppUserRole> list) throws Exception {
        return baseDaoService.insertBatch(list);
    }

    @Override
    public int deleteUserRole(List <String> list) {
        return baseDaoService.deleteBatch("app_user_role",list);
    }


}
