package com.example.newsrecommendation.result;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/29
 * @Version 1.0
 */
public enum CodeMessage {

    SUCCESS(0, "访问成功"),
    /*系统异常*/
    SERVER_ERROR(100, "系统异常"),

    /* 用户登录 异常*/
    NOT_FOUND_USER(201,"找不到该用户"),
    NOT_LOGIN_ERROR(202,"用户为登录，请先登录"),
    PASSWORD_IS_EMPTY(202,"密码为空"),
    PASSWORD_ERROR(203,"密码错误"),

    CLICK_LIMIT(300,"点击次数过多");


    private final String msg;
    private final int code;

    CodeMessage(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
