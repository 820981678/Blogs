package com.glogs.init.cache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.glogs.entity.blog.BTag;
import com.glogs.init.cache.inter.CacheInit;
import com.glogs.util.GlobalLogger;
import com.util.LogsUtil;

/**
 * 全局公共对象缓存持有
 * 
 * @ClassName: GlobalCache 
 * @author huxiaohuan 
 * @date 2014年10月9日 下午4:46:18 
 * @version V1.0
 */
public class GlobalCache implements InitializingBean {
	
	/**
	 * 全局变量初始化对象集合
	 */
	private List<CacheInit> cacheInit;
	
	/**
	 * 全局博客文章标签
	 */
	public static List<BTag> global_btag;
	
	/**
	 * 全局热门文章集合,key为文章title value为文章地址
	 */
	public static List<Map<String, Object>> global_hotBlog;
	
	/**
	 * 全局放行html请求集合
	 */
	public static Set<String> global_discharged;
	
	/**
	 * 日志记录器
	 */
	protected final Logger log = GlobalLogger.init_global;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info(LogsUtil.LINE);
		for (CacheInit init : cacheInit) {
			init.init();
		}
	}
	
	/**
	 * 根据文章标签的id 获取该文章标签
	 * @param id
	 * @return 没有找到返回null
	 */
	public static BTag getBtagById(Integer id){
		for (BTag bTag : global_btag) {
			if(id == bTag.getId()){
				return bTag;
			}
		}
		return null;
	}
	
	/**
	 * 随机抽取20条 热门文章
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> getHotBlog(){
		if(global_hotBlog.size() <= 20){
			return global_hotBlog;
		}
		
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 20; i++) {
			result.add(global_hotBlog.get(i));
		}
		return result;
	}
	
	/*get  set */
	
	public List<CacheInit> getCacheInit() {
		return cacheInit;
	}

	public void setCacheInit(List<CacheInit> cacheInit) {
		this.cacheInit = cacheInit;
	}

}
