package com.example.newsrecommendation.controller;

import com.example.newsrecommendation.entity.News;
import com.example.newsrecommendation.service.NewsService;
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
    private NewsService newsService;

    @RequestMapping("/show/1")
    @ResponseBody
    public News showBody(){
        return newsService.getById(1);
    }


}
