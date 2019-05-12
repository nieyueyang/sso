package com.deyun.sso.pojo;

import com.deyun.mybatis.annotation.Column;
import com.deyun.mybatis.annotation.Id;
import com.deyun.mybatis.annotation.Table;
import com.deyun.mybatis.annotation.Transient;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: nieyy
 * @Date: 2019/3/16 20:37
 * @Version 1.0
 * @describe:
 */
@Table("base_user")
public class User implements Serializable {

    @Column( "ID")
    @Id
    private String  id;
    private String userCode;
    @Transient
    private String userName;
    private String jobNumber;
    private String bankNum;
    private Integer qq;
    private BigDecimal creditAmount;
    private Float money1;
    private Double money2;
    private Date createTime2;
    private Timestamp createTime3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public Integer getQq() {
        return qq;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }

    public BigDecimal getCreateAmount() {
        return creditAmount;
    }

    public void setCreateAmount(BigDecimal createAmount) {
        this.creditAmount = createAmount;
    }

    public Float getMoney1() {
        return money1;
    }

    public void setMoney1(Float money1) {
        this.money1 = money1;
    }

    public Double getMoney2() {
        return money2;
    }

    public void setMoney2(Double money2) {
        this.money2 = money2;
    }

    public Date getCreateTime2() {
        return createTime2;
    }

    public void setCreateTime2(Date createTime2) {
        this.createTime2 = createTime2;
    }

    public Timestamp getCreateTime3() {
        return createTime3;
    }

    public void setCreateTime3(Timestamp createTime3) {
        this.createTime3 = createTime3;
    }
}
