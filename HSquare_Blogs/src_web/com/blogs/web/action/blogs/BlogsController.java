package com.blogs.web.action.blogs;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.blogs.web.action.BaseController;
import com.connection.db.DBException;
import com.connection.db.DBHandle;
import com.connection.page.Page;
import com.generate.BlogStatic;
import com.glogs.emu.blog.BType;
import com.glogs.entity.blog.BTag;
import com.glogs.entity.blog.Blog;
import com.glogs.init.cache.GlobalCache;
import com.glogs.service.blog.BlogService;

import freemarker.template.TemplateException;

@RequestMapping(value="/blogs")
@Controller
public class BlogsController extends BaseController {
	
	/**
	 * DB 数据服务
	 */
	@Resource
	private BlogService blogServiceImpl;
	
	/**
	 * 静态页面生成服务
	 */
	@Resource
	private BlogStatic blogStatic;
	
	/**
	 * 跳转到 所有博客页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/index.do")
	public ModelAndView index(){
		ModelAndView model = new ModelAndView();
		//设置文章标签
		List<BTag> global_btag = GlobalCache.global_btag;
		model.addObject("global_btag", global_btag);
		
		model.setViewName("/blogs/index/index");
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
	public ModelAndView query(
			@RequestParam(value="pageNo",required=false)Integer pageNo, 
			@RequestParam(value="pageSize",required=false)Integer pageSize){
		
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
		model.setViewName("/blogs/index/index");
		return model;
	}
	
	/**
	 * 分页查询所有博客(ajax方式)
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param btagId 是否根据 btagid 进行查询
	 * @return
	 */
	@RequestMapping(value="/query_ajax.do")
	@ResponseBody
	public Map<String, Object> query_ajax(
			@RequestParam(value="pageNo",required=false)Integer pageNo, 
			@RequestParam(value="pageSize",required=false)Integer pageSize,
			@RequestParam(required=false)Integer btagId ){
		
		Map<String, Object> map = new HashMap<String, Object>();
		//初始化分页参数
		if(pageNo == null && pageSize == null){
			pageNo = 1;
			pageSize = 10;
		}
		Page<Blog> page = null;
		try {
			if(btagId != null){
				page = blogServiceImpl.queryBlogByBtag(pageNo, pageSize, btagId);
			} else {
				page = blogServiceImpl.queryBlog(pageNo, pageSize);
			}
			
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
	 * @return code 0 正常，1创建静态文件失败，2创建文件夹失败
	 */
	@RequestMapping(value="/release.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> release(Blog blog,String editorValue){
		Map<String, Object> map = new HashMap<String, Object>();
		
		//初始化blog对象
		blog.setCheckNum(0);
		blog.setCreateTime(new Date());
		blog.setUpdateTime(new Date());
		// TODO 完成对发布人的设置
		blog.setUserId(0);
		blog.setUserName("Apple");
		
		try {
			DBHandle.beginTransation();
			int _i = blogServiceImpl.add(blog);
			int _j = blogServiceImpl.addBlogContent(_i, editorValue);
			
			map.put("code", _j != 0 ? 0 : 1);
			
			//生成静态页面
			Map<String, Object> val = new HashMap<String, Object>();
			val.put("blog", blog);
			val.put("blogText", editorValue);
			blogStatic.createTemplate(val, "index.ftl");
			
			DBHandle.commit();
		} catch (DBException e) {
			log.error("insert blog error", e);
			map.put("code", 1);
		} catch (TemplateException e){
			log.error("create static template html error", e);
			map.put("code", 2);
		} catch(IOException e){
			log.error("find create DirectoryForTemplateLoading error", e);
			map.put("code", 3);
		}finally {
			DBHandle.release();
		}
		
		return map;
	}
	
	/**
	 * 查看博客
	 * 
	 * @param blogId
	 * @return
	 */
	@RequestMapping(value="/seeBlog.do")
	public ModelAndView seeBlog(@RequestParam(required=false)Integer blogId){
		ModelAndView model = new ModelAndView();
		try {
			Blog blog = blogServiceImpl.queryBlogById(blogId);
			blog.setBtagName(GlobalCache.getBtagById(blog.getBtagId()).getTagName());
			
			String blogText = blogServiceImpl.queryBlogTextByBlogId(blogId);
			
			model.addObject("blog", blog);
			model.addObject("blogText", blogText);
		} catch (DBException e) {
			log.error("", e);
		}
		
		model.setViewName("/blogs/see/index");
		return model;
	}
	
}
