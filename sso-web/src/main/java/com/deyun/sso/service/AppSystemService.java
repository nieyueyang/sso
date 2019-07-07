package com.deyun.sso.service;

import com.deyun.mybatis.mapper.BaseDaoService;
import com.deyun.sso.dao.AppSystemDao;
import com.deyun.sso.pojo.AppSystem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: nieyy
 * @Date: 2019/6/24 22:53
 * @Version 1.0
 * @Description:
 */
@Service
public class AppSystemService {

    @Autowired
    AppSystemDao appSystemDao;
    @Autowired
    BaseDaoService baseDaoService;
    @Autowired
    RedisTemplate redisTemplate;

    public PageInfo<AppSystem> queryFroPage(int pageNum,int pageSize,Map map){
        PageHelper.startPage(pageNum,pageSize, (String)map.get("orderBy"));
        List <AppSystem> list = appSystemDao.queryForPage(map);
        PageInfo<AppSystem> PageUser = new PageInfo<>(list);
        return PageUser;
    }

    public List<AppSystem> queryFroList(Map map){
        String rediskey = AppSystem.class.getName();
        List <AppSystem> list = (List <AppSystem>) redisTemplate.opsForValue().get(rediskey);
        if (list == null ){
            list = appSystemDao.queryForPage(map);
            redisTemplate.opsForValue().set(rediskey,list,30,TimeUnit.MINUTES);
        }
        return list;
    }

    public int addAppSystem(AppSystem appSystem) throws Exception {
        return baseDaoService.insert(appSystem);
    }

    public int updateAppSystem(AppSystem appSystem,Map map) throws Exception {
        return baseDaoService.update(appSystem, map);
    }

    public int deleteAppSystem(List<String> list){
        return baseDaoService.deleteBatch("app_system", list);
    }



}
