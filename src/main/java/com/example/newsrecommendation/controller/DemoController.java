package com.example.newsrecommendation.controller;

import com.example.newsrecommendation.util.reptile.ReptileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/28
 * @Version 1.0
 */
@Controller
public class DemoController {

    @Autowired
    private ReptileUtil reptileUtil;

    /**        爬数据
     *
     * @return
     */
    @RequestMapping("/reptile")
    @ResponseBody
    public String showBody(){
        for (int i=2;i<=10;i++){
            reptileUtil.reptileMainInfo("http://mil.news.sina.com.cn/roll/index.d.html?cid=57918&page="+i);
        }

        return "OK";
    }


}
