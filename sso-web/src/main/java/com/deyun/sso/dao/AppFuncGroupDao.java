package com.deyun.sso.dao;

import com.deyun.sso.pojo.AppFuncGroup;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Author: nieyy
 * @Date: 2019/6/30 21:41
 * @Version 1.0
 * @Description:
 */
@Repository
public class AppFuncGroupDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public List<AppFuncGroup> queryForPage(AppFuncGroup appFuncGroup) {
        return sqlSessionTemplate.selectList("com.appFuncGroup.queryForList", appFuncGroup);
    }

}
