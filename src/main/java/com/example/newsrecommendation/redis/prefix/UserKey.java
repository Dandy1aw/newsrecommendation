package com.example.newsrecommendation.redis.prefix;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/3
 * @Version 1.0
 */
public class UserKey extends BasePrefix{


    public static UserKey userToken = new UserKey(3600*24,"token");

    private UserKey(int time, String prefix) {
        super(time, prefix);
    }

    private UserKey(String prefix) {
        super(prefix);
    }
}
