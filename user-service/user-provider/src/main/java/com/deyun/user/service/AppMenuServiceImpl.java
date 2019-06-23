package com.deyun.user.service;

import com.deyun.user.dao.AppMenuDao;
import com.deyun.user.dto.AppMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: nieyy
 * @Date: 2019/5/28 21:19
 * @Version 1.0
 * @Description:
 */
@Service
public class AppMenuServiceImpl implements AppMenuService {

    @Autowired
    AppMenuDao appMenuDao;

    @Override
    public List<AppMenu> queryForList(AppMenu appMenu) {
        List<AppMenu> list = appMenuDao.queryForList(appMenu);
        return list;
    }


}
