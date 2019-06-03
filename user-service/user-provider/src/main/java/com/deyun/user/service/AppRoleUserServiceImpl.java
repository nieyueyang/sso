package com.deyun.user.service;

import com.deyun.common.domain.QueryParameter;
import com.deyun.mybatis.mapper.BaseDaoService;
import com.deyun.user.dto.AppRoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/5/25 10:33
 * @Version 1.0
 * @Description:
 */
@Service
public class AppRoleUserServiceImpl implements AppRoleUserService {

    @Autowired
    BaseDaoService baseDaoService;

    @Override
    public int addAppRoleUser(AppRoleUser appRoleUser) throws Exception {
        return baseDaoService.insert(appRoleUser);
    }

    @Override
    public int updateAppRoleUser(AppRoleUser appRoleUser,QueryParameter queryParameter) throws Exception {
        return baseDaoService.update(appRoleUser, queryParameter);
    }

    @Override
    public int deleteAppRoleUser(QueryParameter queryParameter) {
        return baseDaoService.delete("app_role_user", queryParameter);
    }


}
