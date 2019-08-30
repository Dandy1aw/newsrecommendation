package com.example.newsrecommendation.controller;

import com.example.newsrecommendation.entity.User;
import com.example.newsrecommendation.result.CodeMessage;
import com.example.newsrecommendation.result.CodeMsg;
import com.example.newsrecommendation.result.Result;
import com.example.newsrecommendation.service.UserService;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/29
 * @Version 1.0
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/getuser/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getByid(id);
    }

    /**
     * 登录界面
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login/confirm")
    @ResponseBody
    public Result loginComfirm(@RequestParam("username") String username, @RequestParam("password") String password){
        if (username ==null || username.isEmpty())
            return Result.error(CodeMessage.NOT_FOUND_USER);
        if (password ==null || password.isEmpty())
            return Result.error(CodeMessage.PASSWORD_IS_EMPTY);
        User user = userService.getByUsername(username);
        if (user==null)
        {
            return Result.error(CodeMessage.NOT_FOUND_USER);
        }else if(user.getPassword()!=password){
            return Result.error(CodeMessage.PASSWORD_ERROR);
        }
        return Result.success(true);
    }

}
