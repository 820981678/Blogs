package com.blogs.web.action.blogs;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.blogs.web.action.BaseController;
import com.connection.db.DBException;
import com.connection.db.DBHandle;
import com.connection.page.Page;
import com.glogs.emu.blog.BType;
import com.glogs.emu.blog.BlogState;
import com.glogs.entity.blog.BTag;
import com.glogs.entity.blog.Blog;
import com.glogs.init.cache.GlobalCache;
import com.glogs.service.blog.BlogService;

@RequestMapping(value="/blogs")
@Controller
public class BlogsController extends BaseController {
	
	/**
	 * DB 数据服务
	 */
	@Resource
	private BlogService blogServiceImpl;
	
	/**
	 * 跳转到 所有博客页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/index.do")
	public ModelAndView index(){
		ModelAndView model = new ModelAndView();
		model.setViewName("/blogs/index");
		return model;
	}
	
	/**
	 * 分页查询所有博客,跳转到博客展示页
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/query.do")
	public ModelAndView query(@RequestParam(value="pageNo",required=false)Integer pageNo, @RequestParam(value="pageSize",required=false)Integer pageSize){
		ModelAndView model = new ModelAndView();
		//初始化分页参数
		if(pageNo == null && pageSize == null){
			pageNo = 1;
			pageSize = 10;
		}
		Page<Blog> page = null;
		try {
			page = blogServiceImpl.queryBlog(pageNo, pageSize);
			System.out.println(page);
		} catch (DBException e) {
			log.error("find query error", e);
		}
		model.addObject("page", page);
		model.setViewName("/blogs/index");
		return model;
	}
	
	/**
	 * 分页查询所有博客,跳转到博客展示页(ajax方式)
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/query_ajax.do")
	@ResponseBody
	public Map<String, Object> query_ajax(@RequestParam(value="pageNo",required=false)Integer pageNo, @RequestParam(value="pageSize",required=false)Integer pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		//初始化分页参数
		if(pageNo == null && pageSize == null){
			pageNo = 1;
			pageSize = 10;
		}
		Page<Blog> page = null;
		try {
			page = blogServiceImpl.queryBlog(pageNo, pageSize);
			//设置页面所需的 标签名称
			for(int i = 0; i < page.getResult().size(); i++){
				page.getResult().get(i).setBtagName(GlobalCache.getBtagById(page.getResult().get(i).getBtagId()).getTagName());
			}
			map.put("code", 0);
			map.put("page", page);
			
		} catch (DBException e) {
			log.error("find query error", e);
			map.put("code", 1);
		}
		return map;
	}
	
	/**
	 * 跳转到发布博客页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/addBlog.do")
	public ModelAndView addBlog(){
		ModelAndView model = new ModelAndView();
		//设置文章标签
		model.addObject("btag", GlobalCache.global_btag);
		//设置文章类型
		model.addObject("btype", BType.toList());
		
		model.setViewName("/blogs/insertBlog/index");
		return model;
	}
	
	/**
	 * 发布文章
	 * 
	 * @param blog
	 * @param editorValue 文章内容
	 * @param btags 文章标签 集合
	 * @return
	 */
	@RequestMapping(value="/release.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> release(Blog blog,String editorValue){
		Map<String, Object> map = new HashMap<String, Object>();
		
		//初始化blog对象
		blog.setCheckNum(0);
		blog.setState(BlogState.FB.getKey());
		blog.setCreateTime(new Date());
		blog.setUpdateTime(new Date());
		// TODO 完成对发布人的设置
		blog.setUserId(0);
		blog.setUserName("Apple");
		
		try {
			DBHandle.beginTransation();
			int _i = blogServiceImpl.add(blog);
			int _j = blogServiceImpl.addBlogContent(_i, editorValue);
			DBHandle.commit();
			map.put("code", _j != 0 ? 0 : 1);
		} catch (DBException e) {
			log.error("insert blog error", e);
			map.put("code", 1);
		} finally {
			DBHandle.release();
		}
		
		return map;
	}
	
}
