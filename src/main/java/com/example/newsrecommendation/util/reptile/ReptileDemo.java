package com.example.newsrecommendation.util.reptile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
			String pubTime = document.select("div.top-bar-wrap").select(".date").text();
			Element imgElement = article.select("img").first();
			String imgUrl = imgElement.attr("abs:src");
			System.out.println(pubTime);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
			try {
				System.out.println(simpleDateFormat.parse(pubTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
