package com.deyun.user.dao;

import com.deyun.user.dto.AppUser;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/4/19 22:47
 * @Version 1.0
 * @Description:
 */
@Repository
public class AppUserDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;


    public AppUser queryAppUserByAccount(String account){
        return sqlSessionTemplate.selectOne("com.appuser.queryAppUserByAccount", account);
    }

    public AppUser queryByAccount(String account){
        return sqlSessionTemplate.selectOne("com.appuser.queryByAccount", account);
    }

    public List<AppUser> queryForList(Map map){
        return sqlSessionTemplate.selectList("com.appuser.queryForPage",map);
    }

}
