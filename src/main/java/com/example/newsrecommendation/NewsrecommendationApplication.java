package com.example.newsrecommendation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class NewsrecommendationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewsrecommendationApplication.class, args);
    }
}
