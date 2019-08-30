package com.example.newsrecommendation.service;

import com.example.newsrecommendation.entity.User;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/29
 * @Version 1.0
 */
public interface UserService {

    User getByid(Integer id);

    User getByUsername(String username);



}
