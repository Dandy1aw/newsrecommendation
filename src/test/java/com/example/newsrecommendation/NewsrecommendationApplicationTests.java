package com.example.newsrecommendation;

import com.example.newsrecommendation.redis.RedisService;
import com.example.newsrecommendation.redis.prefix.UserKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsrecommendationApplicationTests {

    @Autowired
    RedisService redisService;
    @Test
    public void contextLoads() {
        redisService.set(UserKey.userToken,"1",11111);
    }

}
