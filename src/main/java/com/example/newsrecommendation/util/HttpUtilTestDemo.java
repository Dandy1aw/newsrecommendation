package com.example.newsrecommendation.util;

import org.jsoup.nodes.Document;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/30
 * @Version 1.0
 */
public class HttpUtilTestDemo {
    public static void main(String[] args) {
        HttpUtilTestDemo testDemo  = new HttpUtilTestDemo();
//        testDemo.testGetHtmlPageResponse();
        testDemo.testGetHtmlPageResponseAsDocument();
    }
    private static final String TEST_URL = "https://new.qq.com/ch/games/";

    public void testGetHtmlPageResponse() {
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.setTimeout(30000);
        httpUtils.setWaitForBackgroundJavaScript(30000);
        try {
            String htmlPageStr = httpUtils.getHtmlPageResponse(TEST_URL);
            //TODO
            System.out.println(htmlPageStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testGetHtmlPageResponseAsDocument() {
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.setTimeout(30000);
        httpUtils.setWaitForBackgroundJavaScript(30000);
        try {
            Document document = httpUtils.getHtmlPageResponseAsDocument(TEST_URL);
            //TODO
            System.out.println(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
