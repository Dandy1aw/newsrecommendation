package com.example.newsrecommendation.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description 新闻实体类
 * @Author 11103882
 * @Date 2019/8/27
 * @Version 1.0
 */
@Data
public class News {
    private Integer id;
    private String title;
    private String author;
    private String url;
    private String content;
    private Date pubDate;
    private String type;

    public News() {
    }

    public News(Integer id, String title, String author, String url, String content, Date pubDate, String type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.url = url;
        this.content = content;
        this.pubDate = pubDate;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
