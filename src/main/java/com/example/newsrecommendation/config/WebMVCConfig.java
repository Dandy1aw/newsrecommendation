package com.example.newsrecommendation.config;

import com.example.newsrecommendation.intercepter.LoginHandlerIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/3
 * @Version 1.0
 */

@Configuration
public class WebMVCConfig  extends WebMvcConfigurationSupport {

    @Autowired
    LoginHandlerIntercepter loginHandlerIntercepter;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(loginHandlerIntercepter).addPathPatterns("/**")
                .excludePathPatterns("/login","/login/confirm");
        super.addInterceptors(registry);
    }
}
