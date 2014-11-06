package com.blogs.web.action.blogs;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.glogs.runservice.checknum.CheckNumService;
import com.glogs.service.blog.BlogService;

import freemarker.template.TemplateException;

@RequestMapping(value="/blogs")
@Controller
public class BlogsController extends BaseController {
	
	/**
	 * DB 数据服务
	 */
	@Resource
	private BlogService blogService;
	
	/**
	 * 静态页面生成服务
	 */
	@Resource
	private BlogStatic blogStatic;
	
	/**
	 * 博客点击量服务
	 */
	@Resource
	private CheckNumService checkNumService;
	
	/**
	 * 跳转到 所有博客页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/index")
	public ModelAndView index() throws Exception {
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
	@RequestMapping(value="/query")
	public ModelAndView query(
			@RequestParam(value="pageNo",required=false)Integer pageNo, 
			@RequestParam(value="pageSize",required=false)Integer pageSize) throws DBException {
		
		ModelAndView model = new ModelAndView();
		//初始化分页参数
		if(pageNo == null && pageSize == null){
			pageNo = 1;
			pageSize = 10;
		}
		Page<Blog> page = null;
		try {
			page = blogService.queryBlog(pageNo, pageSize);
			System.out.println(page);
		} catch (DBException e) {
			log.error("find query error", e);
			throw e;
		} finally {
			DBHandle.release();
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
	@RequestMapping(value="/query_ajax")
	@ResponseBody
	public Map<String, Object> query_ajax(
			@RequestParam(value="pageNo",required=false)Integer pageNo, 
			@RequestParam(value="pageSize",required=false)Integer pageSize,
			@RequestParam(required=false)Integer btagId ) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		//初始化分页参数
		if(pageNo == null && pageSize == null){
			pageNo = 1;
			pageSize = 10;
		}
		Page<Blog> page = null;
		try {
			if(btagId != null){
				page = blogService.queryBlogByBtag(pageNo, pageSize, btagId);
			} else {
				page = blogService.queryBlog(pageNo, pageSize);
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
		} finally {
			DBHandle.release();
		}
		return map;
	}
	
	/**
	 * 跳转到发布博客页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/addBlog")
	public ModelAndView addBlog() throws Exception {
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
	@RequestMapping(value="/release", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> release(Blog blog,String editorValue) throws DBException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//初始化blog对象
		blog.setCheckNum(0);
		blog.setCreateTime(new Date());
		blog.setUpdateTime(new Date());
		//设置标签名称 用于生成静态html文件使用
		blog.setBtagName(GlobalCache.getBtagById(blog.getBtagId()).getTagName());
		// TODO 完成对发布人的设置
		blog.setUserId(0);
		blog.setUserName("Apple");
		
		try {
			//设置html静态文件地址
			blog.setTemplate(blogStatic.validatePath());
			
			DBHandle.beginTransation();
			int _i = blogService.add(blog);
			int _j = blogService.addBlogContent(_i, editorValue);
			
			//设置插入数据库后 blog的id, 用于在模板上生成id
			blog.setId(_i);
			//生成静态页面
			Map<String, Object> val = new HashMap<String, Object>();
			val.put("blog", blog);
			val.put("blogText", editorValue);
			blogStatic.createTemplate(val, "index.ftl");
			
			map.put("code", _j != 0 ? 0 : 1);
			DBHandle.commit();
		} catch (DBException e) {
			log.error("insert blog error", e);
			map.put("code", 1);
			DBHandle.rollback();
		} catch (TemplateException e){
			log.error("create static template html error", e);
			map.put("code", 2);
			DBHandle.rollback();
		} catch(IOException e){
			log.error("find create DirectoryForTemplateLoading error", e);
			map.put("code", 3);
			DBHandle.rollback();
		} finally {
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
	@RequestMapping(value="/seeBlog/{blogId}")
	public ModelAndView seeBlog(@PathVariable("blogId")Integer blogId) throws DBException {
		ModelAndView model = new ModelAndView();
		try {
			Blog blog = blogService.queryBlogById(blogId);
			blog.setBtagName(GlobalCache.getBtagById(blog.getBtagId()).getTagName());
			
			String blogText = blogService.queryBlogTextByBlogId(blogId);
			
			model.addObject("blog", blog);
			model.addObject("blogText", blogText);
		} catch (DBException e) {
			log.error("select blogText is error", e);
			throw e;
		} finally {
			DBHandle.release();
		}
		
		model.setViewName("/blogs/see/index");
		return model;
	}
	
	/**
	 * 查询博客点击量,并对博客点击量 进行了自增长
	 * 
	 * @param blogId
	 * @return
	 */
	@RequestMapping(value="/queryBolgCheckNum")
	@ResponseBody
	public Map<String, Object> queryBolgCheckNum(int blogId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int num = checkNumService.getCheckNum(blogId);
			map.put("code", 0);
			map.put("checkNum", num);
		} catch(DBException e){
			log.error("query blog checkNum error is DBservice", e);
			map.put("code", 1);
		} catch (Exception e) {
			log.error("query blog checkNum error is cache", e);
			map.put("code", 1);
		} finally {
			DBHandle.release();
		}
		return map;
	}
	
	/**
	 * 获取热门推荐文章
	 * 
	 * @return
	 */
	@RequestMapping(value="/queryHotBlog")
	@ResponseBody
	public Map<String, Object> queryHotBlog(){
		List<Map<String, Object>> list = GlobalCache.getHotBlog();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return map;
	}
	
}
