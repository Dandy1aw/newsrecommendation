package com.example.newsrecommendation.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Description  爬虫工具
 * @Author 11103882
 * @Date 2019/8/30
 * @Version 1.0
 */

public class ReptileDemo {

    public static void main(String[] args) {
        String url = "https://mil.news.sina.com.cn/china/2019-08-29/doc-ihytcitn2736349.shtml";
        try {
            Document document = Jsoup.connect(url).get();
            Elements article = document.select("div.article");
            String author = article.select("p").text();
            Element imgElement = article.select("img").first();
            String imgUrl = imgElement.attr("abs:src");
            System.out.println(imgUrl);



//            System.out.println(elementOfBar);
//            System.out.println(content);
//                for (Element liElement :ulElements){
//                    String title = liElement.select("a").text();
//                }

//                System.out.println(title);
//                System.out.println(ulElements);
//                String  title = element.select("li").select("a").text();
//                String  pubTime = element.select("span.time").text();
//                String  newsUrl = element.select("a").attr("href");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}