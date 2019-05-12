package com.deyun.sso.pojo;


import com.deyun.mybatis.annotation.Table;

/**
 * @Author: nieyy
 * @Date: 2019/3/24 20:20
 * @Version 1.0
 * @Description:
 */

@Table("base_dept")
public class BaseDept {
    private String id;
    private String deptCode;
    private String deptName;
    private String parentDeptCode;
    private String parentDeptName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getParentDeptCode() {
        return parentDeptCode;
    }

    public void setParentDeptCode(String parentDeptCode) {
        this.parentDeptCode = parentDeptCode;
    }

    public String getParentDeptName() {
        return parentDeptName;
    }

    public void setParentDeptName(String parentDeptName) {
        this.parentDeptName = parentDeptName;
    }

}
