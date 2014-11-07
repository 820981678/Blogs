package com.glogs.entity.account;

public class Token {
	
	public Token(User user){
		this.user = user;
	}
	
	private User user;
	
	/**
	 * 获取当前用户
	 * 
	 * @return
	 */
	public User getUser(){
		return user;
	}
	
	/**
	 * 获取用户id
	 * 
	 * @return
	 */
	public Integer getUserId(){
		return user == null ? null : user.getId();
	}
	
	/**
	 * 获取用户名称
	 * 
	 * @return
	 */
	public String getUserName(){
		return user == null ? null : user.getName();
	}
	
	/**
	 * 设置用户
	 * 
	 * @param user
	 */
	public void setUser(User user){
		this.user = user;
	}
	
}
