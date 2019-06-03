package com.deyun.user.service;

import com.deyun.common.domain.QueryParameter;
import com.deyun.user.dto.AppRole;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/5/23 17:12
 * @Version 1.0
 * @Description:
 */
public interface AppRoleService {

    List<AppRole> selectForList(QueryParameter queryParameter);

    int addAppRole(AppRole appRole) throws Exception;

    int updateAppRole(AppRole appRole,QueryParameter queryParameter) throws Exception;

    int deleteAppRole(QueryParameter queryParameter);

}
