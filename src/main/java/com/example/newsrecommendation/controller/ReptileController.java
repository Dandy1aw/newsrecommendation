package com.example.newsrecommendation.controller;

import com.example.newsrecommendation.util.reptile.ReptileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/19
 * @Version 1.0
 */
@Controller
public class ReptileController {

    private static final  String  REPTILE_URL = "https://news.sina.com.cn/roll/#pageid=153&lid=2510&k=&num=50&page=";
    @Autowired
    ReptileUtil reptileUtil;

    @RequestMapping("/reptile")
    public void reptileNewsFromSina(){
        for (int i=1;i<=5;i++){
           reptileUtil.reptileAllKindOfNews(REPTILE_URL+i);
       }
    }
}
