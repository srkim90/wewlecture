package com.newlecture.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController{ // implements Controller


//	@RequestMapping("index")
//	//public String index(HttpServletResponse response) {
//	public void index(HttpServletResponse response) {
//		PrintWriter out;
//		try {
//			out = response.getWriter();
//			out.println("ddddddd");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return;
//		//return "root.index";
//		
//	}

	
	@RequestMapping("index")
	//@ResponseBody()
	public String index() {
		//return "Hello Index";
		return "root.index";
	}
	
	
//	
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// TODO Auto-generated method stub
//		
//		ModelAndView mv = new ModelAndView("root.index");
//		mv.addObject("data", "Hello Spring MVC");
//		//mv.setViewName("/WEB-INF/view/index.jsp");
//		return mv;
//	}

}
 