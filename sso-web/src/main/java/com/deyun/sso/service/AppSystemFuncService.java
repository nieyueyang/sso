package com.deyun.sso.service;

import com.deyun.mybatis.mapper.BaseDaoService;
import com.deyun.sso.dao.AppSystemFuncDao;
import com.deyun.sso.pojo.AppSystem;
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
    @Autowired
    BaseDaoService baseDaoService;

    public PageInfo<AppSystemFunc> queryFroPage(int pageNum, int pageSize,String orderBy,AppSystemFunc appSystemFunc){
        PageHelper.startPage(pageNum,pageSize, orderBy);
        List<AppSystemFunc> list = appSystemFuncDao.queryForPage(appSystemFunc);
        PageInfo<AppSystemFunc> PageUser = new PageInfo<>(list);
        return PageUser;
    }

    public List<AppSystemFunc> queryForFuncGroup(String funcGroupId){
        return appSystemFuncDao.queryForFuncGroup(funcGroupId);
    }

    public int addAppSystemFunc(AppSystemFunc appSystemFunc) throws Exception {
        return baseDaoService.insert(appSystemFunc);
    }

    public int updateAppSystemFunc(AppSystemFunc appSystemFunc,Map map) throws Exception {
        return baseDaoService.update(appSystemFunc,map);
    }

    public int deleteAppSystemFunc(List<String> list){
        return baseDaoService.deleteBatch("app_system_func", list);
    }



}
