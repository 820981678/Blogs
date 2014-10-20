package com.glogs.emu.blog;

/**
 * 文章状态枚举
 * 
 * @ClassName: BlogState 
 * @author huxiaohuan 
 * @date 2014年10月11日 上午9:35:13 
 * @version V1.0
 */
public enum BlogState {

	FB(1,"发布"),
	
	BC(2,"保存");
	
	private int key;
	
	private String name;
	
	/**
	 * 构造方法
	 * 
	 * @param key
	 * @param name
	 */
	private BlogState(int key,String name){
		this.key = key;
		this.name = name;
	}
	
	/**
	 * 获取key
	 * 
	 * @return
	 */
	public int getKey(){
		return key;
	}
	
	/**
	 * 获取name
	 * 
	 * @return
	 */
	public String getName(){
		return name;
	}
}
