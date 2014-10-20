package com.glogs.emu.blog;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章类型枚举
 * 
 * @ClassName: BType 
 * @author huxiaohuan 
 * @date 2014年10月10日 上午11:00:21 
 * @version V1.0
 */
public enum BType {
	
	YC(1,"原创"),
	
	ZZ(2,"转载"),
	
	FY(3,"翻译");
	
	private int key;
	
	private String name;
	
	/**
	 * 构造方法
	 * 
	 * @param key
	 * @param name
	 */
	private BType(int key,String name){
		this.key = key;
		this.name = name;
	}
	
	/**
	 * 获取文章类型的唯一key
	 * @return
	 */
	public int getKey(){
		return this.key;
	}
	
	/**
	 * 获取文章类型的唯一名称
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * 通过类型key获取 类型枚举对象
	 * 
	 * @param key
	 * @param defaultV
	 * @return
	 */
	public static BType toBTypeByKey(Integer key,BType defaultV){
		if(key == null){
			return defaultV;
		}
		BType[] bs = BType.values();
		for (BType _b : bs) {
			if(_b.key == key){
				return _b;
			}
		}
		return defaultV;
	}
	
	/**
	 * 通过类型key获取 类型枚举对象
	 * 
	 * @param key
	 * @return
	 */
	public static BType toBTypeByKey(Integer key){
		return toBTypeByKey(key,null);
	}
	
	/**
	 * 通过类型name获取 类型枚举对象
	 * 
	 * @param name
	 * @param defaultV
	 * @return
	 */
	public static BType toBTypeByName(String name, BType defaultV){
		if(name == null || name.length() == 0){
			return defaultV;
		}
		BType[] bs = BType.values();
		for (BType _b : bs) {
			if(_b.getName().equals(name)){
				return _b;
			}
		}
		return defaultV;
	}
	
	/**
	 * 通过类型name获取 类型枚举对象
	 * 
	 * @param name
	 * @return
	 */
	public static BType toBTypeByName(String name){
		return toBTypeByName(name,null);
	}
	
	/**
	 * 返回所有的类型list
	 * 
	 * @return
	 */
	public static List<BType> toList(){
		List<BType> list = new ArrayList<BType>();
		BType[] bs = BType.values();
		for (BType _b : bs) {
			list.add(_b);
		}
		return list;
	}
}
