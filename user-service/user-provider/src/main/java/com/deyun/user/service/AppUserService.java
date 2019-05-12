package com.deyun.user.service;

import com.deyun.user.dao.AppUserDao;
import com.deyun.user.dto.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: nieyy
 * @Date: 2019/4/19 22:35
 * @Version 1.0
 * @Description:
 */
@Service
public class AppUserService {

    @Autowired
    AppUserDao appUserDao;

    public AppUser selectAppUserByAccount(String account){
        return appUserDao.selectAppUserByAccount(account);
    }




}