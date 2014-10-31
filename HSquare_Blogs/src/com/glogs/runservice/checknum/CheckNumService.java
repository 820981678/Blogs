package com.glogs.runservice.checknum;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.connection.db.DBException;
import com.glogs.entity.blog.Blog;
import com.glogs.service.blog.BlogService;

@Component
public class CheckNumService {
	
	@Resource
	private BlogService blogService;
	
	/**
	 * 保存博客的访问次数,会启动定时任务 定时向数据库中插入
	 */
	private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	
	/**
	 * 记录当天开始访问的博客点击初始值, 同步到数据库时用于 计算当天的博客全部访问量
	 */
	private static long startCheckNum = 0;
	
	/**
	 * 对博客的点击量进行查询
	 * 
	 * @param key
	 * @return
	 * @throws DBException
	 */
	public int getCheckNum(int key) throws DBException {
		//查询map中是否包含有该条博客的 点击量
		if(map.containsKey(key)){
			//获取该条博客 增加后的值
			int temp = map.get(key) + 1;
			map.put(key, temp);
			
			//修改数据库
			blogService.updateCheckNum(key, temp);
			
			return temp;
		}
		//没有则查询数据库,将查询的结果存入map中 key为博客id value为点击量
		Blog blog = blogService.queryBlogById(key);
		//设置博客点击初始值
		startCheckNum += blog.getCheckNum();
		int temp = blog.getCheckNum() + 1;
		map.put(blog.getId(),temp);
		
		//修改数据库
		blogService.updateCheckNum(key, temp);
		
		return temp;
	}
	
	public static Map<Integer, Integer> getMap(){
		return map;
	}
	
	public static long getStartCheckNum(){
		return startCheckNum;
	}
	
	public static void clean(){
		map = new HashMap<Integer, Integer>();
		startCheckNum = 0;
	}

}
