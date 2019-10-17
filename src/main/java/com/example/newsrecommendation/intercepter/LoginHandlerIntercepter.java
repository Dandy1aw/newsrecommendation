package com.example.newsrecommendation.intercepter;

import com.alibaba.fastjson.JSON;
import com.example.newsrecommendation.config.UserContext;
import com.example.newsrecommendation.entity.User;
import com.example.newsrecommendation.intercepter.limiter.AccessLimitService;
import com.example.newsrecommendation.result.CodeMessage;
import com.example.newsrecommendation.result.Result;
import com.example.newsrecommendation.service.Impl.UserServiceImpl;
import com.example.newsrecommendation.service.UserService;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * @Description   登录拦截器
 * @Author 11103882
 * @Date 2019/9/3
 * @Version 1.0
 */
@Component
public class LoginHandlerIntercepter extends HandlerInterceptorAdapter {
	@Autowired
    private UserService userService;

	@Autowired
	private AccessLimitService accessLimitService;

    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = getUser(request, response);
		// 用户存在的情况下，放入私有线程中用于后续操作
//		if (user==null){
//		    //todo 异常
//		}
		UserContext.setUserThreadLocal(user);
		HandlerMethod handlerMethod =(HandlerMethod)handler;
		return super.preHandle(request,response,handler);
//		AcessLimiter acessLimiter =handlerMethod.getMethodAnnotation(AcessLimiter.class);
//		if (acessLimiter==null){
//			return true;
//		}
//		boolean isLogin  =acessLimiter.isLogin();
//		if (isLogin) {
//			if (user==null) {
//				render(response, CodeMessage.NOT_LOGIN_ERROR);
//				return false;
//			}
//		}
//		if (accessLimitService.tryAcquire()){
//			return super.preHandle(request,response,handler);
//        }else {
//			render(response,CodeMessage.CLICK_LIMIT);
//			throw new RuntimeException();
//		}
	}

//	private static Boolean acquire(int clickLimit) {
//
//		  return rateLimiter.tryAcquire();
//	}

	/**
	 * render 方法为了 拦截的时候 输出到 浏览器，获得 response
	 */
	private void render(HttpServletResponse response, CodeMessage  codeMessage) throws IOException {
			/*注意 这里 输出的是 json 数据，所以 务必要定义 contentType 以及编码*/
			response.setContentType("application/json;charset=utf-8");
			OutputStream out = response.getOutputStream();
			String str = JSON.toJSONString(Result.error(codeMessage));//转化为Json传输出
			out.write(str.getBytes("UTF-8"));
			out.flush();
			out.close();
	}


	private User getUser(HttpServletRequest request, HttpServletResponse response) {
		/* 先通过 请求参数中 获取 token */
		String paramToken = request.getParameter(UserServiceImpl.USER_TOKEN);
		/* 请求参数中没有，去 cookie列表中找 可能之前登陆过 还保存过 cookie 且没过期 */
		String cookieToken = getCookieFromCookieList(request, UserServiceImpl.USER_TOKEN);
		// 没有登录过且请求中没有cookie
		if (StringUtils.isEmpty(paramToken) && StringUtils.isEmpty(cookieToken)) {
			return null;
		}
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
