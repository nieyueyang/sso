package com.deyun.user.constants;

/**
 * @Author: nieyy
 * @Date: 2019/5/18 16:44
 * @Version 1.0
 * @Description: 常量类
 */
public class Constants {
    //登录用户ID
    public final static String USER_ID = "id";
    //登录用户账号
    public final static String ACCOUNT = "account";
    //登录用户名称
    public final static String USER_NAME = "name";
    //过期时间
    public final static String TOKEN_EXPIRATION = "expirationTime";
    //过期时间
    public final static String TOKEN_REFRESH = "refreshTime";
    //过期时间  10分钟(毫秒)
    public final static long EXPIRATION_TIME = 1000 * 60 * 10  ;
    //刷新时间  5分钟 (毫秒)
    public final static long REFRESH_TIME =  1000 * 60 * 5;
    //获取TOKEN的KEY
    public static final String HEADER_STRING = "Authorization";

}
