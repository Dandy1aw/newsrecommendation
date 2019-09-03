package com.example.newsrecommendation.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description   配置类Config  Redis 相关配置
 * @Author 11103882
 * @Date 2019/9/3
 * @Version 1.0
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	Logger logger	= LoggerFactory.getLogger(RedisConfig.class);

	@Value("${spring.redis.host}")
	private String	host;

	@Value("${spring.redis.port}")
	private int		port;

	@Value("${spring.redis.timeout}")
	private String	timeout;

	@Value("${spring.redis.jedis.pool.max-idle}")
	private int		maxIdle;

	@Value("${spring.redis.jedis.pool.max-wait}")
	private String	maxWaitMillis;

	@Value("${spring.redis.password}")
	private String	password;

	@Bean
	public JedisPool redisPoolFactory() {

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

		jedisPoolConfig.setMaxIdle(maxIdle);

		jedisPoolConfig.setMaxWaitMillis(Long.valueOf(maxWaitMillis.substring(0, maxWaitMillis.length() - 2)));

		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, Integer.valueOf(timeout.substring(0, timeout.length() - 2)));

		return jedisPool;
	}
}
