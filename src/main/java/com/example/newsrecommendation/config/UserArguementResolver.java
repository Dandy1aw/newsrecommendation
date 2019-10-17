package com.example.newsrecommendation.config;

import com.example.newsrecommendation.entity.User;
import com.example.newsrecommendation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Description      自定义参数解析器 用于解析controller方法上的 用户参数注入
 * @Author 11103882
 * @Date 2019/9/12
 * @Version 1.0
 */
@Service
public class UserArguementResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserService userService;
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        /*获取可支持的参数类型，解析controller 上的方法参数*/
        Class<?> clazz = methodParameter.getParameterType();
        /* 若是 User 类才进行下一步*/
        return clazz == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //返回该线程私有的用户变量
        return UserContext.getUserThreadLocal();
    }
}
