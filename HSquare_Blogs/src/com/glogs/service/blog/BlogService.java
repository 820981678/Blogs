package com.glogs.service.blog;

import com.connection.db.DBException;
import com.connection.page.Page;
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
	 * 分页查询所有博客
	 * 
	 * @param pageNo 当前页码
	 * @param pageSize 每页显示条数
	 * @return
	 * @throws DBException
	 */
	Page<Blog> queryBlog(int pageNo, int pageSize) throws DBException;
	
	/**
	 * 分页查询博客 (根据Btag id 查询)
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param btagid
	 * @return
	 */
	Page<Blog> queryBlogByBtag(int pageNo, int pageSize, Integer btagid) throws DBException;
	
	/**
	 * 根据id查询博客实体
	 * 
	 * @param id
	 * @return
	 * @throws DBException
	 */
	Blog queryBlogById(Integer id) throws DBException;
	
	/**
	 * 根据id查询 该博客的 内容
	 * 
	 * @param id
	 * @return
	 * @throws DBException
	 */
	String queryBlogTextByBlogId(Integer id) throws DBException;
	
	/**
	 * 插入一条博客
	 * 
	 * @param blog
	 * @return
	 */
	int add(Blog blog) throws DBException;
	
	/**
	 * 插入博客的内容
	 * 
	 * @param blogId 该内容对应blog的id
	 * @param content 博客内容
	 * @return
	 */
	int addBlogContent(Integer blogId,String content) throws DBException;
	
	/**
	 * 更新博客的点击量
	 * 
	 * @param blogId
	 * @param checkNum
	 * @return
	 */
	int updateCheckNum(Integer blogId,Integer checkNum) throws DBException;
}
