package com.example.newsrecommendation.rabbitmq;

import com.example.newsrecommendation.dao.MongoDBDao;
import com.example.newsrecommendation.entity.Article;
import com.example.newsrecommendation.entity.News;
import com.example.newsrecommendation.redis.RedisService;
import com.example.newsrecommendation.util.wordsegmenter.SegmentWordUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description      消费者
 * @Author 11103882
 * @Date 2019/9/16
 * @Version 1.0
 */
@Service
public class RabbitReceiver {

    @Autowired
    private MongoDBDao mongoDBDao;
    /**
     *        1对1 监听  RabbitMQConfig.DIRECT_NEWS_QUEUE 端口，一有消息发送过来就 进行处理
     * @param newsStr
     */
    @RabbitListener(queues = RabbitMQConfig.DIRECT_NEWS_QUEUE)
    @RabbitHandler
    public void newsReceicer(String newsStr){
        News  news = RedisService.strToBean(newsStr,News.class);
        Integer id = news.getId();
        String content = news.getContent();
        //分词打标 存入mongo
        Article article = new Article();
        article.setArticleId(id);
        article.setContent(content);
        article.setPartOfSpeech(SegmentWordUtil.segmentWordIntoList(content));
        //关键词
        article.setTag(SegmentWordUtil.getKeyword(content));
        mongoDBDao.insertArticle(article);
    }
}
