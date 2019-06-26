package com.deyun.sso.service;

import com.deyun.common.domain.QueryParameter;
import com.deyun.mybatis.mapper.BaseDaoService;
import com.deyun.sso.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService1 {

    @Autowired
    private BaseDaoService baseDaoService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public User getUser(User user) {
        return sqlSessionTemplate.selectOne("com.user.queryUser",user);
    }

    public List<User> queryForList(User user) {
        List<User> list  = (List <User>) redisTemplate.opsForValue().get("user");
        if (list == null){
            list = sqlSessionTemplate.selectList("com.user.queryUser",user);
            list = sqlSessionTemplate.selectOne("com.user.queryUser",user);
            redisTemplate.opsForValue().set("user", list, 30, TimeUnit.MINUTES);
        }
        return list;
    }

    public PageInfo<User> pageForList(User user) {
        PageHelper.startPage(1 , 10,"user_code");
        List<User> list = sqlSessionTemplate.selectList("com.user.queryUser",user);
        PageInfo<User> PageUser = new PageInfo <>(list);
        return PageUser;
    }

    public int insert(User user) throws Exception {
        return baseDaoService.insert(user);
    }

    public int insertBtch(List<User> list) throws Exception {
        return baseDaoService.insertBatch(list);
    }

    public int update(User users,QueryParameter queryParameter) throws Exception {
        return baseDaoService.update(users,queryParameter);
    }

    public int delete(String tableName,QueryParameter queryParameter) throws Exception {
        return baseDaoService.delete(tableName,queryParameter);
    }

    public int deleteBatch(String tableName,List<String> list) throws Exception {
        return baseDaoService.deleteBatch(tableName,list);
    }

    @Transactional
    public void transientTest() throws Exception {
        long beginTime = System.currentTimeMillis();


        User users = new User();
        users.setId("cccccc");
        users.setUserCode("nyy");
        users.setUserName("用户");
        users.setJobNumber("10000000");
        users.setBankNum("bank_num");
        users.setQq(434593435);
        BigDecimal bigDecimal = BigDecimal.valueOf(1222.122);
        users.setCreateAmount(bigDecimal);
        float f = (float) 1003.11;
        users.setMoney1(f);
        double d = 2001.0001;
        users.setMoney2(d);
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        users.setCreateTime2(new Date());
        users.setCreateTime3(time1);

        baseDaoService.insert(users);
        System.out.println(System.currentTimeMillis() - beginTime );

    }




}
