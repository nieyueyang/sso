package com.deyun.user.service;


import com.deyun.user.dto.AppUser;
import com.github.pagehelper.PageInfo;

public interface AppUserService {

    String login(String account, String password);

    int register(AppUser appUser) throws Exception;

    PageInfo<AppUser> selectForPage(int pageNum,int pageSize,String OrderBy,AppUser appUser);

    AppUser selectAppUserByAccount(String account);

    AppUser selectByAccount(String account);



}

