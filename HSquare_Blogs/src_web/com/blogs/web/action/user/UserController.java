package com.blogs.web.action.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	/**
	 * 返回该id的用户基本信息
	 * html格式返回 用于嵌入页面中
	 */
	@RequestMapping(value="/user/{uesrid}")
	public ModelAndView query(@PathVariable("uesrid")String userid){
		ModelAndView model = new ModelAndView();
		
		
		model.setViewName("/user/data/user_data");
		return model;
	}
	
}
