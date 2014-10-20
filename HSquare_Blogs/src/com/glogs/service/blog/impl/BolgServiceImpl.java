package com.glogs.service.blog.impl;

import java.util.Map;

import com.connection.db.DBException;
import com.connection.db.DBHandle;
import com.glogs.entity.blog.Blog;
import com.glogs.service.blog.BlogService;

/**
 * 博客DB服务 实现类
 * 
 * @ClassName: BolgServiceImpl 
 * @author huxiaohuan 
 * @date 2014年10月11日 下午2:08:07 
 * @version V1.0
 */
public class BolgServiceImpl implements BlogService {

	/**
	 * 插入一条博客
	 * 
	 * @param blog
	 * @return 返回插入数据的id
	 * @throws DBException 
	 */
	@Override
	public int add(Blog blog) throws DBException {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ").append(Blog.DB_NAME);
		sql.append("(TITLE,OVERVIEW,BTAGID,BTYPE,USERID,CREATETIME,UPDATETIME,CHECKNUM,STATE) VALUES(?,?,?,?,?,?,?,?,?)");
		Object[] params = new Object[]{
			blog.getTitle(),blog.getOverview(),blog.getBtagId(),blog.getBtype(),
			blog.getUserId(),blog.getCreateTime(),blog.getUpdateTime(),blog.getCheckNum(),
			blog.getState()
		};
		int code = DBHandle.exceute(sql.toString(), params);
		int backId = 0;
		if(code != 0){
			Map<String, Object> map = DBHandle.queryFirst("SELECT LAST_INSERT_ID()", new Object[0]);
			backId = Integer.valueOf(map.get("LAST_INSERT_ID()").toString());
		}
		return backId;
	}

	/**
	 * 插入博客的内容
	 * 
	 * @param blogId 该内容对应blog的id
	 * @param content 博客内容
	 * @return
	 */
	@Override
	public int addBlogContent(Integer blogId, String content) throws DBException {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ").append(Blog.CONTENT_DB_NAME);
		sql.append("(CONTENT,BLOGID) VALUES(?,?)");
		Object[] params = new Object[]{
			content,blogId
		};
		return DBHandle.exceute(sql.toString(), params);
	}
	
}
