package com.example.newsrecommendation.service.Impl;

import com.example.newsrecommendation.dao.UserDao;
import com.example.newsrecommendation.entity.User;
import com.example.newsrecommendation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/29
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User getByid(Integer id) {
        return userDao.getById(id);

    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }
}
