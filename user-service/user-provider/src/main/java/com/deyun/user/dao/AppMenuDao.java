package com.deyun.user.dao;

import com.deyun.user.dto.AppMenu;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: nieyy
 * @Date: 2019/5/28 21:32
 * @Version 1.0
 * @Description:
 */
@Repository
public class AppMenuDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public List<AppMenu> queryForList(AppMenu appMenu){
        return sqlSessionTemplate.selectList("com.appMenu.selectForList",appMenu);
    }




}
