package com.example.newsrecommendation.dao;

import com.example.newsrecommendation.entity.Article;
import com.example.newsrecommendation.entity.MongoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/16
 * @Version 1.0
 */
@Component
public class MongoDBDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     */
    public void insertArticle(Article article) {
        mongoTemplate.save(article);
    }

    /**
     * 根据id 查询对象
     *
     * @return
     */
    public Article getByName(Integer id) {
        Query query = new Query(Criteria.where("articleId").is(id));
        Article article = mongoTemplate.findOne(query, Article.class);
        return article;
    }

    /**
     * 更新对象
     */
    public void updateArticle(Article article) {
        Query query = new Query(Criteria.where("id").is(article.getArticleId()));
        //todo 更新对象 随后 补充
        Update update = new Update().set("content", article.getContent()).set("tag", article.getTag());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, Article.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,TestEntity.class);
    }

    /**
     * 删除对象
     *
     * @param id
     */
    public void deleteById(Integer id) {
        Query query = new Query(Criteria.where("articleId").is(id));
        mongoTemplate.remove(query, Article.class);
    }
}