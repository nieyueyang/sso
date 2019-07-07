package com.deyun.sso.service;

import com.deyun.mybatis.mapper.BaseDaoService;
import com.deyun.sso.dao.AppFuncGroupDao;
import com.deyun.sso.pojo.AppFuncGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/6/30 21:49
 * @Version 1.0
 * @Description:
 */
@Service
public class AppFuncGroupService {

    @Autowired
    private AppFuncGroupDao appFuncGroupDao;
    @Autowired
    private BaseDaoService baseDaoService;

    public PageInfo<AppFuncGroup> queryFroPage(int pageNum, int pageSize, String orderBy, AppFuncGroup appFuncGroup){
        PageHelper.startPage(pageNum,pageSize, orderBy);
        List<AppFuncGroup> list = appFuncGroupDao.queryForPage(appFuncGroup);
        PageInfo<AppFuncGroup> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public int addFuncGroup(AppFuncGroup appFuncGroup) throws Exception {
        return baseDaoService.insert(appFuncGroup);
    }

    public int updateFuncGroup(AppFuncGroup appFuncGroup,Map map) throws Exception {
        return baseDaoService.update(appFuncGroup,map);
    }

    public int deleteAppFuncGroup(List<String> list){
        return baseDaoService.deleteBatch("app_func_group", list);
    }


}
