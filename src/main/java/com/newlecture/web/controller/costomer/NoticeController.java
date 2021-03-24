package com.newlecture.web.controller.costomer;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@Controller
@RequestMapping("/customer/notice/")
public class NoticeController {
	
	private NoticeService noticeService;
	
	@Autowired
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
		
	@RequestMapping("list")
	//public String list(HttpServletRequest request) {
	//public String list(@RequestParam(name = "p", defaultValue = "1") int page) { //String page) {
	public String list(@RequestParam(name = "p", required = false) String page, ModelMap modelMap) {
		//String p = request.getParameter("p");
		System.out.printf("page: %s\n", page);
		try {
			List<Notice> list = this.noticeService.getList(1, "TITLE", "");
			modelMap.addAttribute("list", list);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "notice.list";
	}
	
	//@RequestMapping("/customer/notice/detail")
	@RequestMapping("detail")
	public String detail(@RequestParam(name = "id", required = true) String id, ModelMap modelMap) {
		Notice notice = null;
		
		notice = this.noticeService.getItem(id);
		modelMap.addAttribute("item", notice);
		return "notice.detail";
	}
	
}
