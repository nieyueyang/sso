package com.deyun.sso.dao;

import com.deyun.sso.pojo.AppSystemFunc;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/6/26 23:41
 * @Version 1.0
 * @Description:
 */
@Repository
public class AppSystemFuncDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public List<AppSystemFunc> queryForPage(Map map) {
        return sqlSessionTemplate.selectList("com.appsystemFunc.queryForList", map);
    }


}
