package com.blogs.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.connection.db.DBException;
import com.glogs.entity.account.Token;
import com.glogs.entity.account.User;
import com.glogs.service.user.UserService;
import com.glogs.service.user.impl.UserServiceImpl;
import com.glogs.util.GlobalLogger;
import com.glogs.util.PublicKey;

public class AccountInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger log = GlobalLogger.interceptor;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		// 当前用户第一次访问web, 则为其自动分配新的session.
		HttpSession session = request.getSession();
		Object u = session.getAttribute(PublicKey.SESSION_USER_KEY);
		if(null != u){
			return true;
		}
		
		Cookie[] cookies = request.getCookies();
		String userName = "";
		boolean is_cookie = false;
		for (Cookie ck : cookies) {
			if(ck.getName().equals(PublicKey.COOKIE_USER_KEY)){
				is_cookie = true;
				userName = ck.getValue();
				break;
			}
		}
		
		//当cookie中有值  session中没值时  登陆
		if(is_cookie){
			//直接将用户设置到session中
			// TODO 完成用户的登陆
			UserService userService = new UserServiceImpl();
			User user;
			try {
				user = userService.login_name(userName);
				Token token = new Token(user);
				session.setAttribute(PublicKey.SESSION_USER_KEY, token);
			} catch (DBException e) {
				log.error("使用cookie登陆异常", e);
			}
			
		}
		
		return true;
	}
	
}
