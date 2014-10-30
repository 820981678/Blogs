package com.generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import junit.framework.TestCase;

import org.springframework.beans.factory.InitializingBean;

import com.glogs.init.properties.PropertiesConfigurer;
import com.util.FileUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 博客静态页面生成
 * 
 * @ClassName: BlogState 
 * @author huxiaohuan 
 * @date 2014年10月28日 上午16:00:00
 * @version V1.0
 */
public class BlogStatic extends TestCase implements InitializingBean {
	
	private Configuration cfg;
	
	/**
	 * 存放当前线程 生成html静态文件 作用于a标签的路径
	 */
	private ThreadLocal<String> templatePathLoacl = new ThreadLocal<String>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		cfg = new Configuration();
		String rootPath = FileUtil.BASE_PATH;
		System.out.println(rootPath);
		cfg.setDirectoryForTemplateLoading(new File(rootPath + "template/"));
	}
	
	/**
	 * 生成静态模板
	 * 
	 * @return 
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public void createTemplate(Map<String, Object> root,String template) throws IOException, TemplateException {
		//读取模板，设置编码
		Template t = cfg.getTemplate(template,"UTF-8");
		t.setEncoding("UTF-8");
		
		String templatePath = PropertiesConfigurer.getStringValueByKey("freemarker.static.path");
		String directory = templatePathLoacl.get();
		
		Writer out = null;
		try{
			out = new OutputStreamWriter(new FileOutputStream(templatePath + directory), "UTF-8");
			t.process(root, out);
		} finally{
			out.flush();
			out.close();
		}
		
	}
	
	/**
	 * 创建html静态文件存放路径<br/>
	 * 不存在则创建
	 * 
	 * @return 返回创建的文件夹路径<br/>
	 * 			只返回PropertiesConfigurer.getStringValueByKey("freemarker.static.path")后面的路径
	 */
	public String validatePath(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		
		String templateFileName = "" + year + month + "/" + String.valueOf(System.currentTimeMillis()) + ".html";
		
		String directory = PropertiesConfigurer.getStringValueByKey("freemarker.static.path") + year + month;
		File dir = new File(directory);
		if(!dir.exists()){
			dir.mkdirs();
		}
		//设置当前线程产生的 路径地址
		templatePathLoacl.set(templateFileName);
		return templateFileName;
	}
	
}
