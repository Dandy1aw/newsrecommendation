package com.example.newsrecommendation.vo;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/2
 * @Version 1.0
 */
@Data
public class LoginVo {
	private String	username;
	private String	password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
