package com.example.newsrecommendation.result;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/29
 * @Version 1.0
 */
public enum CodeMessage {
    SUCCESS(0, "访问成功"),
    SERVER_ERROR(100, "系统异常"),
    NOT_FOUND_USER(201,"找不到该用户"),
    PASSWORD_IS_EMPTY(202,"密码为空"),
    PASSWORD_ERROR(203,"密码错误")
    ;
    private final String msg;
    private final int code;

    private CodeMessage(int code, String msg) {
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
