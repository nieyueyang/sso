package com.deyun.user.dao;

import com.deyun.common.domain.QueryParameter;
import com.deyun.user.dto.AppRole;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/5/23 17:30
 * @Version 1.0
 * @Description:
 */
@Repository
public class AppRoleDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public List<AppRole> selectForList(Map map) {
        return sqlSessionTemplate.selectList("com.approle.selectForList", map);
    }




}
