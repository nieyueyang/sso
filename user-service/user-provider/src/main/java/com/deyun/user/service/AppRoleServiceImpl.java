package com.deyun.user.service;

import com.deyun.common.domain.QueryParameter;
import com.deyun.mybatis.mapper.BaseDaoService;
import com.deyun.user.dao.AppRoleDao;
import com.deyun.user.dto.AppRole;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public List<AppRole> selectForList(Map map) {
        return appRoleDao.selectForList(map);
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
    public int deleteAppRole(List<String> list) {
        return baseDaoService.deleteBatch("app_role", list);
    }


}
