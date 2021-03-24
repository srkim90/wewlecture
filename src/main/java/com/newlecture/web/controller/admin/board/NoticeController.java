package com.newlecture.web.controller.admin.board;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {

		@RequestMapping("list")
		public String list() {
			
			return "";
		}
		
		@RequestMapping("reg")
		@ResponseBody
		public String reg(String title, String content, String category, String[] foods, String food) {
			for(String item : foods){
				// for item in foods:
				System.out.println(item);
			}
			System.out.printf("food:%s\n", food);
			return String.format("Title:%s<br>Content:%s<br>, category:%s", title, content, category);
		}
		
		@RequestMapping("edit")
		public String edit() {
			
			return "";
		}
}
