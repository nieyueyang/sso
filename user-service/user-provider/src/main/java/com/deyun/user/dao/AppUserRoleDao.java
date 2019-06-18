package com.deyun.user.dao;

import com.deyun.user.dto.AppUserRole;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/6/18 18:47
 * @Version 1.0
 * @Description:
 */
@Repository
public class AppUserRoleDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public List<AppUserRole> queryUserRoleForList(Map map) {
        return sqlSessionTemplate.selectList("com.appUserRole.queryUserRoleForList",map);
    }

}
