package com.deyun.sso.service;

import com.deyun.sso.dao.AppSystemFuncDao;
import com.deyun.sso.pojo.AppSystemFunc;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/6/26 23:40
 * @Version 1.0
 * @Description:
 */
@Service
public class AppSystemFuncService {

    @Autowired
    private AppSystemFuncDao appSystemFuncDao;

    public PageInfo<AppSystemFunc> queryFroPage(int pageNum, int pageSize, Map map){
        PageHelper.startPage(pageNum,pageSize, (String)map.get("orderBy"));
        List<AppSystemFunc> list = appSystemFuncDao.queryForPage(map);
        PageInfo<AppSystemFunc> PageUser = new PageInfo<>(list);
        return PageUser;
    }

}
