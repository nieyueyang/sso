package com.deyun.user.dao;

import com.deyun.common.domain.PageParameter;
import com.deyun.user.dto.AppUser;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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


    public AppUser selectAppUserByAccount(String account){
        return sqlSessionTemplate.selectOne("com.appuser.selectAppUserByAccount", account);
    }

    public AppUser selectByAccount(String account){
        return sqlSessionTemplate.selectOne("com.appuser.selectByAccount", account);
    }

    public List<AppUser> selectForPage(PageParameter pageParameter){
        return sqlSessionTemplate.selectList("com.appuser.selectForPage",pageParameter);
    }

}
