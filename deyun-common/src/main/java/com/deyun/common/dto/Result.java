package com.deyun.common.dto;

/**
 * @Author: nieyy
 * @Date: 2019/4/28 23:14
 * @Version 1.0
 * @Description: 返回结果类
 */
public class Result<T> {
    private int code;
    private String msg;
    private T t;

    public Result(){ }

    public Result(int code, String msg, T t){
        this.code = code;
        this.msg = msg;
        this.t = t;
    }

    public Result(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
