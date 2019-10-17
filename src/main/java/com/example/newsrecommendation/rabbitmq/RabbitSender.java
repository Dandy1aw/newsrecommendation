package com.example.newsrecommendation.rabbitmq;

import com.example.newsrecommendation.entity.News;
import com.example.newsrecommendation.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description    生产者
 * @Author 11103882
 * @Date 2019/9/16
 * @Version 1.0
 */
@Service
public class RabbitSender {
    private static final Logger log = LoggerFactory.getLogger(RabbitSender.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     *       将爬取到新闻对象发送给下一步，做分词和分类之类的处理
     *       注意：rabbitmq 消息队列如果发对象需要序列化（或者转为字符串）
     * @param news
     */
    public void send(News news){
        String newsStr = RedisService.beanToString(news);
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_NEWS_QUEUE,newsStr);
    }
}
