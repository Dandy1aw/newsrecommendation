package com.example.newsrecommendation.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.newsrecommendation.redis.prefix.BasePrefix;
import com.sun.deploy.util.SyncAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.validation.constraints.Future;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/3
 * @Version 1.0
 */
@Service
public class RedisService {

	private static final Logger log = LoggerFactory.getLogger(RedisService.class);
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
	 *        设置对象列表缓存 存入
	 * @param prefix
	 * @param key
	 * @param <T>
	 * @return   返回值-0 存入失败， count  成功存入的列表中对象的个数
	 */
    public <T> long setObjectList(BasePrefix prefix, String key, List<T> list){
    	if (StringUtils.isEmpty(key) || prefix==null) return 0;
    	Jedis jedis = null;
    	long count = 0;
    	try {
			jedis = jedisPool.getResource();
			String realKey = prefix.getPrefix()+"_"+key;
			int seconds = prefix.getExpireSeconds();
			if (jedis.exists(realKey)){
				jedis.del(realKey);
			}
			// 分布式锁 不太会用
//			if (jedis.setnx("LOCK"+realKey,realKey)==1){
				if (jedis.exists(realKey)){
					return 0;
				}
				for (T object : list){
					String objectStr = beanToString(object);
					log.info(objectStr);
					//这里要注意存的时候右边存，那么取的时候就从左边取，可以保证列表的顺序一致
					jedis.rpush(realKey,objectStr);
					count++;
				}
				//设置过期时间
				if (seconds>0) jedis.expire(realKey,seconds);

		}finally {
    		returnToPool(jedis);
		}
    	return count;
    }

	/**
	 *        获取对象列表缓存 取到的是已经反序列化的对象列表
	 * @param prefix
	 * @param key
	 * @param clazz
	 * @param <T>
	 * @return
	 */
    public <T> List<T> getObjectList(BasePrefix prefix, String key,Class<T> clazz){
    	if (StringUtils.isEmpty(key)|| prefix == null) return null;
    	Jedis jedis = null;
    	List<T> objectList = null;
    	try {
    		jedis = jedisPool.getResource();
    		String realKey = prefix.getPrefix()+"_"+key;
    		if (jedis.exists(realKey)){
    			List<String> strList = jedis.lrange(realKey,0,-1);
    			objectList = new ArrayList<>();
    			for (String s : strList){
    				objectList.add(strToBean(s,clazz));
				}
			}
    		return objectList;
		}finally {
			returnToPool(jedis);
		}
	}


	public  boolean exist(BasePrefix prefix, String key)
	{
		/**1.获取jedis 客户端 是一个连接，用完必须释放掉 close()方法 返回 连接池 而不是关闭
		 * */
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();/*调用jedisPool 对象的 getResource()方法 难道 jedis （其实就是一个连接）*/
			/* 生成 新的 带有前缀的 Key*/
			String  realKey = prefix.getPrefix() +key;
			/* 判断该 key 是否存在*/
			return jedis.exists(realKey);
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
    public static   <T> T strToBean(String strValue, Class<T> clazz) {
        if (strValue==null || strValue.isEmpty() || clazz==null) return null;
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
	public static  <T> String beanToString(T value) {
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
