package com.example.newsrecommendation.config;

import com.example.newsrecommendation.intercepter.LoginHandlerIntercepter;
import com.example.newsrecommendation.intercepter.limiter.AccessLimiterIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
;import java.util.Arrays;
import java.util.List;

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

    @Autowired
    AccessLimiterIntercepter accessLimiterIntercepter;
    @Autowired
    UserArguementResolver userArguementResolver;
    /**
     *        注册拦截器，并规定拦截哪些资源
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截 spring 2.X之后的拦截器会拦截静态资源，需手动添加 然后还要在下面注册静态资源
        registry.addInterceptor(accessLimiterIntercepter)
                .excludePathPatterns(Arrays.asList("/login","/login/confirm","/static/**"));
        registry.addInterceptor(loginHandlerIntercepter).addPathPatterns("/**")
                .excludePathPatterns(Arrays.asList("/login","/login/confirm","/static/**"));
        super.addInterceptors(registry);
    }

    // 同时需要注册静态资源地址
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     *        将我们自定义的解析器放入spring的解析列表中
     * @param argumentResolvers
     */
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArguementResolver);
    }
}
