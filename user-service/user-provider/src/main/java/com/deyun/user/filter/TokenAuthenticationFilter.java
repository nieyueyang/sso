package com.deyun.user.filter;

import com.deyun.common.constants.Constants;
import com.deyun.common.dto.Result;
import com.deyun.common.enums.ErrorUserMsgEnum;
import com.deyun.common.util.CookieUtil;
import com.deyun.common.util.HttpUtil;
import com.deyun.user.service.AppUserService;
import com.deyun.user.dto.AuthUser;
import com.deyun.user.util.TokenUtil;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/4/21 23:20
 * @Version 1.0
 * @Description:  TOKEN解析拦截器
 */

public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    AppUserService appUserService;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws IOException, ServletException {
        if (!request.getRequestURI().contains("/login")){
            String token = request.getHeader(Constants.HEADER_STRING);
            if (StringUtils.isEmpty(token)){
                token = CookieUtil.read(Constants.HEADER_STRING, request,"UTF-8");
            }
            String account = "";
            if (StringUtil.isNotEmpty(token)) {
                try{
                    //TODO 考虑动态加盐，盐存放的问题
                    Map claims = TokenUtil.getUserDetailsFromToken(token);
                    account = (String) claims.get(Constants.ACCOUNT);
                    long expirationTime = (long) claims.get(Constants.TOKEN_EXPIRATION);
                    long refreshTime = (long) claims.get(Constants.TOKEN_REFRESH);
                    long currentTime = Instant.now().toEpochMilli();
                    AuthUser authUser = new AuthUser();
                    authUser.setId((String) claims.get(Constants.USER_ID));
                    authUser.setAccount(account);
                    authUser.setName((String) claims.get(Constants.USER_NAME));
                    if (currentTime < expirationTime ){
                        //token未过期
                        authUser.setAccountNonExpired(true);
                    }else if(currentTime > expirationTime && currentTime < refreshTime){
                        //TODO TOKEN过期，但未过刷新时间，返回TOKEN过期，由前台刷新获取TOKEN
                        priintLog(request,account,token);
                        Result result = new Result(ErrorUserMsgEnum.TOKEN_TIMEOUT.getCode(),ErrorUserMsgEnum.TOKEN_TIMEOUT.getMsg());
                        HttpUtil.responseWriteJson(response, result);
                        return;
                    }else{//TOKEN超过刷新时间，需要重新申请TOKEN
                        //打印日志
                        priintLog(request,account,token);
                        Result result = new Result(ErrorUserMsgEnum.LOGIN_TIMEOUT.getCode(),ErrorUserMsgEnum.LOGIN_TIMEOUT.getMsg());
                        HttpUtil.responseWriteJson(response, result);
                        return;
                    }

                    if (authUser.isAccountNonExpired() && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                authUser, null, authUser.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                                request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }

                }catch(Exception e){

//                response.setStatus(403);
//                response.setContentType("application/json; charset=utf-8");
//                response.setCharacterEncoding("UTF-8"); // 避免乱码
//                response.flushBuffer();
//                UserException userException = new UserException(ErrorUserMsgEnum.LOGIN_TIMEOUT);
//                throw userException;
                    // TOKEN 解析失败
                    Result result = new Result(ErrorUserMsgEnum.TOKEN_INVALID.getCode(),ErrorUserMsgEnum.TOKEN_INVALID.getMsg());
                    HttpUtil.responseWriteJson(response, result);
                    return;

                }
            }
        }
        chain.doFilter(request, response);
    }

    private void priintLog(HttpServletRequest request,String account,String token){
        String localIp = HttpUtil.getLocalIpAddr();
        String uri = request.getRequestURI();
        int port = request.getServerPort();
        //后台打印日志信息
        String url = "http://" + localIp + ":" + port + uri;
        logger.info("url:" + url + "   " + account + "  " + token );
    }



}
