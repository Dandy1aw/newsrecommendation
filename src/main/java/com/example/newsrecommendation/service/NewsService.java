package com.example.newsrecommendation.service;

import com.example.newsrecommendation.entity.News;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/27
 * @Version 1.0
 */

public interface NewsService {

    List<News> showAllNews();

    News getById(Integer id);

    void showAllByPage();

    Page<News> getNewsList();

    List<News> recommendMoviesBasedItem(Integer userId,Integer size);

    List<News> getLookedNewsByUser(Integer userId);

    boolean updateRatingByUserClick(Integer userId, Integer newsId);
}
