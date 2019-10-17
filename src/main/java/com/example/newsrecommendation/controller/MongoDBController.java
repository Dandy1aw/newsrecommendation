package com.example.newsrecommendation.controller;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/16
 * @Version 1.0
 */
public class MongoDBController {
    public static void main(String[] args) {
        int[] x = {1,2,3,4,5,6,7,8};
        List<Integer> list = new ArrayList<>(x.length);
        for (Integer i: x){
            list.add(i);
        }
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
    }
}
