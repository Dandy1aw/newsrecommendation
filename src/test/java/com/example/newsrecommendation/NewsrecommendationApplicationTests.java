package com.example.newsrecommendation;


import com.alibaba.fastjson.JSONObject;
import com.example.newsrecommendation.dao.MongoDBDao;
import com.example.newsrecommendation.dao.NewsDao;
import com.example.newsrecommendation.dao.RateDao;
import com.example.newsrecommendation.entity.News;
import com.example.newsrecommendation.rabbitmq.RabbitSender;
import com.example.newsrecommendation.redis.RedisService;
import com.example.newsrecommendation.redis.prefix.UserKey;
import com.example.newsrecommendation.service.NewsService;
import com.example.newsrecommendation.test.YidianMessage;
import com.example.newsrecommendation.test.YidianMessageOther;
import com.example.newsrecommendation.util.reptile.ReptileUtil;
import com.example.newsrecommendation.util.wordsegmenter.SegmentWordUtil;
import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.Word2VecTrainer;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsrecommendationApplicationTests {

    @Autowired
    RedisService redisService;
    @Autowired
    ReptileUtil reptileUtil;

    @Autowired
    NewsDao newsDao;

    @Autowired
    NewsService newsService;

    @Autowired
    RateDao rateDao;
    @Autowired
    private MongoDBDao mongoDBDao;
    @Autowired
    RabbitSender rabbitSender;
    @Test
    public void contextLoads() throws ParseException, IOException {

        YidianMessage yidianMessage = new YidianMessage();
        yidianMessage.setMessageType("video");
        yidianMessage.setDocId("110");
        yidianMessage.setcType("fate");
        String json = JSONObject.toJSONString(yidianMessage);
        System.out.println(json);
        YidianMessageOther jsonObject  = JSONObject.parseObject(json, YidianMessageOther.class);
        System.out.println(jsonObject);


    }

}
