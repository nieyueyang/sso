package com.deyun.user.config;

import com.deyun.user.filter.TokenAuthenticationFilter;
import com.deyun.user.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: nieyy
 * @Date: 2019/4/19 8:54
 * @Version 1.0
 * @Description:
 */

@Configuration     //启用自定义配置
@EnableWebSecurity   //禁用Boot的默认Security配置
@EnableGlobalMethodSecurity(prePostEnabled = true)   //启用Security注解，例如最常用的@PreAuthorize。
public class SecuiityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private RestAuthenticationAccessDeniedHandler restAuthenticationAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity
                .exceptionHandling().accessDeniedHandler(restAuthenticationAccessDeniedHandler).and()
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/login","/register").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加JWT filter
        httpSecurity.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    @Autowired
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        //校验用户
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder());
//        auth.parentAuthenticationManager(authenticationManagerBean());
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() throws Exception {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web){
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/assets/**");
        web.ignoring().antMatchers("/favicon.ico");
        web.ignoring().antMatchers("/druid/**");

    }


    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() throws Exception {
        return new TokenAuthenticationFilter(authenticationManagerBean());
    }


}
