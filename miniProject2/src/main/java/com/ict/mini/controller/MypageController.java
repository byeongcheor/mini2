package com.ict.mini.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.mini.service.CourseService;
import com.ict.mini.service.FestivalService;
import com.ict.mini.service.MemberService;
import com.ict.mini.service.MypageService;
import com.ict.mini.service.RestService;
import com.ict.mini.vo.AnswerVO;
import com.ict.mini.vo.CourseVO;
import com.ict.mini.vo.FestivalVO;
import com.ict.mini.vo.InquiryVO;
import com.ict.mini.vo.PagingVO;
import com.ict.mini.vo.RestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MypageController {
	@Inject
	MypageService service;
	@Autowired
	CourseService courseService;
    @Autowired
    FestivalService festivalService;
    @Autowired
    RestService restService;
	@Autowired
	MemberService memberService;
		
	@GetMapping("/mypage/mypage")
	public String myPage(HttpSession session, Model model) {
	    String username = (String) session.getAttribute("logName");
	    String userid = (String) session.getAttribute("logId");
	    String imgname = memberService.getUserimg(userid);
	    log.info(imgname);
	    // �⺻ �̹��� ��� ����
	    String defaultImg = "/user.png"; // �⺻ �̹��� ���ϸ��� ���

	    if (username != null) {
	        model.addAttribute("greeting", username + "��");
	        // ����� �̹����� �ִ� ���, �ش� �̹��� ��� ����
	        model.addAttribute("userimg", imgname != null ? imgname : defaultImg);
	    }

	    return "/mypage/mypage"; // ���������� ��� �̵�
	}
	
	
	   //���������� �̹��� ����
	 
	   
	   @PostMapping("/mypage/updateProfileImage")
	   public ResponseEntity<Map<String, String>> updateProfileImage(@RequestParam("userimgfile") MultipartFile file, Model model,HttpSession session,HttpServletRequest request) {
	       log.info("���� ���ε� ��û ����");
	       String UPLOAD_DIR = session.getServletContext().getRealPath("/resources/uploadfile/");
	       log.info(UPLOAD_DIR);
	       String webPath = request.getContextPath() + "/resources/uploadfile/";
	       log.info("�� ���: {}", webPath);  
	       model.addAttribute("webPath", webPath);
	       if (file.isEmpty()) {
	           log.warn("���ε�� ������ �������");
	           Map<String, String> response = new HashMap<String, String>();
	           response.put("error", "������ ����ֽ��ϴ�.");
	           return ResponseEntity.badRequest().body(response);
	       }

	       try {
	           // ���� �̸� ���� �� ���� ��� ����
	           String fileName = file.getOriginalFilename();
	           File targetFile = new File(UPLOAD_DIR + fileName);
	           log.info("���� ���� ���: {}", targetFile.getAbsolutePath());

	           // ���� ����
	           file.transferTo(targetFile);
	           log.info("������ ���������� �����: {}", fileName);

	           // ����� ���ǿ��� ����� ID ��������
	           String userId = (String) session.getAttribute("logId");
	           log.info("���ǿ��� ����� ID ��������: {}", userId);

	           if (userId != null) {
	               // ����� DB ������Ʈ
	               log.info("����� DB ������Ʈ ����");
	               service.updateUserProfileImage(userId, fileName);
	               log.info("����� DB ������Ʈ �Ϸ�");

	               // ���ǿ��� �̹��� URL ������Ʈ
	               String fileUrl = webPath + fileName;
	               session.setAttribute("imgname", fileUrl);
	               log.info("���� �̹��� URL ������Ʈ �Ϸ�: {}", fileUrl);

	               // ���������� ���ε�� ������ URL ��ȯ
	               Map<String, String> response = new HashMap<String, String>();
	               response.put("imgUrl", fileUrl);
	               log.info("test"+response);
	               return ResponseEntity.ok().body(response);
	           } else {
	               log.warn("���ǿ��� ����� ID�� ã�� �� ����");
	               Map<String, String> response = new HashMap<String, String>();
	               response.put("error", "����� ID�� ã�� �� �����ϴ�.");
	               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	           }

	       } catch (IOException e) {
	           log.error("���� ���ε� �� ���� �߻�", e);
	           Map<String, String> response = new HashMap<String, String>();
	           response.put("error", "���� ���ε� ����");
	           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	       }
	   }
	    

	    


// ���ƿ��� ��� ���̰�
    @GetMapping("/mypage/mypage_list_fav")
    public String getLikedItems(Model model, HttpSession session) {
    	log.info("ttttt");
        String userid = (String) session.getAttribute("logId");
        log.info("User ID: " + userid); // userid �� �α׷� Ȯ��
        if (userid != "") {
        	log.info(userid);
            List<FestivalVO> likedFestivals = festivalService.getLikedFestivals(userid);
            List<RestVO> likedRestaurants = restService.getLikedRestaurants(userid);
            log.info(likedFestivals.toString());
            log.info(likedRestaurants.toString());
            
            model.addAttribute("likedFestivals", likedFestivals);
            model.addAttribute("likedRestaurants", likedRestaurants);
        }
        return "mypage/mypage_list_fav"; // JSP ������ ���
    }
	

	//���������� �ڱⰡ �� �ڽ���
	@RequestMapping(value = "/mypage/mypage_list_cs2", method = RequestMethod.GET)
	public String getMyCourses(HttpSession session, Model model) {
		   log.info("test");
	    String userid = (String) session.getAttribute("logId");
	    if (userid == null) {
	        // �α������� ���� ��� �Ǵ� ���ǿ� `logId`�� ���� ���
	        return "redirect:/login"; // �α��� �������� ���𷺼��ϰų� ������ ó���� �մϴ�.
	    }
	    List<CourseVO> course = courseService.getUserCourses(userid);
	    log.info(course.toString());
	    model.addAttribute("course", course);
	    return "/mypage/mypage_list_cs";  // �� ������ �̸�
	}
	
	
	
	
		
	@GetMapping("/mypage/qnaList")
	public String qna(){
		
		return "/mypage/qnaList";
	}
	@PostMapping("/mypage/qnaList")
	@ResponseBody
	public Map<String, Object> qnaList(PagingVO pVO,HttpSession session
										,@RequestParam(value = "userid") String userid,
										@RequestParam(value="page") int page){
		log.info(userid);
		log.info(page+"");
		pVO.setNowPage(page);  // ���� ������ ����
		log.info(pVO.toString());
	    pVO.setTotalRecord(service.totalmyinquiry(pVO,userid));  // �� ���ڵ� �� ����
	    int totalPage = (int) Math.ceil((double) pVO.getTotalRecord() / pVO.getOnePageRecord());
	    pVO.setTotalPage(totalPage);
	    pVO.setOffset((pVO.getNowPage() - 1) * pVO.getOnePageRecord());
		List<InquiryVO> list= service.myinquiry(userid,pVO);
		log.info(list.toString());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("pVO", pVO);
		
		return result;
		
	}
	@GetMapping("/mypage/inquiryWrite")
	public String inquiryWrite() {
		return "/mypage/inquiryWrite";
	}
	
	@PostMapping("/mypage/write")
	public String write(InquiryVO vo,HttpSession session){
		log.info(vo.toString());
		String userid=(String)session.getAttribute("logId");
		vo.setUserid(userid);
		int writeOk= service.qnaWrite(vo);
		if(writeOk==1) {
			return "redirect:/mypage/qnaList";
		}else {
			return "/mypage/Writefalse"; 
		}
		
	}
	@GetMapping("/mypage/inquiryView/{index}")
	public ModelAndView qnaView(@PathVariable("index")int index,HttpServletRequest request,InquiryVO vo) {
		ModelAndView mav = new ModelAndView();
		 request.setAttribute("excludeHeader", true);
		vo=service.selectoneqna(index);
		log.info("asdfa"+vo.toString());
		
		AnswerVO avo= service.selectAnswer(index);
	
		if(avo!=null) {
			mav.addObject("avo", avo);
			log.info("nullte11st"+avo.toString());
		}
		
		mav.addObject("vo",vo);
		mav.setViewName("/mypage/qnaView");
		return mav;
		
	}
	
}