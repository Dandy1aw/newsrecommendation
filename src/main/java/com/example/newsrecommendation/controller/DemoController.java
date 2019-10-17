package com.example.newsrecommendation.controller;

import com.example.newsrecommendation.dao.NewsDao;
import com.example.newsrecommendation.entity.News;
import com.example.newsrecommendation.rabbitmq.RabbitSender;
import com.example.newsrecommendation.util.reptile.ReptileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    NewsDao newsDao;
    @Autowired
    private ReptileUtil reptileUtil;
    @Autowired
    RabbitSender rabbitSender;

    /**        爬数据
     *
     * @return
     */
//    @RequestMapping("/reptile")
//    @ResponseBody
//    public String showBody(){
//        for (int i=1;i<=25;i++){
//            reptileUtil.reptileMainInfo("http://mil.news.sina.com.cn/roll/index.d.html?cid=57919"+i);
//        }
//        return "OK";
//    }

    @RequestMapping("/testRabbitMQ")
    public void testRabbitMQ(){
        for (int i =1;i<20;i++){
            News news = newsDao.getById(i);
            rabbitSender.send(news);
        }
    }

    @RequestMapping("/demo")
    public String demo(Model model){
        News news = newsDao.getById(22);
        model.addAttribute("newsDetail",news);
        return "demo";
    }

}
