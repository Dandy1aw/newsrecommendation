package com.example.newsrecommendation.intercepter.limiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/24
 * @Version 1.0
 */
@Component
public class AccessLimiterIntercepter extends HandlerInterceptorAdapter {
    @Autowired
    AccessLimitService accessLimitService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (accessLimitService.tryAcquire()){
            return super.preHandle(request,response,handler);
        }else {
            throw new RuntimeException();
        }
    }
}
