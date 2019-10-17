package com.example.newsrecommendation.intercepter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description     限流注解：限制规定时间内点击次数 同时判断是否登录
 * @Author 11103882
 * @Date 2019/9/24
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AcessLimiter {
    int clickLimit();
//    int secondsLimit();
    boolean isLogin();

}
