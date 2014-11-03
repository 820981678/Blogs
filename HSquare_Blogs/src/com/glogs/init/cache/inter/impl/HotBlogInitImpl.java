package com.glogs.init.cache.inter.impl;

import java.util.List;
import java.util.Map;

import com.connection.db.DBException;
import com.connection.db.DBHandle;
import com.glogs.entity.blog.Blog;
import com.glogs.init.cache.GlobalCache;
import com.glogs.init.cache.inter.CacheInit;
import com.util.LogsUtil;

public class HotBlogInitImpl implements CacheInit {

	@Override
	public void init() {
		log.info(LogsUtil.PREFIX2 + "HotBlog Load ...");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT TITLE,TEMPLATE ").append("FROM ").append(Blog.DB_NAME);
		sql.append(" WHERE TEMPLATE IS NOT NULL) t LIMIT 0,200");
		
		List<Map<String, Object>> result = null;
		try {
			result = DBHandle.query(sql.toString(), new Object[0]);
		} catch (DBException e) {
			log.error(LogsUtil.PREFIX3 + "select blog DataBase error", e);
		}
		GlobalCache.global_hotBlog = result;

		log.info(LogsUtil.PREFIX3 + "HotBlog Loading is complete");
	}

}
