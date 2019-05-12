package com.deyun.user.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: nieyy
 * @Date: 2019/4/21 16:16
 * @Version 1.0
 * @Description:
 */
public class AuthUser implements UserDetails,Serializable {

    private String id;
    private String account;                 //用户名
    private String password;                //密码
   // private String salt;                   //盐
    //private List<Role> authorities;       //权限集合
    boolean accountNonExpired =true;        //账户是否未过期,true 未过期  false 过期
    boolean accountNonLocked = true;         //账户是否未锁定
    boolean credentialsNonExpired = true;    //密码是否未过期
    boolean enabled = true;                  //账户是否可用
    boolean tokenExpired = true;             //token是否为过期, true 未过期  false 过期

    public AuthUser(AppUser appUserser) {
        //super();
        this.id = appUserser.getId();
        this.account = appUserser.getAccount();
        this.password = appUserser.getPassword();
        //this.salt = appUserser.getSalt();
        //this.authorityKind = appUserser.getAuthorityKind();
    }

    public AuthUser(){ }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        //list.add(new SimpleGrantedAuthority(authorityKind));
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }
}
