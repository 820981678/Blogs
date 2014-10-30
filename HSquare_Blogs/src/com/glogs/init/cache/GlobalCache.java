package com.glogs.init.cache;

import java.util.List;

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
	
	/*get  set */
	
	public List<CacheInit> getCacheInit() {
		return cacheInit;
	}

	public void setCacheInit(List<CacheInit> cacheInit) {
		this.cacheInit = cacheInit;
	}

}
