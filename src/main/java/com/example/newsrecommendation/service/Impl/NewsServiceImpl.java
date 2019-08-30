package com.example.newsrecommendation.service.Impl;

import com.example.newsrecommendation.dao.NewsDao;
import com.example.newsrecommendation.entity.News;
import com.example.newsrecommendation.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/27
 * @Version 1.0
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public List<News> showAllNews() {
        return newsDao.getAllNews();
    }

    @Override
    public News getById(Integer id) {
        return newsDao.getById(id);
    }

}
