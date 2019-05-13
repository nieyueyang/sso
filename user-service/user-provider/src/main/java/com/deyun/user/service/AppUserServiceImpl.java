package com.deyun.user.service;

import com.deyun.common.enums.ErrorUserMsgEnum;
import com.deyun.common.exception.UserException;
import com.deyun.mybatis.mapper.BaseDaoService;
import com.deyun.user.AppUserService;
import com.deyun.user.dao.AppUserDao;
import com.deyun.user.dto.AppUser;
import com.deyun.user.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserDao appUserDao;
    @Autowired
    BaseDaoService baseDaoService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    //过期时间  10分钟(毫秒)
    private static final long EXPIRATION_TIME = 1000 * 60 * 10  ;
    //刷新时间  5分钟 (毫秒)
    private static final long REFRESH_TIME =  1000 * 60 * 5;

    @Override
    public String login(String account, String password) {
        String token = "";
        try{
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(account,password);
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = TokenUtil.generateToken(account,EXPIRATION_TIME,REFRESH_TIME);
            stringRedisTemplate.opsForValue().set(account, token, EXPIRATION_TIME + REFRESH_TIME , TimeUnit.MILLISECONDS);

        }catch(Exception e){
            throw new UserException(ErrorUserMsgEnum.WRONG_USERNAME_OR_PASSWORD);
        }
        return token;
    }

    @Override
    public int register(AppUser appUser) throws Exception {
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        return baseDaoService.insert(appUser);
    }

    @Override
    public AppUser selectAppUserByAccount(String account){
        return appUserDao.selectAppUserByAccount(account);
    }

    @Override
    public AppUser selectByAccount(String account) {
       return appUserDao.selectByAccount(account);
    }

}
