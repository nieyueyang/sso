package com.deyun.user.util;

import io.jsonwebtoken.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JWT 工具类
 * @Author: nieyy
 * @Date: 2019/4/20 15:07
 * @Version 1.0
 * @Description:
 */

public class TokenUtil implements Serializable {

    private static final String CLAIM_KEY_USERNAME = "account";
    private static final String CLAIM_KEY_EXPIRATION = "expirationTime";
    private static final String CLAIM_REFRESH = "refreshTime";
    private static final String CLAIM_salt = "aaabbb";
    /**
     * 签发JWT
     */
    public static String generateToken(String account,long expiration_time,long refresh_time) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(CLAIM_KEY_USERNAME, account);
        claims.put(CLAIM_KEY_EXPIRATION,Instant.now().toEpochMilli() + expiration_time);
        claims.put(CLAIM_REFRESH, Instant.now().toEpochMilli() + expiration_time + refresh_time);
        return Jwts.builder()  //这里其实就是new一个JwtBuilder，设置jwt的body
                .setId(UUID.randomUUID().toString())  //设置ID
                .setClaims(claims)  //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                //.setExpiration(new Date(Instant.now().toEpochMilli() + expiration_time))  //过期时间
                .setIssuedAt(new Date())  //iat: jwt的签发时间
                //.setSubject("user")  //说明
                .setIssuer("deyun") //签发者信息
                //.setAudience(account)  //接收用户
                .compressWith(CompressionCodecs.GZIP)  //数据压缩方式
                .signWith(SignatureAlgorithm.HS512, CLAIM_salt)  //设置签名使用的签名算法和签名使用的秘钥
                .compact();
    }

    /**
     * 解析token，获取UserDetails
     */

    public static Claims getUserDetailsFromToken(String token) {
        return  getClaimsFromToken(token);
    }

    /**
     * 根据token获取username
     */
    public static String getUsernameFromToken(String token,String salt) {
        return (String) getClaimsFromToken(token).get(CLAIM_KEY_USERNAME);
    }

    /**
     * 解析JWT
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(CLAIM_salt)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

}