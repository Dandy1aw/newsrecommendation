package com.example.newsrecommendation.redis;

import com.alibaba.fastjson.JSON;
import com.example.newsrecommendation.redis.prefix.BasePrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/3
 * @Version 1.0
 */
@Service
public class RedisService {

	@Autowired
	private JedisPool jedisPool;

	/**
	 *  set 方法 返回类型 为boolean  成功设置还是失败
	 * @param key 想要获取的数据的键
	 * @param <T>
	 */
	public <T> boolean set(BasePrefix prefix, String key, T value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();/* 调用jedisPool 获取连接 */
			/*
			 * 通过 jedis set() 方法 设置 值
			 * 但是 redis 只能存储 String 类型 或其他 不能存储 T 类型 需要转化 将T 类型 转化为String
			 */
			String strValue = beanToString(value);
			/* 生成 新的 带有前缀的 Key */
			String realKey = prefix.getPrefix() + key;
			/* 若数据为 空直接返回 false */
			if (strValue == null)
				return false;
			int seconds = prefix.getExpireSeconds();
			if (seconds <= 0) {
				/* 有效期小于0 时，调用set函数 认为 永不过期 */
				jedis.set(realKey, strValue);
			} else {
				/* 当有效期不小于0,设置有效期 */
				jedis.setex(realKey, seconds, strValue);
			}
			return true;
		} finally {
		    /*关闭连接*/
			returnToPool(jedis);
		}
	}


    /**
     *       根据传入的key和对象的类型 直接封装 返回一个对象
     * @param basePrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
	public <T> T get(BasePrefix basePrefix,String key,Class<T> clazz){
	    Jedis jedis =null;
	    try {
            jedis = jedisPool.getResource();
            String realKey = basePrefix.getPrefix()+key;
            String strValue = jedis.get(realKey);
            return strToBean(strValue,clazz);
        }finally {
	        returnToPool(jedis);
        }

    }

    /**
     *        将JSON字符串 转化为对象
     * @param strValue
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> T strToBean(String strValue, Class<T> clazz) {
        if (strValue==null && strValue.isEmpty() && clazz==null) return null;
        if (clazz == int.class || clazz == Integer.class){
            return (T) Integer.valueOf(strValue);
        }else if (clazz == long.class || clazz == Long.class){
            return (T)Long.valueOf(strValue);
        }else if (clazz == String.class){
            return (T) strValue;
        }else {
            return JSON.toJavaObject(JSON.parseObject(strValue),clazz);
        }
    }

    /**
	 *       将对象转化为字符串用于存在redis中
	 * @param value
	 * @param <T>
	 * @return
	 */
	private <T> String beanToString(T value) {
		if (value == null)
			return null;
		Class<?> clazz = value.getClass();
		if (clazz==int.class || clazz == Integer.class){
		    return ""+value;
        }else if (clazz == long.class || clazz == Long.class){
		    return ""+value;
        }else if (clazz == String.class){
		    return (String)value;
        }else {
		    return JSON.toJSONString(value);
        }
	}

	/* 将该 连接释放 重新放入连接池 */
	private void returnToPool(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}
}
