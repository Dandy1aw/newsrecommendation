package com.example.newsrecommendation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/11
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
    private Integer userId;
    private Integer newsId;
    private Double rating;
    private Integer ratetime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRatetime() {
        return ratetime;
    }

    public void setRatetime(Integer ratetime) {
        this.ratetime = ratetime;
    }
}
