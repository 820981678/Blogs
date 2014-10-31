package com.glogs.runservice.checknum;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.connection.db.DBException;
import com.glogs.service.blog.BlogService;
import com.glogs.service.blog.impl.BolgServiceImpl;
import com.glogs.util.GlobalLogger;
import com.task.StatefulJob;
import com.task.TaskException;
import com.util.LogsUtil;

public class CheckNumTask extends StatefulJob  {
	
	private BlogService blogService = new BolgServiceImpl();
	
	/**
	 * 日志记录器
	 */
	private static Logger log = GlobalLogger.task_checknum;
	
	/**
     * 构造方法.
     */
    public CheckNumTask() {
        super();
    }
	
	@Override
	public void init() throws TaskException {
		
	}
	
	/**
	 * 定时将 map中数据 写入到
	 */
	@Override
	public void execute(JobExecutionContext arg0) {
		if (log.isInfoEnabled()) {
			log.info(LogsUtil.LINE);
			log.info(LogsUtil.PREFIX2 + "CheckNumServiceTask start...");
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
			log.info(LogsUtil.PREFIX3 + "Time: " + sdf.format(new Date()));
        }
		try {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			map.put(54, 100);
			map.put(55, 100);
			updateDB(CheckNumService.getMap(),CheckNumService.getStartCheckNum());
			//updateDB(map,0);
		} catch(Exception e){
			log.error("checkNum update Exception", e);
		} catch (DBException e) {
			log.error("checkNum update db error", e);
		}
	}

	/**
	 * 将map中数据写入到数据库
	 */
	private void updateDB(Map<Integer, Integer> map,long startCheckNum) throws DBException,Exception {
		Set<Integer> set = map.keySet();
		//计算当天同步前 所有博客的点击量
		long checkSum = 0;
		for (Integer key : set) {
			System.out.println("------------------->"+ key);
			int temp = blogService.updateCheckNum(key,map.get(key));
			if(temp != 1){
				log.error("checkNum update db error key: " + key + " value: " + map.get(key));
			} else {
				checkSum += map.get(key);
				log.info(LogsUtil.PREFIX3 + "update key:" + key + "  value:" + map.get(key));
			}
		}
		long today = checkSum - startCheckNum;
		log.info(LogsUtil.PREFIX3 + "------> " + getDay() + "  The visit quantity: " + today);
		//清除记录
		CheckNumService.clean();
	}
	
	/**
	 * 获取当天的前一天的日期
	 * 
	 * @return 调用时间为 2014-10-31 则返回 2014-10-30
	 */
	private String getDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		return year + "-" + month + "-" + day;
	}

}
