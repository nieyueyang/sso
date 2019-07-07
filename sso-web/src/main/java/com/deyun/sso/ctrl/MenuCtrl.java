package com.deyun.sso.ctrl;

import com.deyun.common.dto.Result;
import com.deyun.sso.service.MenuService;
import com.deyun.user.dto.AppMenu;
import com.deyun.user.dto.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/5/28 21:45
 * @Version 1.0
 * @Description:
 */
@RestController
@RequestMapping("/menu")
public class MenuCtrl {

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/{menuId}/{parentId}" ,method = RequestMethod.GET)
    public Result queryForList(@PathVariable("menuId") String menuId,
                                @PathVariable("parentId") String parentId){
        AppMenu appMenu = new AppMenu();
        //appMenu.setMenuId(menuId);
        appMenu.setParentId("-1");
        List<AppMenu> list = menuService.queryForList(appMenu);
        AppMenu appMenu1 = list.get(0);
        appMenu.setParentId(appMenu1.getId());
        List<AppMenu> list2 = menuService.queryForList(appMenu);
        appMenu1.setChildren(list2);

        List list3 = new ArrayList();
        list3.add(appMenu1);

        return new Result(list3);
    }


    @RequestMapping(value = "" ,method = RequestMethod.GET)
    public Result queryMenuAll(){

        List<AppMenu> list = menuService.queryMenuAll();
        return new Result(list);
    }


}
