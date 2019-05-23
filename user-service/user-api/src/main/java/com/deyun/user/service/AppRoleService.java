package com.deyun.user.service;

import com.deyun.user.dto.AppRole;
import java.util.List;

/**
 * @Author: nieyy
 * @Date: 2019/5/23 17:12
 * @Version 1.0
 * @Description:
 */
public interface AppRoleService {

    List<AppRole> selectAppRole(AppRole appRole);

}
