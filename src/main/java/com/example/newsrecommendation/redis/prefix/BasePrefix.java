package com.example.newsrecommendation.redis.prefix;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/3
 * @Version 1.0
 */
public class BasePrefix implements Prefix {

	private int		expireSeconds;
	private String	prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
        /*过期时间为0，说明永不过期*/
        this.expireSeconds = 0;
        this.prefix = prefix;
    }

    @Override
	public int getExpireSeconds() {
		return expireSeconds;

	}

	@Override
	public String getPrefix() {
		String prefixName = this.getClass().getSimpleName();
		return prefixName + "_" + prefix;
	}
}
