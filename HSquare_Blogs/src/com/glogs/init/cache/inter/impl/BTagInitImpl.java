package com.glogs.init.cache.inter.impl;

import java.util.List;

import com.connection.db.DBException;
import com.connection.db.DBHandle;
import com.glogs.entity.blog.BTag;
import com.glogs.init.cache.GlobalCache;
import com.glogs.init.cache.inter.CacheInit;
import com.util.LogsUtil;

/**
 * 文章标签 缓存初始化 实现类
 * 
 * @ClassName: BlogTypeInitImpl 
 * @author huxiaohuan 
 * @date 2014年10月10日 上午10:05:28 
 * @version V1.0
 */
public class BTagInitImpl implements CacheInit {
	
	@Override
	public void init() {
        log.info(LogsUtil.PREFIX2 + "BTag Load ...");
        
        String sql = "SELECT * FROM " + BTag.BD_NAME;
        List<BTag> result = null;
        try {
        	result = DBHandle.query(sql, new Object[0], BTag.class);
		} catch (DBException e) {
			log.error(LogsUtil.PREFIX3 + "select blogtype DataBase error", e);
		}
        GlobalCache.global_btag = result;
        
        log.info(LogsUtil.PREFIX3 + "BlogType Loading is complete");
	}

}
