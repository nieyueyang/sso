package com.deyun.sso.ctrl;

import com.deyun.common.domain.QueryParameter;
import com.deyun.sso.pojo.User;
import com.deyun.sso.service.UserService1;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(value = "/user")
public class UserCtrl1 {
    @Autowired
    UserService1 userService1;

    /**
     * 单条查询
     * @return
     */
    @RequestMapping(value = "/getUser")
    public User getUser(){
        User user = new User();
        user.setId("1d18302017be46b6aa2c40837f92f698");
        return userService1.getUser(user);
    }

    /**
     * 多条查询
     * @return
     */

    @RequestMapping(value = "/queryForList")
    public List<User> queryForList() {
        User user = new User();
       // user.setUserCode("nyy");
        List<User> list = userService1.queryForList(user);
        return list;
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value = "/pageForList")
    public PageInfo<User> pageForList() {
        User user = new User();
        //user.setUserCode("nyy");
        return userService1.pageForList(user);
    }

    /**
     * 单条插入
     * @throws Exception
     */
    @RequestMapping(value = "/insert")
    public void insert() throws Exception {
        User users = new User();
        users.setId(UUID.randomUUID().toString().replace("-",""));
        users.setUserCode("nyy");
        users.setUserName("用户");
        users.setJobNumber("10000000");
        users.setBankNum("bank_num");
        users.setQq(82455504);
        BigDecimal bigDecimal = BigDecimal.valueOf(1222.122);
        users.setCreateAmount(bigDecimal);
        float f = (float) 1003.11;
        users.setMoney1(f);
        double d = 2001.0001;
        users.setMoney2(d);
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        users.setCreateTime2(new Date());
        users.setCreateTime3(time1);
        userService1.insert(users);
    }

    /**
     * 批量插入
     * @throws Exception
     */
    @RequestMapping(value = "/insertBatch")
    public void insertBatch() throws Exception {
        User user = new User();
        //user.setId("aaaaa");
        user.setUserCode("nyy");
        user.setUserName("用户");
        user.setJobNumber("10000000");
        user.setBankNum("bank_num");
        user.setQq(82455504);
        BigDecimal bigDecimal = BigDecimal.valueOf(1222.122);
        user.setCreateAmount(bigDecimal);
        float f = (float) 1003.11;
        user.setMoney1(f);
        double d = 2001.0001;
        user.setMoney2(d);
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        user.setCreateTime2(new Date());
        user.setCreateTime3(time1);

        User user2 = new User();
        //user2.setId();
        user2.setUserCode("nyy2");
        user2.setUserName("用户2");
        user2.setJobNumber("4444444");
        user2.setBankNum("bank_num2");
        user2.setQq(3333333);
        BigDecimal bigDecimal2 = BigDecimal.valueOf(444333.887);
        user2.setCreateAmount(bigDecimal2);
        float f2 = (float) 3322.11;
        user2.setMoney1(f2);
        double d2 = 33322111.88;
        user2.setMoney2(d2);
        Timestamp time4 = new Timestamp(System.currentTimeMillis());
        user2.setCreateTime2(new Date());
        user2.setCreateTime3(time4);
        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user2);
        userService1.insertBtch(list);
    }

    /**
     * 数据修改
     * @throws Exception
     */
    @RequestMapping(value = "/update")
    public void upate() throws Exception {
        User user = new User();
        user.setUserCode("nyy4");
        user.setUserName("用户4");
        user.setJobNumber("4444");
        user.setBankNum("bank_num4");
        user.setQq(44444444);
        BigDecimal bigDecimal = BigDecimal.valueOf(4444.4444);
        user.setCreateAmount(bigDecimal);
        float f = (float) 4444.4444;
        user.setMoney1(f);
        double d = 4444.4444;
        user.setMoney2(d);
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        user.setCreateTime2(new Date());
        user.setCreateTime3(time1);
        QueryParameter queryParameter = new QueryParameter();
        queryParameter.put("user_code", "nyy");
        // map.put("user_name", "聂月阳");
        userService1.update(user,queryParameter);
    }

    /**
     * 删除
     * @throws Exception
     */
    @RequestMapping(value = "delete")
    public void delete() throws Exception {
        QueryParameter queryParameter = new QueryParameter();
        queryParameter.put("user_code", "nyy");
        userService1.delete("base_user",queryParameter);
    }

    /**
     * 根据ID批量删除
     * @throws Exception
     */
    public void deleteBatch() throws Exception {
        List<String> list = new ArrayList();
        list.add("bd714102f3d24d4486e8df46227e60aa");
        list.add("4d1cd83788aa457ba3ddbf8bb1cbb0af");
        userService1.deleteBatch("base_user",list);
    }

    /**
     * 事务
     * @throws Exception
     */
    @RequestMapping(value = "/transient")
    public void transientTest() throws Exception {
        userService1.transientTest();
    }

}
