package com.example.newsrecommendation.intercepter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.Data;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/24
 * @Version 1.0
 */

public class RetelimiterDemo {

    private  RateLimiter rateLimiter;
    public RetelimiterDemo(int limitCount){
        this.rateLimiter = RateLimiter.create(limitCount);
    }

    public boolean tryAcquire(){
        return rateLimiter.tryAcquire();
    }
}

