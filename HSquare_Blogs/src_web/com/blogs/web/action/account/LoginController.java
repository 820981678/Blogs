package com.blogs.web.action.account;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	@RequestMapping(value="/toLogin.do")
	public ModelAndView toLogin(){
		ModelAndView model = new ModelAndView();
		
		model.setViewName("/account/login/index");
		return model;
	}
	
}
