package com.example.newsrecommendation.intercepter.limiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/24
 * @Version 1.0
 */
@Service
@Slf4j
public class AccessLimitService {
    RateLimiter rateLimiter= RateLimiter.create(3);
    //尝试获取令牌
    public boolean tryAcquire() {
        boolean flag = rateLimiter.tryAcquire();
        log.info(String.valueOf(flag));
        return flag;
    }
}
