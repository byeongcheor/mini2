package com.ict.mini.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ict.mini.service.CalendarService;
import com.ict.mini.service.CourseService;
import com.ict.mini.service.FestivalService;
import com.ict.mini.service.MemberService;
import com.ict.mini.service.MypageService;
import com.ict.mini.service.RestService;
import com.ict.mini.service.RootPageService;
import com.ict.mini.vo.AnswerVO;
import com.ict.mini.vo.CourseVO;
import com.ict.mini.vo.FestivalVO;
import com.ict.mini.vo.InquiryVO;
import com.ict.mini.vo.MemberVO;
import com.ict.mini.vo.PagingVO;
import com.ict.mini.vo.RestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller

public class RootPageController {

	@Autowired
	FestivalService festivalService;
	@Autowired
	RestService restService;
	@Autowired
	CourseService courseService;
	@Autowired
	MemberService memberService;
	@Autowired
	MypageService mypageService;
	@Autowired
	RootPageService pageService;
	
	//ȸ������ ������ ù ����
	@GetMapping("/mypage/rootpage")
	public String rootpage() {
		
		return "/adminpage/rootpage";
	}

	@GetMapping("/adminpage/mem")
	@ResponseBody
	public Map<String, Object> memlist(PagingVO pVO, @RequestParam("page") int page) {
	    pVO.setNowPage(page);  // ���� ������ ����
	    pVO.setTotalRecord(memberService.totalmem(pVO));  // �� ���ڵ� �� ����

	    // �� ������ �� ���
	    int totalPage = (int) Math.ceil((double) pVO.getTotalRecord() / pVO.getOnePageRecord());
	    pVO.setTotalPage(totalPage);

	    // ������ ����: SQL �������� �� ��° ���ڵ���� �������� ����
	    pVO.setOffset((pVO.getNowPage() - 1) * pVO.getOnePageRecord());

	    // �ش� �������� �´� ������ ��������
	    List<MemberVO> members = memberService.memSelectAll(pVO);

	    Map<String, Object> result = new HashMap<String, Object>();
	    result.put("members", members);  // ȸ�� ���
	    result.put("pVO", pVO);          // ����¡ ����

	    return result;
	}


	//ȸ�� �Ѹ��� Ż��
	@PostMapping("adminpage/userDel")
	@ResponseBody
	public String userDel(@RequestParam("userid")String userid) {
		log.info(userid);
		memberService.delOneUser(userid);
		return "/mypage/rootpage";
	}
	//ȸ�� �ѹ��� Ż��
	@PostMapping("/adminpage/memDel")
	@ResponseBody
	public ModelAndView memDel(@RequestParam(value = "test[]", required = false) List<String> delList) {
	    ModelAndView mav = new ModelAndView();
	    if (delList != null && !delList.isEmpty()) {
	        log.info("������ ȸ�� ���: " + delList.toString());
	        // ���⿡ ���� ���� �߰�
	        memberService.delUsers(delList);
	    } else {
	        log.info("������ ȸ���� ���õ��� �ʾҽ��ϴ�.");
	    }

	    mav.setViewName("/adminpage/rootpage");
	    return mav;
	}
	//boardListù ����
	@GetMapping("/adminpage/boardList")
	public String boardList() {
		return "/adminpage/boardList";
	}
	//��������Ʈ����
	@GetMapping("/adminpage/listSelect")
	@ResponseBody
	public Map<String, Object> listSelect(HttpSession session,PagingVO pVO, @RequestParam("page") int page){
		pVO.setNowPage(page);  // ���� ������ ����
	    pVO.setTotalRecord(festivalService.totalfestival(pVO));  // �� ���ڵ� �� ����

	    // �� ������ �� ���
	    int totalPage = (int) Math.ceil((double) pVO.getTotalRecord() / pVO.getOnePageRecord());
	    pVO.setTotalPage(totalPage);

	    // ������ ����: SQL �������� �� ��° ���ڵ���� �������� ����
	    pVO.setOffset((pVO.getNowPage() - 1) * pVO.getOnePageRecord());
		List<FestivalVO>flist=festivalService.SelectAll(pVO);
	    Map<String, Object> result = new HashMap<String, Object>();
	    result.put("flist", flist);  // ȸ�� ���
	    result.put("pVO", pVO);          // ����¡ ����
		session.setAttribute("rootlist", "festival");
		return result;
	}
	//��������Ʈ�ϳ�������
	@GetMapping("/adminpage/listDel/{no}")
	public String listDel(@PathVariable("no")int no){
		festivalService.delOneList(no);
		return "/adminpage/boardList";
		
	}
	//��������Ʈ �ѹ��� ����
	@PostMapping("/adminpage/listDel")
	@ResponseBody
	public ModelAndView boardListDel(@RequestParam(value = "test[]", required = false) List<String> delList) {
	    ModelAndView mav = new ModelAndView();
	    if (delList != null && !delList.isEmpty()) {
	        log.info("������ �� ���: " + delList.toString());
	        // ���⿡ ���� ���� �߰�
	        festivalService.delLists(delList);
	    } else {
	        log.info("������ ���� ���õ��� �ʾҽ��ϴ�.");
	    }

	    mav.setViewName("/adminpage/boardList");
	    return mav;
	}
	//��������Ʈ����
	@GetMapping("/adminpage/restList")
	@ResponseBody
	public  Map<String, Object> restList(HttpSession session,PagingVO pVO, @RequestParam("page") int page){
		pVO.setNowPage(page);  // ���� ������ ����
	    pVO.setTotalRecord(restService.totalrest(pVO));  // �� ���ڵ� �� ����
	    // �� ������ �� ���
	    int totalPage = (int) Math.ceil((double) pVO.getTotalRecord() / pVO.getOnePageRecord());
	    pVO.setTotalPage(totalPage);
	    // ������ ����: SQL �������� �� ��° ���ڵ���� �������� ����
	    pVO.setOffset((pVO.getNowPage() - 1) * pVO.getOnePageRecord());

		List<RestVO> rlist=restService.restAllSelectpaging(pVO);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rlist", rlist);
		result.put("pVO", pVO);  
		session.setAttribute("rootlist", "rest");
		return result;
	}
	//���� �ϳ��� ����
	@GetMapping("/adminpage/rlistDel/{rest_code}")
	public String restDel(@PathVariable("rest_code")int rest_code) {
		restService.restDel(rest_code);
		return "/adminpage/boardList";
	}
//	���� �ѹ��� ����
	@PostMapping("/adminpage/rlistDel")
	public String rlistDel(@RequestParam(value="rest_code[]",required=false)List<String> delRlist) {
		if(delRlist!=null &&!delRlist.isEmpty()) {
			restService.delRlist(delRlist);
		}
		return "redirect:/adminpage/boardList";
	}
	//�ڽ� ����Ʈ����
	@GetMapping("/adminpage/courseList")
	@ResponseBody
	public Map<String, Object> courseList(HttpSession session,PagingVO pVO, @RequestParam("page") int page){
		session.setAttribute("rootlist", "course");
		pVO.setNowPage(page);  // ���� ������ ����
	    pVO.setTotalRecord(courseService.totalRecord(pVO));  // �� ���ڵ� �� ����

	    // �� ������ �� ���
	    int totalPage = (int) Math.ceil((double) pVO.getTotalRecord() / pVO.getOnePageRecord());
	    pVO.setTotalPage(totalPage);

	    // ������ ����: SQL �������� �� ��° ���ڵ���� �������� ����
	    pVO.setOffset((pVO.getNowPage() - 1) * pVO.getOnePageRecord());
		List<CourseVO> clist=courseService.courseSelectPaging(pVO);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("clist", clist);  // ȸ�� ���
		result.put("pVO", pVO);          // ����¡ ����

		return result;
	}
	//�ڽ� �ϳ��� ����
	@GetMapping("/adminpage/clistDel/{news_no}")
	public String clistDel(@PathVariable("news_no")int news_no) {
		courseService.cOneDel(news_no);
		
		return "redirect:/adminpage/boardList";
		
	}
	@PostMapping("/adminpage/clistDel")
	public String clistDel(@RequestParam(value="news_no[]",required=false) List<String> delClist){
		if(delClist!=null &&!delClist.isEmpty()) {
			courseService.delClist(delClist);
		}
		return "redirect:/adminpage/boardList";
	}
	@PostMapping("/adminpage/answerWrite")
	public ModelAndView answerWrite(AnswerVO vo) {
		ModelAndView mav = new ModelAndView();
		log.info(vo.toString());
		
		int result=mypageService.answerWrite(vo); 
		log.info(result+"");
		if(result==1) {
			mypageService.answerset(vo);
			mav.setViewName("redirect:/mypage/inquiryView/"+vo.getIndex());
		}
		return mav;
		
	}
	@PostMapping("adminpage/answerEdit")
	public ModelAndView answerEdit(AnswerVO vo) {
		ModelAndView mav = new ModelAndView();
		mypageService.editResult(vo);
		
			mav.addObject("vo", vo);
			mav.setViewName("redirect:/mypage/inquiryView/"+vo.getIndex());
		
		
		return mav;
	
	}
	@GetMapping("/adminpage/adminQnaList")
	public String adqnalist() {
		return "/adminpage/adminqnalist";
	}
	@PostMapping("/adminpage/qnaList")
	@ResponseBody
	public Map<String, Object> qnaList(PagingVO pVO,HttpSession session
										,@RequestParam(value = "userid") String userid,
										@RequestParam(value="page") int page){
		log.info(userid);
		log.info(page+"");
		pVO.setNowPage(page);  // ���� ������ ����
		log.info(pVO.toString());
	    pVO.setTotalRecord(pageService.totalAllinquiry(pVO));  // �� ���ڵ� �� ����
	    int totalPage = (int) Math.ceil((double) pVO.getTotalRecord() / pVO.getOnePageRecord());
	    pVO.setTotalPage(totalPage);
	    pVO.setOffset((pVO.getNowPage() - 1) * pVO.getOnePageRecord());
		List<InquiryVO> list= pageService.Allinquiry(pVO);
		log.info(list.toString());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("pVO", pVO);
		
		
		return result;
		
	}
	
}