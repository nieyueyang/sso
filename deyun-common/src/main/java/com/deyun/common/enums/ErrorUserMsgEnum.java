package com.deyun.common.enums;

/**
 * @Author: nieyy
 * @Date: 2019/5/1 9:16
 * @Version 1.0
 * @Description: user模块错误枚举类
 */
public enum ErrorUserMsgEnum {

    TOKEN_INVALID(20001,"无效的TOKEN"),
    TOKEN_TIMEOUT(20002,"TOKEN已过期"),
    LOGIN_TIMEOUT(20003,"登录超时，请您重新登录"),
    WRONG_USERNAME_OR_PASSWORD(20010,"用户名或密码错误"),
    WRONG_CHECKCODE(20011,"输入的验证码不正确"),
    PARAM_ERROR(20012,"参数不能为空"),
    CHECK_PWD_ERROR(20013,"密码校验失败");

    private int code;
    private String msg;

    private ErrorUserMsgEnum(int code,String msg){
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

    /**
     * 根据错误代码获取错误信息
     * @param code
     * @return
     */
    public static String getMsgByCode(int code){
        for(ErrorUserMsgEnum item : ErrorUserMsgEnum.values()){
            if(code==item.getCode()){
                return item.msg;
            }
        }
        return getUnknowErrorMsg();
    }

    public static String getUnknowErrorMsg(){
        return "未知错误信息";
    }
}