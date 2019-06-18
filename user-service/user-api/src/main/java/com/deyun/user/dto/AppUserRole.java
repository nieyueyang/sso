package com.deyun.user.dto;

import com.deyun.mybatis.annotation.Id;
import com.deyun.mybatis.annotation.Table;
import com.deyun.mybatis.annotation.Transient;


/**
 * @Author: nieyy
 * @Date: 2019/5/23 17:02
 * @Version 1.0
 * @Description:
 */
@Table("app_user_role")
public class AppUserRole {

    @Id
    private String id;
    private String userId;
    private String userAccount;
    private String roleId;
    private String roleCode;
    private String isDelete;

    @Transient
    private String roleName;
    @Transient
    private String roleType;
    @Transient
    private String isactive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }
}
