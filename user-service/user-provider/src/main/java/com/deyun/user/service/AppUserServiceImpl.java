package com.deyun.user.service;

import com.deyun.common.constants.Constants;
import com.deyun.common.domain.PageParameter;
import com.deyun.common.enums.ErrorUserMsgEnum;
import com.deyun.common.exception.UserException;
import com.deyun.mybatis.mapper.BaseDaoService;
import com.deyun.user.dao.AppUserDao;
import com.deyun.user.dto.AppRole;
import com.deyun.user.dto.AppUser;
import com.deyun.user.dto.AuthUser;
import com.deyun.user.util.TokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
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
            String token = TokenUtil.generateToken(map);
            return token;
        }catch(Exception e){
            throw new UserException(ErrorUserMsgEnum.WRONG_USERNAME_OR_PASSWORD);
        }

    }

    @Override
    public List<AppUser> queryForList(Map map) {
        return appUserDao.queryForList(map);
    }

    @Override
    public AppUser queryAppUserByAccount(String account){
        return appUserDao.queryAppUserByAccount(account);
    }

    @Override
    public AppUser queryByAccount(String account) {
       return appUserDao.queryByAccount(account);
    }

    @Override
    public int register(AppUser appUser) throws Exception {
        String password = appUser.getPassword();
        if (StringUtils.isEmpty(password)){
            password = "888888";
        }
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        return baseDaoService.insert(appUser);
    }

    @Override
    public int updateAppUser(AppUser appUser, Map map) throws Exception {
        return baseDaoService.update(appUser, map);
    }


    @Override
    public int deleteAppUser(List<String> list) {
        return baseDaoService.deleteBatch("app_user", list);
    }

}
