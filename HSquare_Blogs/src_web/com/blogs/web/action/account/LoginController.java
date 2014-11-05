package com.blogs.web.action.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.glogs.entity.account.Token;
import com.glogs.entity.account.User;
import com.glogs.util.PublicKey;

/**
 * 登陆处理控制器
 * 
 * @ClassName: BlogState 
 * @author huxiaohuan 
 * @date 2014年10月28日 上午12:00:00
 * @version V1.0
 */
@RequestMapping(value="/login")
@Controller
public class LoginController {
	
	/**
	 * 跳转到登陆页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/toLogin")
	public ModelAndView toLogin(){
		ModelAndView model = new ModelAndView();
		model.setViewName("/account/login/index");
		return model;
	}
	
	@RequestMapping(value="/doLogin")
	@ResponseBody
	public Map<String, Object> doLogin(User user,boolean isSave,HttpServletResponse response,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		
		// TODO 完成用户的登陆
		
		//模拟测试登陆成功
		if(!user.getName().equals("Apple") && !user.getPassword().equals("1")){
			map.put("code", 1);
		}
		
		//设置cookie
		Cookie cookie_user = new Cookie(PublicKey.COOKIE_USER_KEY, user.getName());
		cookie_user.setPath("/");
		//是否保存一周
		if(isSave){
			cookie_user.setMaxAge(604800);
		}
		response.addCookie(cookie_user);
		
		//设置session
		Token token = new Token(user);
		HttpSession session = request.getSession();
		session.setAttribute(PublicKey.SESSION_USER_KEY, token);
		
		map.put("code", 0);
		return map;
	}
	
}
