package com.example.newsrecommendation.controller;

import com.example.newsrecommendation.entity.News;
import com.example.newsrecommendation.entity.User;
import com.example.newsrecommendation.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description  新闻个性化推荐
 * @Author 11103882
 * @Date 2019/9/10
 * @Version 1.0
 */
@Controller
public class RecommendController {

    private static final Logger log = LoggerFactory.getLogger(RecommendController.class);
    //推荐结果个数
    final private int RECOMMENDER_NUM = 10;
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/recommend")
    public String recommendlist(Model model, User user) {
        List<News> listNewsRecommend=newsService.recommendMoviesBasedItem(user.getId(), RECOMMENDER_NUM);
        List<News> lookedMovies=newsService.getLookedNewsByUser(user.getId());
        model.addAttribute("lookedMovies",lookedMovies);
        model.addAttribute("moviesRBI",listNewsRecommend);
        model.addAttribute("user",user);
        return "recommend";
    }
}
