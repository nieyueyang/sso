package com.deyun.user.service;

import com.deyun.user.dto.AppUserRole;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/6/18 18:45
 * @Version 1.0
 * @Description:
 */
public interface AppUserRoleService {

    List<AppUserRole> queryUserRoleForList(Map map);

    List<AppUserRole> queryRoleForList(Map map);

    int saveUserRole(List<AppUserRole> list) throws Exception;

    int deleteUserRole(List<String> list);
}
