package com.deyun.common.exception;

import com.deyun.common.enums.ErrorUserMsgEnum;

import java.text.MessageFormat;

/**
 * @Author: nieyy
 * @Date: 2019/5/14 23:08
 * @Version 1.0
 * @Description: 全局异常类
 */
public class GlobalException extends Exception {
    public GlobalException() {
        super();
    }

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(ErrorUserMsgEnum errorUserMsgEnum) {
        super(errorUserMsgEnum.getCode()+ " : " + errorUserMsgEnum.getMsg());
        this.globalExceptionCode = errorUserMsgEnum.getCode();
        this.globalExceptionMsg = errorUserMsgEnum.getMsg();
    }

    private int globalExceptionCode;
    private String globalExceptionMsg;

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]",this.globalExceptionCode,this.globalExceptionMsg);
    }

    public int getGlobalExceptionCode() {
        return globalExceptionCode;
    }

    public void setGlobalExceptionCode(int globalExceptionCode) {
        this.globalExceptionCode = globalExceptionCode;
    }

    public String getGlobalExceptionMsg() {
        return globalExceptionMsg;
    }

    public void setGlobalExceptionMsg(String globalExceptionMsg) {
        this.globalExceptionMsg = globalExceptionMsg;
    }
}
