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

    public List<AppUserRole> queryUserRoleForList(Map map);

    public int BatchSave(List<AppUserRole> list) throws Exception;
}
