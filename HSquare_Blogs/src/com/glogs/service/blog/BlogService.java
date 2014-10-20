package com.glogs.service.blog;

import com.connection.db.DBException;
import com.glogs.entity.blog.Blog;

/**
 * 博客DB服务
 * 
 * @ClassName: BlogService 
 * @author huxiaohuan 
 * @date 2014年10月11日 下午2:02:39 
 * @version V1.0
 */
public interface BlogService {
	
	/**
	 * 插入一条博客
	 * 
	 * @param blog
	 * @return
	 */
	int add(Blog blog) throws DBException ;
	
	/**
	 * 插入博客的内容
	 * 
	 * @param blogId 该内容对应blog的id
	 * @param content 博客内容
	 * @return
	 */
	int addBlogContent(Integer blogId,String content) throws DBException ;
}
