package com.deyun.user.filter;

import com.deyun.common.dto.Result;
import com.deyun.common.enums.ErrorUserMsgEnum;
import com.deyun.common.util.HttpUtil;
import com.deyun.user.dto.AuthUser;
import com.deyun.user.service.AppUserService;
import com.deyun.user.util.TokenUtil;
import com.github.pagehelper.util.StringUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

/**
 * @Author: nieyy
 * @Date: 2019/4/21 23:20
 * @Version 1.0
 * @Description:  TOKEN解析拦截器
 */

public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    AppUserService appUserService;

    /**
     * 存放Token的Header Key
     */
    private static final String HEADER_STRING = "Authorization";
    private static final String ACCOUNT = "account";

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(HEADER_STRING);
        String account = "";
        if (StringUtil.isNotEmpty(token)) {
            try{
//                if (StringUtil.isNotEmpty(account)){
//                    appUser = appUserService.selectAppUserByAccount(account);
//                }
                //UserDetails userDetails = TokenUtil.getUserDetailsFromToken(token,appUser.getSalt());
                Claims claims = TokenUtil.getUserDetailsFromToken(token);
                account = (String) claims.get(ACCOUNT);
                //打印日志
                priintLog(request,account,token);
                long expirationTime = (long) claims.get("expirationTime");
                long refreshTime = (long) claims.get("refreshTime");
                long currentTime = Instant.now().toEpochMilli();
                AuthUser authUser = new AuthUser();
                authUser.setAccount((String) claims.get("account"));
                if (currentTime < expirationTime ){
                    //token未过期
                    authUser.setAccountNonExpired(true);
                }else if(currentTime > expirationTime && currentTime < refreshTime){
                    //TODO TOKEN过期，单还没过刷新时间，重新签发TOKEN
                }else{
                    //TODO TOKEN超过刷新时间，需要重新申请TOKEN
                    //authUser.setAccountNonExpired(false);
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
