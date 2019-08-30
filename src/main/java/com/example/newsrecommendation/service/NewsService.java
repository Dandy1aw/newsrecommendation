package com.example.newsrecommendation.service;

import com.example.newsrecommendation.entity.News;
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
}
