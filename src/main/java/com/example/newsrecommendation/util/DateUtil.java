package com.example.newsrecommendation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/25
 */
public class DateUtil {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static Date convert(Date date) throws ParseException {
        return simpleDateFormat.parse(date.toString());
    }
}
