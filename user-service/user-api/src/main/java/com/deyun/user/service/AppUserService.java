package com.deyun.user.service;


import com.deyun.user.dto.AppUser;

public interface AppUserService {

    String login(String account, String password);

    int register(AppUser appUser) throws Exception;

    AppUser selectAppUserByAccount(String account);

    AppUser selectByAccount(String account);

}

