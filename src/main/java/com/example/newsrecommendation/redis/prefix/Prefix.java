package com.example.newsrecommendation.redis.prefix;

/**
 * @Description  用于设置 redis key 的前缀接口
 * @Author 11103882
 * @Date 2019/9/3
 * @Version 1.0
 */
public interface Prefix {

        int getExpireSeconds();//过期时间

        String getPrefix();//获取前缀的方法
}
