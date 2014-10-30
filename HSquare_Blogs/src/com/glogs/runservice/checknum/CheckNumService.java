package com.glogs.runservice.checknum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.connection.db.DBException;
import com.glogs.entity.blog.Blog;
import com.glogs.service.blog.BlogService;
import com.glogs.util.GlobalLogger;
import com.util.LogsUtil;

@Component
public class CheckNumService extends QuartzJobBean {
	
	@Resource
	private BlogService blogService;
	
	/**
	 * 日志记录器
	 */
	private static Logger log = GlobalLogger.task_checknum;
	
	/**
	 * 保存博客的访问次数,会启动定时任务 定时向数据库中插入
	 */
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	
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
			return temp;
		}
		//没有则查询数据库,将查询的结果存入map中 key为博客id value为点击量
		Blog blog = blogService.queryBlogById(key);
		int temp = blog.getCheckNum() + 1;
		map.put(blog.getId(),temp);
		
		return temp;
	}

	/**
	 * 定时将 map中数据 写入到
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		if (log.isInfoEnabled()) {
			log.info(LogsUtil.LINE);
			log.info(LogsUtil.PREFIX2 + "CheckNumServiceTask start...");
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
			log.info(LogsUtil.PREFIX3 + "Time: " + sdf.format(new Date()));
			//log.info(LogsUtil.PREFIX3 + "StaticTemplateFilter init... is complete");
        }
		
	}
	
	/**
	 * 将map中数据写入到数据库
	 */
	private synchronized void updateDB(){
		for (Integer key : map.keySet()) {
			System.out.println(key);
		}
	}
	
	public static void main(String[] args) throws Exception {
		final CheckNumService c = new CheckNumService();
		for (int i = 0; i < 1000; i++) {
			c.map.put(i, i);
		}
		
		Runnable run = new Runnable() {
			@Override
			public void run() {
				c.updateDB();
			}
		};
		
		Thread t = new Thread(run);
		t.start();
		Thread.sleep(10);
		System.out.println("key-->1 value-->" + c.map.put(1, 2));
	}
	
}
