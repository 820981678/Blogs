package com.blogs.web.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.glogs.init.properties.PropertiesConfigurer;
import com.glogs.util.GlobalLogger;
import com.glogs.util.PublicKey;
import com.util.LogsUtil;

/**
 * 需要登陆的url拦截
 * 
 * @ClassName: MustLoginRequestInterceptor 
 * @author huxiaohuan 
 * @date 2014年11月7日 下午15:58:30
 * @version V1.0
 */
public class MustLoginRequestInterceptor extends HandlerInterceptorAdapter implements InitializingBean {
	
	/**
	 * 所有需要登陆拦截的Url请求地址
	 */
	private static Set<String> mustLoginUrl = new HashSet<String>();
	
	private static Logger log = GlobalLogger.interceptor;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String servletPath = request.getServletPath();
		HttpSession session = request.getSession();
		
		//需要拦截
		if(discharged(servletPath)){
			//获取session中是否有登陆信息
			Object obj = session.getAttribute(PublicKey.SESSION_USER_KEY);
			if(obj == null){
				response.sendRedirect(getBaseUrl(request) + "static/exception/must_login.html");
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info(LogsUtil.LINE);
		log.info(LogsUtil.PREFIX2 + "MustLoginRequestInterceptor init ...");
		
		//初始化要拦截的url集合
		String[] urls = PropertiesConfigurer.getStringValueByKey(PublicKey.MUST_LOGIN_URL).split(",");
		for (String s : urls) {
			mustLoginUrl.add(s);
		}
		log.info(LogsUtil.PREFIX3 + "value: " + mustLoginUrl);
        log.info(LogsUtil.PREFIX3 + "StaticTemplateFilter init... is complete");
	}
	
	/**
	 * 判断是否需要进行拦截
	 * 
	 * @param requestPath
	 * @return 需要拦截返回 true
	 */
	private boolean discharged(String requestPath){
		if(mustLoginUrl.contains(requestPath)){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取请求的根路径
	 * 
	 * @param request
	 * @return
	 */
	private String getBaseUrl(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		return basePath;
	}
	
}
