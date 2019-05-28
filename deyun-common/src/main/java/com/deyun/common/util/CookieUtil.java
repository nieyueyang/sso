package com.deyun.common.util;

import com.alibaba.fastjson.JSON;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Author: nieyy
 * @Date: 2019/5/28 11:25
 * @Version 1.0
 * @Description: cookie操作类
 */

public class CookieUtil{

    public final static String COOKIE_NAME_PREFIX = "org.evanframework";

//    public static String read(String cookieName, HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        String returnV = null;
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (StringUtils.equals(cookie.getName(), COOKIE_NAME_PREFIX + cookieName)) {
//                    returnV = cookie.getValue();
//                    try {
//                        returnV = URLDecoder.decode(returnV, webencoding);
//                    } catch (UnsupportedEncodingException e) {
//                        throw new IllegalStateException(e);
//                    }
//                    break;
//                }
//            }
//        }
//        return returnV;
//    }
//
//    /**
//     * 璇诲彇Cookie,灏嗚Cookie鐨剉alue杞崲灞傚璞″湪杩斿洖
//     * <p>
//     * author:
//     * version: 2010-12-10 涓婂崍10:24:45 <br>
//     *
//     * @param c
//     * @param cookieName
//     * @param request
//     */
//    public static Object read(Class c, String cookieName, HttpServletRequest request,String webencoding) {
//        String cookieValue = read(cookieName, request,webencoding);
//        String json = null;
//        Object returnO = null;
//        if (cookieValue != null) {
//            try {
//                json = URLDecoder.decode(cookieValue, webencoding);
//            } catch (UnsupportedEncodingException e) {
//                throw new IllegalStateException(e);
//            }
//            returnO = JsonUtils.jsonToBean(json, c);
//        }
//
//        return returnO;
//    }
//
//    /**
//     *
//     * <p>
//     * author:
//     * version: 2010-12-10 涓婂崍10:26:35 <br>
//     *
//     * @param cookieName
//     * @param request
//     * @param response
//     */
//    public static void remove(String cookieName, HttpServletRequest request, HttpServletResponse response) {
//        Cookie cookie = new Cookie(COOKIE_NAME_PREFIX + cookieName, null);
//        cookie.setMaxAge(0);
//        cookie.setPath("/");
//        response.addCookie(cookie);
//    }


    public static void save(String cookieName, Object obj, int expiry,String domain,HttpServletResponse response) {
        String value;
        try {
            if (obj instanceof String || obj instanceof Integer || obj instanceof Long) {
                value = obj.toString();
            } else {
                value = JSON.toJSONString(obj);
            }
            value = URLEncoder.encode(value,"utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(Integer.valueOf(expiry));
        cookie.setPath("/");
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

}


