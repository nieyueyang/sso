package com.deyun.sso.dao;

import com.deyun.sso.pojo.AppSystem;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/6/24 22:37
 * @Version 1.0
 * @Description:
 */
@Repository
public class AppSystemDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public List<AppSystem> queryForPage(Map map) {
        return sqlSessionTemplate.selectList("com.appsystem.queryForList", map);
    }


}
