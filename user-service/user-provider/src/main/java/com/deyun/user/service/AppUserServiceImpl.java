package com.deyun.user.service;

import com.deyun.common.enums.ErrorUserMsgEnum;
import com.deyun.common.exception.UserException;
import com.deyun.mybatis.mapper.BaseDaoService;
import com.deyun.user.constants.Constants;
import com.deyun.user.dao.AppUserDao;
import com.deyun.user.dto.AppUser;
import com.deyun.user.dto.AuthUser;
import com.deyun.user.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public String login(String account, String password) {
        String token = "";
        try{
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(account,password);
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            AuthUser authUser = (AuthUser) authentication.getPrincipal();
            Map <String, Object> map = new HashMap();
            map.put(Constants.USER_ID, authUser.getId());
            map.put(Constants.ACCOUNT, authUser.getAccount());
            map.put(Constants.USER_NAME,authUser.getName());
            map.put(Constants.TOKEN_EXPIRATION,Instant.now().toEpochMilli() + Constants.EXPIRATION_TIME);
            map.put(Constants.TOKEN_REFRESH, Instant.now().toEpochMilli() + Constants.EXPIRATION_TIME + Constants.REFRESH_TIME);
            token = TokenUtil.generateToken(map);
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
