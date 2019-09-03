package com.example.newsrecommendation.controller;

import com.example.newsrecommendation.entity.User;
import com.example.newsrecommendation.result.CodeMessage;
import com.example.newsrecommendation.result.CodeMsg;
import com.example.newsrecommendation.result.Result;
import com.example.newsrecommendation.service.UserService;
import com.example.newsrecommendation.vo.LoginVo;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/29
 * @Version 1.0
 */
@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
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
    public Result loginComfirm(LoginVo loginVo, HttpServletResponse response){
        log.info(loginVo.toString());
        if (loginVo==null)
        {
            return Result.error(CodeMessage.SERVER_ERROR);
        }

        String username = loginVo.getUsername();
        String password =loginVo.getPassword();
        if (username ==null || username.isEmpty())
            return Result.error(CodeMessage.NOT_FOUND_USER);
        if (password ==null || password.isEmpty())
            return Result.error(CodeMessage.PASSWORD_IS_EMPTY);

        Result loginResult = userService.checkUser(loginVo,response);
        return loginResult;
    }

}
