package com.glogs.init.cache;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.glogs.entity.blog.BTag;
import com.glogs.init.cache.inter.CacheInit;
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
	 * 日志记录器
	 */
	protected final Logger log = Logger.getLogger("init.global");
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info(LogsUtil.LINE);
		for (CacheInit init : cacheInit) {
			init.init();
		}
	}
	
	public List<CacheInit> getCacheInit() {
		return cacheInit;
	}

	public void setCacheInit(List<CacheInit> cacheInit) {
		this.cacheInit = cacheInit;
	}

}
