package com.deyun.sso.ctrl;

import com.deyun.common.dto.Result;
import com.deyun.sso.service.MenuService;
import com.deyun.user.dto.AppMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Result selectForList(@PathVariable("menuId") String menuId,
                                @PathVariable("parentId") String parentId){
        AppMenu appMenu = new AppMenu();
        //appMenu.setMenuId(menuId);
        //appMenu.setParentId(parentId);
        List<AppMenu> list = menuService.selectForList(appMenu);
        return new Result(list);
    }


}
