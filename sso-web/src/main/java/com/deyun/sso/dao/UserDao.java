package com.deyun.sso.dao;

import com.deyun.sso.pojo.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    List<User> selectUser(Map map);
}
