package com.blogs.web.action.temp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/guanggao")
@Controller
public class GuangGao_1_Controller {
	
	@RequestMapping("/_1")
	public ModelAndView _1(){
		ModelAndView model = new ModelAndView();
		
		model.setViewName("/include/guanggao/_1");
		return model;
	}
	
}
