package com.ict.mini.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ict.mini.service.CourseReplyService;
import com.ict.mini.vo.CourseReplyVO;

@RestController
@RequestMapping("/courseReply")
public class CourseReplyController {
	@Inject
	CourseReplyService service;
	
	//��۵��
		@PostMapping("/writeOk")
		public String writeOk(CourseReplyVO vo, HttpSession session) {
			vo.setUserid((String)session.getAttribute("logId"));
			
			int result=0;
		      try {
		         result = service.replyInsert(vo);
		      }catch(Exception e) {
		         result=0;
		         
		      }
			
		      return result+"";//"1"String���� �����ؾߵǼ� +""�� ���ڿ� ������
		}
		
		//��۸�ϼ���
		@GetMapping("/list")
		public List<CourseReplyVO> list(int news_no) {
			List<CourseReplyVO> list = service.replySelectList(news_no);
			for(CourseReplyVO v : list) {
				System.out.println(v.getComments());
			}
			return list;
		}
		
		//��ۼ���
		@PostMapping("/edit")
		public String edit(CourseReplyVO vo, HttpSession session) {
			vo.setUserid((String)session.getAttribute("logId"));
			//��۹�ȣ, ��۳���, �α��ξ��̵�
			return String.valueOf(service.replyUpdate(vo));
		}
		@GetMapping("/delete")
		public String delete(int reply_no, HttpSession session) {
			String userid = ((String)session.getAttribute("logId"));
			
			return String.valueOf(service.replyDelete(reply_no, userid));
		}
}
