package com.deyun.user.service;

import com.deyun.common.domain.PageParameter;
import com.deyun.user.dto.AppUser;
import com.github.pagehelper.PageInfo;

public interface AppUserService {

    String login(String account, String password);

    int register(AppUser appUser) throws Exception;

    PageInfo<AppUser> selectForPage(PageParameter pageParameter);

    AppUser selectAppUserByAccount(String account);

    AppUser selectByAccount(String account);



}

