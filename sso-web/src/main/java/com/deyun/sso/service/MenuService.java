package com.deyun.sso.service;

import com.deyun.user.dto.AppMenu;
import com.deyun.user.service.AppMenuService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
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

    public List<AppMenu> queryForList(AppMenu appMenu){
        return appMenuService.queryForList(appMenu);
    }

    public List<AppMenu> queryMenuAll(){
        AppMenu appMenu = new AppMenu();
        appMenu.setParentId("-1");
        List<AppMenu> list = appMenuService.queryForList(appMenu);
        return queryMenuForList(list);
    }

    public List<AppMenu> queryMenuForList(List<AppMenu> list){

        List<AppMenu> returnList = list;
        for(AppMenu appMenu : returnList){
            AppMenu appMenu1Query = new AppMenu();
            appMenu1Query.setParentId(appMenu.getId());
            List<AppMenu> children = appMenuService.queryForList(appMenu1Query);
            if (children != null && children.size() > 0){
                appMenu.setChildren(children);
                queryMenuForList(children);
            }
        }
        return returnList;

    }



}
