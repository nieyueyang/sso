package com.deyun.user.service;

import com.deyun.user.dto.AppRoleUser;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/5/25 10:27
 * @Version 1.0
 * @Description:
 */
public interface AppRoleUserService {

    int addAppRoleUser(AppRoleUser appRoleUser) throws Exception;

    int updateAppRoleUser(AppRoleUser appRoleUser,Map map) throws Exception;

    int deleteAppRoleUser(Map map);

}
