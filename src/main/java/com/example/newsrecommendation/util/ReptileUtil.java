package com.example.newsrecommendation.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Description 爬虫工具 从新浪网上爬取相关信息存到数据库里
 * @Author 11103882
 * @Date 2019/8/30
 * @Version 1.0
 */

public class ReptileUtil {
    private final static String URL_OF_MAIN_PAGE = "http://mil.news.sina.com.cn/roll/index.d.html?cid=57918";

    private ReptileUtil() {
    }

    public static void reptileMainInfo(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements ContentList = document.select("div.fixList");
        for (Element conElement : ContentList) {
            Elements LiElements = conElement.select("ul.linkNews > li");
            for (Element newsElement : LiElements) {
                /*文章标题*/
                String title = newsElement.select("a").text();
                /*对应URL*/
                String newsUrl = newsElement.select("a").attr("href");
                /*发布时间不包含年份*/
                String pubTime = newsElement.select("span.time").text();
                try {
                    Document contentDoc = Jsoup.connect(newsUrl).get();
                    /*文章作者（来源）*/
                    Elements elementOfBar = contentDoc.select("div.top-bar-wrap");
                    String author = elementOfBar.select(".source").text();
                    /*带有年份的发布时间*/
                    String pubTimeWithYear = elementOfBar.select(".date").text();
                    Elements article = document.select("div.article");
                    /*文中的第一章图片*/
                    Element imgElement = article.select("img").first();
                    String imgUrl = imgElement.attr("abs:src");
                    /*文章内容*/
                    String content = contentDoc.select("p").text();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
