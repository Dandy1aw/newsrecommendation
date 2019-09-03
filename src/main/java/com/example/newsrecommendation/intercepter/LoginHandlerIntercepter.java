package com.example.newsrecommendation.intercepter;

import com.example.newsrecommendation.entity.User;
import com.example.newsrecommendation.service.Impl.UserServiceImpl;
import com.example.newsrecommendation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description   登录拦截器
 * @Author 11103882
 * @Date 2019/9/3
 * @Version 1.0
 */
@Component
public class LoginHandlerIntercepter extends HandlerInterceptorAdapter {
	private final Logger log = LoggerFactory.getLogger(LoginHandlerIntercepter.class);

	@Autowired
    private UserService userService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/* 打印请求日志 */
		log.info("request  path :{},uri :{}", request.getServletPath(), request.getRequestURI());
		User user = getUser(request, response);
		if (user==null) {
			return false;
		}
		return true;
	}

	private User getUser(HttpServletRequest request, HttpServletResponse response) {
		/* 先通过 请求参数中 获取 token */
		String paramToken = request.getParameter(UserServiceImpl.USER_TOKEN);
		/* 请求参数中没有，去 cookie列表中找 可能之前登陆过 还保存过 cookie 且没过期 */
		String cookieToken = getCookieFromCookieList(request, UserServiceImpl.USER_TOKEN);
		String realToken = paramToken!=null?paramToken:cookieToken;
		return userService.getUserByToken(response,realToken);
	}

	private String getCookieFromCookieList(HttpServletRequest request, String userToken) {
		Cookie[] cookies = request.getCookies();
		if (cookies==null || cookies.length==0){
		    return null;
        }
		for (Cookie cookie : cookies){
		    if (cookie.getName().equals(userToken)){
		        /*如果找到 这个 cookie 返回cookie值 用于 取用户对象*/
		        return cookie.getValue();
            }
        }
		return null;
	}
}
