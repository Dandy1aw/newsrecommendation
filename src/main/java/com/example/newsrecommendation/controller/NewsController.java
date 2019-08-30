package com.example.newsrecommendation.controller;


import com.example.newsrecommendation.entity.News;
import com.example.newsrecommendation.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/27
 * @Version 1.0
 */

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;


    @RequestMapping("/index")
    public String showMainPage(Model model){

        List<News> newsList = newsService.showAllNews();
        model.addAttribute("newsList",newsList);
        return "index";
    }


    @RequestMapping(value = "/{newsId}") // 前端传入的参数 newsId
    public String showById(Model model, @PathVariable("newsId") Integer newsId){
        News news = newsService.getById(newsId);
        model.addAttribute("news",news);
        return "index";
    }

}
