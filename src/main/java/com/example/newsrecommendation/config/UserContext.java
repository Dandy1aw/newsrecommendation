package com.example.newsrecommendation.config;

import com.example.newsrecommendation.entity.User;

/**
 * @Description  定义一个线程私有变量用于存放登录后的用户，不用每次都取用户
 * @Author 11103882
 * @Date 2019/9/12
 * @Version 1.0
 */
public class UserContext {
    private static  ThreadLocal<User> userThreadLocal =new ThreadLocal<>();

    public static User getUserThreadLocal(){
        return userThreadLocal.get();
    }

    public static void setUserThreadLocal(User user){
        userThreadLocal.set(user);
    }
}
