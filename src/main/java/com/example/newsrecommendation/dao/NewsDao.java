package com.example.newsrecommendation.dao;

import com.example.newsrecommendation.entity.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/27
 * @Version 1.0
 */

@Mapper
public interface NewsDao {

    @Select({"select * from news where id = #{id}"})
    News getById(@Param("id") Integer id);

    @Select("select * from news")
    List<News> getAllNews();

    @Select("select count(*) from news")
    Integer count();

    @Insert("INSERT INTO news(title,author,url,content,pubdate) VALUES(#{title},#{author},#{url},#{content},#{pubDate});")
    void insertNews(News news);

}
