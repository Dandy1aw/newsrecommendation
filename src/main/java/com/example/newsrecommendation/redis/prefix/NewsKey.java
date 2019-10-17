package com.example.newsrecommendation.redis.prefix;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/16
 * @Version 1.0
 */
public class NewsKey  extends BasePrefix{
    private static final int EXPIRE_TIME = 60*60*24;

    public static NewsKey recommendKey = new NewsKey(EXPIRE_TIME,"recommendKey");

    public NewsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public NewsKey(String prefix) {
        super(prefix);
    }
}
