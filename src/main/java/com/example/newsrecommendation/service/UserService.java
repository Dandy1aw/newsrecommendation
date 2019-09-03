package com.example.newsrecommendation.service;

import com.example.newsrecommendation.entity.User;
import com.example.newsrecommendation.result.Result;
import com.example.newsrecommendation.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/29
 * @Version 1.0
 */
public interface UserService {

    User getByid(Integer id);

    User getByUsername(String username);


    Result checkUser(LoginVo loginVo, HttpServletResponse response);

    User getUserByToken(HttpServletResponse response, String realToken);
}
