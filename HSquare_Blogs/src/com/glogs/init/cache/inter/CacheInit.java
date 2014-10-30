package com.glogs.init.cache.inter;

import org.apache.log4j.Logger;

import com.glogs.util.GlobalLogger;

/**
 * 全局公共对象缓存 初始化接口
 * 
 * @ClassName: CacheInit 
 * @author huxiaohuan 
 * @date 2014年10月10日 上午10:58:03 
 * @version V1.0
 */
public interface CacheInit {
	
	final Logger log = GlobalLogger.init_global;
	
	void init();
	
}
