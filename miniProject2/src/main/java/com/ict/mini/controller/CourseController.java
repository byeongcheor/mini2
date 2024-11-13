package com.ict.mini.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.mini.service.CourseService;
import com.ict.mini.vo.CourseVO;
import com.ict.mini.vo.PagingVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/course")
@Slf4j
public class CourseController {
	@Autowired
	CourseService service;
	//�ڽ����
	@GetMapping("/courseList")
	public String courseList(CourseVO vo, PagingVO pvo, Model model) {
		service.updateReplyCount(vo.getNews_no());
		pvo.setTotalRecord(service.totalRecord(pvo));
		List<CourseVO> list = service.courseSelectPaging(pvo);
		log.info(pvo.toString());
		model.addAttribute("list", list);
		model.addAttribute("pvo", pvo);
		return "/board/course/courseList";
	}
	//�ڽ�¥�� �̵�
	@GetMapping("/courseWrite")
	public String courseWrite() {
		return "/board/course/courseWrite";
	}
//	//�ڽ����
//	@PostMapping("/courseWriteResult")
//	public String newsWriteOK(CourseVO vo, HttpServletRequest request, Model model){
//		//vo->����,�۳���
//		//request -> ip login Id
//		vo.setIp(request.getRemoteAddr());	
//		vo.setUserid((String)request.getSession().getAttribute("logId"));
//		String content = vo.getContent();
//		System.out.println(content);
//		int new_no = vo.getNews_no();
//		Pattern nonValidPattern = Pattern
//		  		.compile("(?i)< *[IMG][^\\>]*[src] *= *[\"\']{0,1}([^\"\'\\ >]*)");
//		  		int imgCnt = 0;
//		  		String img = "";
//		  		Matcher matcher = nonValidPattern.matcher(content);
//		  		while (matcher.find()) {
//		  			img = matcher.group(1);
//		  			imgCnt++;
//		  			if(imgCnt == 1){
//		  		        break;                                  
//		  		    }
//		  		}
//		vo.setThumb(img);
//		System.out.println(img);
//		int result=0;
//		try {
//				result=service.courseInsert(vo);
//				
//			}catch(Exception e) {}
//		model.addAttribute("result",result);
//		return "redirect:courseList";
//		}
	//�ڽ����
	@PostMapping("/courseWriteResult")
	public String newsWriteOK(CourseVO vo, HttpServletRequest request, Model model){
		//vo->����,�۳���
		//request -> ip login Id
		log.info("content =>{}", vo.toString());
		vo.setIp(request.getRemoteAddr());	
		vo.setUserid((String)request.getSession().getAttribute("logId"));
		
		
		int s = vo.getContent().indexOf("<img src=\"");
		int l = vo.getContent().indexOf("\">", s);
		String img = vo.getContent().substring(s+10, l);
		log.info("img=>{}", img);
		vo.setThumb(img);
		int result=0;
		try {
				result=service.courseInsert(vo);
				model.addAttribute("result",result);
		}catch(Exception e) {}
		return "/board/course/courseWriteResult";
	}

	//�ڽ����뺸��
	@GetMapping("/courseView")
	public ModelAndView courseView(@RequestParam("news_no") int news_no, HttpSession session){
		//��ȸ������
		String logId = (String)session.getAttribute("logId");//session�� �ִ� logId�� ������
		log.info(logId);
		service.hitCount(news_no, logId);
		service.updateReplyCount(news_no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.courseSelect(news_no));
		mav.setViewName("board/course/courseView");
		return mav;
	}
	//�ڽ������ϱ�
	@GetMapping("/courseEdit")
	public String courseEdit(int news_no, Model model) {
		model.addAttribute("vo", service.courseSelect(news_no));
		return "/board/course/courseEdit";
	}
	//�ڽ�����(DB)
	@PostMapping("/courseEditOk")
	public ModelAndView courseEditOk(CourseVO vo, HttpSession session) {
		vo.setUserid((String)session.getAttribute("logId"));
		int result = service.courseUpdate(vo);
		
		ModelAndView mav = new ModelAndView();
		if(result>0) {
			//��������
			mav.addObject("news_no", vo.getNews_no());
			mav.setViewName("redirect:courseView");
		}else {
			//��������
			mav.setViewName("course/courseEditResult");
		}
		return mav;
	}
	//�ڽ�����
	@GetMapping("/courseDel")
	public String courseDel(int news_no, HttpSession session) {
		String userid = (String)session.getAttribute("logId");
		int result = service.courseDelete(news_no, userid);
		
		if(result>0) {
			//������
			return "redirect:courseList";
		}else {
			//���н�
			return "redirect:courseView?news_no="+news_no;
		}
	}
	
}