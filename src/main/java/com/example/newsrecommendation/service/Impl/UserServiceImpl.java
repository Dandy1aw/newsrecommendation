package com.example.newsrecommendation.service.Impl;

import com.example.newsrecommendation.dao.UserDao;
import com.example.newsrecommendation.entity.User;
import com.example.newsrecommendation.redis.RedisService;
import com.example.newsrecommendation.redis.prefix.UserKey;
import com.example.newsrecommendation.result.CodeMessage;
import com.example.newsrecommendation.result.Result;
import com.example.newsrecommendation.service.UserService;
import com.example.newsrecommendation.vo.LoginVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/29
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    public static  final  String USER_TOKEN = "USER_TOKEN";

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;



    @Override
    public User getByid(Integer id) {
        return userDao.getById(id);

    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }


    @Override
    public Result checkUser(LoginVo loginVo, HttpServletResponse response) {
        String username = loginVo.getUsername();
        if (username==null)
            return Result.error(CodeMessage.SERVER_ERROR);
        User user = userDao.getByUsername(username);
        if (user==null){
            /*这里需要 抛出异常来显示 为什么false  false由于 找不到用户不存在还是 密码错误*/
            return Result.error(CodeMessage.NOT_FOUND_USER);
        }
        String dbPassword = user.getPassword();
        String inputPassword = loginVo.getPassword();
        if (!inputPassword.equals(dbPassword)){
            return Result.error(CodeMessage.PASSWORD_ERROR);
        }
        /*验证通过 完成登录 写入cookie*/
        String token = UUID.randomUUID().toString().replace("-","");
        addCookie(token,user,response);
        return Result.success(true);
    }

    @Override
    public User getUserByToken(HttpServletResponse response, String realToken) {
        if (realToken==null || realToken.isEmpty())
            return null;
        User user = redisService.get(UserKey.userToken,realToken,User.class);
        if (user!=null){
            /*延长cookie时间*/
            addCookie(realToken,user,response);
        }
        return user;
    }

    /**
     *       Cookie设置
     * @param token
     * @param user
     * @param response
     */
    private void addCookie(String token, User user, HttpServletResponse response) {
        /*同时将用户对象 通过 前缀和token值  写入缓存 用于后面拦截器 取出*/
        redisService.set(UserKey.userToken,token,user);
        Cookie cookie = new Cookie(USER_TOKEN,token);
        cookie.setMaxAge(UserKey.userToken.getExpireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
