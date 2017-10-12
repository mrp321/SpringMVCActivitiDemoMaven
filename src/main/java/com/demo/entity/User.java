package com.demo.entity;

import java.io.Serializable;

/**
 * 用户实体
 * 
 * @date 2017-10-10
 * @author qchen
 * 
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = -719579910115319636L;
	private String userId;
	private String password;

	public User() {
		super();
	}

	/**
	 * 含参构造器
	 * 
	 * @param userId
	 * @param password
	 */
	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + "]";
	}

}
