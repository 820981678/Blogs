package com.blogs.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.glogs.entity.account.Token;
import com.glogs.entity.account.User;
import com.glogs.util.PublicKey;

public class AccountInterceptor extends HandlerInterceptorAdapter {

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
		boolean is_cookie = false;
		for (Cookie ck : cookies) {
			if(ck.getName().equals(PublicKey.COOKIE_USER_KEY)){
				is_cookie = true;
				break;
			}
		}
		
		//当cookie中有值  session中没值时  登陆
		if(is_cookie){
			//直接将用户设置到session中
			// TODO 完成用户的登陆
			User user = new User();
			user.setName("Apple");
			Token token = new Token(user);
			session.setAttribute(PublicKey.SESSION_USER_KEY, token);
		}
		
		return true;
	}
	
}
