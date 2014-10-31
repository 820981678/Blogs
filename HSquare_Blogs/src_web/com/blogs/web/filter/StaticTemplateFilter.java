package com.blogs.web.filter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.glogs.init.properties.PropertiesConfigurer;
import com.glogs.util.GlobalLogger;
import com.util.LogsUtil;

public class StaticTemplateFilter implements Filter {
	
	// 日志记录器.
    public static final Logger logger = GlobalLogger.init_filter;
    
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		if (logger.isInfoEnabled()) {
            logger.info(LogsUtil.LINE);
            logger.info(LogsUtil.PREFIX2 + "StaticTemplateFilter init ...");
            logger.info(LogsUtil.PREFIX3 + "StaticTemplateFilter init... is complete");
        }
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		String servletPath = request.getServletPath();
		String htmlPath = PropertiesConfigurer.getStringValueByKey("freemarker.static.path") + servletPath;
		InputStreamReader inr = new InputStreamReader(new FileInputStream(htmlPath),"UTF-8");
		
		BufferedReader br = new BufferedReader(inr);
		PrintWriter out = null;
		String data = null;
		try{
			out = response.getWriter();
			while( (data = br.readLine()) != null){
				out.println(data);
			}
		} catch (IOException e){
			logger.error("out template html error", e);
			// TODO 完成没有该文件的友好提示
			out.write("not found this html page!");
		} finally {
			br.close();
			out.flush();
			out.close();
		}
		//chain.doFilter(request, response);
	}

}
