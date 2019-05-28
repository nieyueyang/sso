package com.deyun.user.service;

import com.deyun.user.dto.AppMenu;
import java.util.List;

/**
 * @Author: nieyy
 * @Date: 2019/5/28 21:22
 * @Version 1.0
 * @Description:
 */
public interface AppMenuService {

    List<AppMenu> selectForList(AppMenu appMenu);
}
