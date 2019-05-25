package com.deyun.user.dto;

/**
 * @Author: nieyy
 * @Date: 2019/5/25 10:25
 * @Version 1.0
 * @Description:
 */
public class AppRoleUser {
    private String id;
    private String roleId;
    private String roleCode;
    private String userId;
    private String userAccount;
    private String tendId;
    private String sysId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getTendId() {
        return tendId;
    }

    public void setTendId(String tendId) {
        this.tendId = tendId;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }
}
