package com.blogs.web.action.blogs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.blogs.web.action.BaseController;
import com.connection.db.DBException;
import com.connection.db.DBHandle;
import com.glogs.emu.blog.BType;
import com.glogs.emu.blog.BlogState;
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
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(value="index.do")
	public ModelAndView index(String type){
		return new ModelAndView("/blogs/index");
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
	public Map<String, Object> release(Blog blog,String editorValue,String[] btags){
		Map<String, Object> map = new HashMap<String, Object>();
		
		//初始化blog对象
		blog.setCheckNum(0);
		blog.setState(BlogState.FB.getKey());
		blog.setCreateTime(new Date());
		blog.setUpdateTime(new Date());
		blog.setUserId(0);
		
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
