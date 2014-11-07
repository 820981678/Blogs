package com.blogs.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.glogs.entity.account.Token;
import com.glogs.entity.account.User;
import com.glogs.util.GlobalLogger;
import com.glogs.util.PublicKey;

public class BaseController {
	
	/**
	 * 日志记录器
	 */
	protected static Logger log = GlobalLogger.controller;
	
	/**
	 * 获取当前登录用户
	 * 
	 * @param request
	 * @return
	 */
	protected User getUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(PublicKey.SESSION_USER_KEY);
		if(obj instanceof Token){
			Token token = (Token)obj;
			return token.getUser();
		}
		return null;
	}
	
}
