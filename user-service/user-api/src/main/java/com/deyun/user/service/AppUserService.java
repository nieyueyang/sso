package com.deyun.user.service;

import com.deyun.user.dto.AppUser;
import java.util.List;
import java.util.Map;

public interface AppUserService {

    String login(String account, String password);

    List<AppUser> queryForList(Map map);

    AppUser queryAppUserByAccount(String account);

    AppUser queryByAccount(String account);

    int register(AppUser appUser) throws Exception;

    int updateAppUser(AppUser appUser, Map map) throws Exception;

    int deleteAppUser(List<String> list);

}

