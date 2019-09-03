package com.example.newsrecommendation.entity;

import java.util.Date;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/29
 * @Version 1.0
 */
public class User {
	private Integer	id;
	private String	username;
	private String	password;
	private Date	registerDate;
	private Date	lastLoginTime;

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", registerDate=" + registerDate
				+ ", lastLoginTime=" + lastLoginTime + '}';
	}

	public User(Integer id, String username, String password, Date registerDate, Date lastLoginTime) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.registerDate = registerDate;
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}
