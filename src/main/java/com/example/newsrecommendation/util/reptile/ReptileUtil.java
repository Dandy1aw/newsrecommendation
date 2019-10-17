package com.example.newsrecommendation.util.reptile;

import com.example.newsrecommendation.dao.NewsDao;
import com.example.newsrecommendation.entity.News;
import com.example.newsrecommendation.rabbitmq.RabbitSender;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Description 爬虫工具 从新浪网上爬取相关信息存到数据库里
 * @Author 11103882
 * @Date 2019/8/30
 * @Version 1.0
 */

@Service
public class ReptileUtil {

    @Autowired
    private NewsDao newsDao;
    /**
     * 新浪军事 URL
     */
    private final static String URL_OF_MAIN_PAGE = "http://mil.news.sina.com.cn/roll/index.d.html?cid=57918&page=";

    /**
     *
     */
    private final static String URL_OF_ALL_KINDS_NEWS = "https://news.sina.com.cn/roll/#pageid=153&lid=2510&k=&num=50&page=1";

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

    @Autowired
    RabbitSender rabbitSender;

    private ReptileUtil() {
    }

    /**
     * 爬虫 ---目前只能 针对 新浪军事 部分。
     *
     * @param url
     */
    public void reptileMainInfo(String url) {
        /*用于存日期格式*/

        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements ContentList = document.select("div.fixList");
        if (ContentList!=null){
            for (Element conElement : ContentList) {
                Elements LiElements = conElement.select("ul.linkNews > li");
                for (Element newsElement : LiElements) {
                    /*文章标题*/
                    String newsTitle = newsElement.select("a").text();
                    /*对应URL*/
                    String newsUrl = newsElement.select("a").attr("href");
                    /*发布时间不包含年份*/
                    String pubTime = newsElement.select("span.time").text();
                    News news = new News();
                    news.setTitle(newsTitle);
                    news.setUrl(newsUrl);
                    /*对象赋值*/
                    reptileDetailInfo(news, newsUrl);
                    newsDao.insertNews(news);
                    rabbitSender.send(news);
                    System.out.println(news.toString());
                }
            }
        }


    }

    /**
     * 细节赋值：内容，作者，日期，
     *
     * @param news
     * @param newsUrl
     */
    private void reptileDetailInfo(News news, String newsUrl) {
        try {
            Document contentDoc = Jsoup.connect(newsUrl).get();
            /*文章作者（来源）*/
            Elements elementOfBar = contentDoc.select("div.top-bar-wrap");
            String author = elementOfBar.select(".source").text();
            /*带有年份的发布时间*/
            String pubTimeWithYear = elementOfBar.select(".date").text();
            Elements articleElement = contentDoc.select("div.article");
            /*文中的第一章图片*/
            Element imgElement = articleElement.select("img").first();
            /*文章内容*/
            String content = articleElement.select("p").text();
            if (imgElement != null) {
                String imgUrl = imgElement.attr("abs:src");
            }
            /*对象赋值*/
            news.setContent(content);
            news.setAuthor(author);
            try {
                news.setPubDate(simpleDateFormat.parse(pubTimeWithYear));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 针对新浪新闻 滚动全部内容 新闻
     * @param url
     */
    public void reptileAllKindOfNews(String url) {
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.setTimeout(30000);
        httpUtils.setWaitForBackgroundJavaScript(30000);
        Document document = null;
        try {
            document = httpUtils.getHtmlPageResponseAsDocument(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Elements contentList = document.select("div.d_list_txt");
        for (Element element : contentList) {
            Elements liList = element.select("li");
            for (Element newsElement : liList) {
                String newsTitle = newsElement.select("a").text();
                String newsUrl = newsElement.select("a").attr("href");
                News news = new News();
                news.setTitle(newsTitle);
                news.setUrl(newsUrl);
                /*对象赋值*/
                reptileDetailInfo(news, newsUrl);
                newsDao.insertNews(news);
                /*Rabbit MQ异步处理 用于分词 和保存文章 关键词 到Mongo*/
                rabbitSender.send(news);
                System.out.println(news.toString());
            }
        }
    }
}