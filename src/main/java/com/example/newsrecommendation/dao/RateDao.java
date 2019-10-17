package com.example.newsrecommendation.dao;

import com.example.newsrecommendation.entity.Rate;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/11
 * @Version 1.0
 */
@Mapper
public interface RateDao {

    int insertRate(Rate rate);

    int updateRatingByClick(Integer userId, Integer newsId);

    int updateRatingByClickWithScore(Integer userId, Integer newsId,int score);

    int getCurrentRating(Integer userId, Integer newsId);
}
