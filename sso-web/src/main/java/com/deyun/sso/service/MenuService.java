package com.deyun.sso.service;

import com.deyun.user.dto.AppMenu;
import com.deyun.user.service.AppMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: nieyy
 * @Date: 2019/5/28 21:44
 * @Version 1.0
 * @Description:
 */
@Service
public class MenuService {

    @Autowired
    AppMenuService appMenuService;

    public List<AppMenu> selectForList(AppMenu appMenu){
        return appMenuService.selectForList(appMenu);
    }
}
