package com.example.newsrecommendation.service.Impl;

import com.example.newsrecommendation.dao.NewsDao;
import com.example.newsrecommendation.dao.RateDao;
import com.example.newsrecommendation.entity.News;
import com.example.newsrecommendation.entity.Rate;
import com.example.newsrecommendation.recommend.mahout.NewsRecommender;
import com.example.newsrecommendation.redis.RedisService;
import com.example.newsrecommendation.redis.prefix.NewsKey;
import com.example.newsrecommendation.service.NewsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    @Autowired
    private NewsRecommender newsRecommender;
    @Autowired
    private RateDao rateDao;
    @Autowired
    private RedisService redisService;

    @Override
    public List<News> showAllNews() {
        return newsDao.getAllNews();
    }

    @Override
    public News getById(Integer id) {

        return newsDao.getById(id
        );
    }

    @Override
    public void showAllByPage() {
        PageHelper.startPage(1, 5);
        List<News> list = newsDao.getAllNews();
        PageInfo<News> page = new PageInfo<News>(list);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());
    }

    @Override
    public Page<News> getNewsList() {
        return newsDao.getNewsList();
    }



    @Override
    public List<News> recommendMoviesBasedItem(Integer userId, Integer size) {
        List<Long> recommendedItems=null;
        try {
            recommendedItems=newsRecommender.itemBasedRecommender(userId.longValue(),size);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        //优化：获取的推荐先在Redis中缓存一天，找不到再去数据库取
        List<News> recommendNewsList = redisService.getObjectList(NewsKey.recommendKey,userId.toString(),News.class);
        if (recommendNewsList==null){
            recommendNewsList = newsDao.getsNewsListByIds(recommendedItems);
            long count = redisService.setObjectList(NewsKey.recommendKey,userId.toString(),recommendNewsList);
        }
        return recommendNewsList;
    }

    @Override
    public List<News> getLookedNewsByUser(Integer userId) {
        return newsDao.getLookedNewsByUser(userId);
    }

    @Override
    public boolean updateRatingByUserClick(Integer userId, Integer newsId) {
        int currentRating = rateDao.getCurrentRating(userId,newsId);
        int returnData = 0;
        if (currentRating==0){
            Rate rate = new Rate();
            rate.setUserId(userId);
            rate.setNewsId(newsId);
            rate.setRating(1.0);
            Random random = new Random();
            rate.setRatetime(random.nextInt(5)+1000);
            returnData = rateDao.insertRate(rate);
        }else {
            //todo 后续设置 评分上限 10分 同时 添加 点赞 -一次点赞3分
           returnData = rateDao.updateRatingByClick(userId,newsId);
        }

        return returnData>0;
    }


}
