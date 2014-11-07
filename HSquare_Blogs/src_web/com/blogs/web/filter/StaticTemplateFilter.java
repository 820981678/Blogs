package com.blogs.web.filter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.glogs.init.cache.GlobalCache;
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
		if(!discharged(servletPath)){
			chain.doFilter(arg0, response);
			return;
		}
		
		PrintWriter out = null;
		BufferedReader br = null;
		try{
			String htmlPath = PropertiesConfigurer.getStringValueByKey("freemarker.static.path") + servletPath;
			InputStreamReader inr = new InputStreamReader(new FileInputStream(htmlPath),"UTF-8");
			
			br = new BufferedReader(inr);
			out = null;
			String data = null;
			out = response.getWriter();
			while( (data = br.readLine()) != null){
				out.println(data);
			}
		} catch(FileNotFoundException e){
			logger.error("not found file", e);
			((HttpServletResponse)response).sendRedirect("../static/exception/notfound_file.html");
		} catch (IOException e){
			logger.error("out template html error", e);
			((HttpServletResponse)response).sendRedirect("../static/exception/notfound_file.html");
		} finally {
			br.close();
			out.flush();
			out.close();
		}
		return;
	}
	
	/**
	 * 判断是否需要进行拦截
	 * 
	 * @param requestPath
	 * @return 需要拦截返回 true
	 */
	private boolean discharged(String requestPath){
		if(GlobalCache.global_discharged.contains(requestPath)){
			return false;
		}
		return true;
	}

}
