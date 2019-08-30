package com.example.newsrecommendation.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/30
 * @Version 1.0
 */
public class HtmlutilDemo {
    public static void main(String[] args) {

        String url = "https://new.qq.com/ch/games/";
        System.out.println("Loading page now-----------------------------------------------: "+url);

        // HtmlUnit 模拟浏览器
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);              // 启用JS解释器，默认为true
        webClient.getOptions().setCssEnabled(false);                    // 禁用css支持
        webClient.getOptions().setThrowExceptionOnScriptError(false);   // js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(10 * 1000);                   // 设置连接超时时间
        HtmlPage page = null;
        try {
            page = webClient.getPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        webClient.waitForBackgroundJavaScript(30 * 1000);               // 等待js后台执行30秒

        String pageAsXml = page.asXml();

        // Jsoup解析处理
        Document doc = Jsoup.parse(pageAsXml, "https://new.qq.com/ch/games/");
        Elements pngs = doc.select("img[src$=.png]");                   // 获取所有图片元素集
        // 此处省略其他操作
        System.out.println(doc.toString());
    }
}
