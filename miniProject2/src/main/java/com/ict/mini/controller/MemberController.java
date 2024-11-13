package com.ict.mini.controller;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ict.mini.service.MemberService;
import com.ict.mini.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/mypage")
public class MemberController {

	@Inject
	MemberService service;
	//�α��� ������ �̵�
		@GetMapping("/login")
		public String login() {
			// /WEB-INF/views/member/login.jsp
			return "/mypage/login"; //�α��� �������� ��
		}
		//�α��� (DB��ȸ)
		@PostMapping("/loginOk")
		public String loginOk(MemberVO vo, HttpSession session) {//���̵�,��� request
			System.out.println(vo.toString());
			MemberVO resultVo = service.loginOk(vo);//���̵�� ����� ������ ��� , ������ null�� ���´�.
			//System.out.println(resultVo.toString());
			
			if(resultVo==null) {//�α��ν���-> �α�����
				return "/mypage/login";
				
			}else {//�α��� �����ϸ� -> ���� �α��� ������� -> Ȩ
				session.setAttribute("logId", resultVo.getUserid());
				session.setAttribute("logName", resultVo.getUsername());
				session.setAttribute("logStatus", "Y");
				
				return "/mypage/loginOk"; //redirect�� ���������� �̵����� �ʰ� ���ϴ� ��Ʈ�ѷ� �������� �ٷ� �̵��Ѵ�.
			}	
		}
		
	//�α׾ƿ�
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/";
		}
	//ȸ������
		@GetMapping("/join")
		public String join() {
			return "/mypage/join";
		}
		 //���̵� �ߺ��˻�
		@GetMapping("/idDoubleCheck")
		public ModelAndView idDoubleCheck(String userid) {
			
			//db ��ȸ
			int result = service.idDoubleCheck(userid);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("result", result);
			mav.addObject("userid", userid); //������������ �ʿ���
			mav.setViewName("mypage/idDoubleCheck");
		
			
			return mav; 
		}
		//ȸ������
		@PostMapping("/joinOk")
		public ModelAndView joinOk(MemberVO vo) {
			
			int result=0;
			//insert -> int 
			try {
				result = service.memberInsert(vo);
			}catch(Exception e){
				
			}
			ModelAndView mav = new ModelAndView();
			mav.addObject("result",result);
			mav.setViewName("mypage/joinResult");
			
			return mav;
		}
		//ȸ������ ������
		@GetMapping("/joinEdit")
		public ModelAndView joinEdit(HttpSession session) {
			//���ǿ� �ִ� �α��� ���̵� -> �ش���̵��� ������ DB���� Select�ϱ�
			String userid = (String)session.getAttribute("logId");
			
			MemberVO vo = service.memberSelect(userid);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("vo", vo);
			mav.setViewName("mypage/joinEdit");
			
			return mav;
			
		}
		
		//ȸ������ ����(DB update)
		@PostMapping("/joinEditOk")
		public String joinEditOk(MemberVO vo, HttpSession session) {
			vo.setUserid((String)session.getAttribute("logId"));
			
			service.memberUpdate(vo);
			
			return "redirect:joinEdit";
			
		}
		
		//ȸ��Ż��
	    @GetMapping("/unjoins")
	    public  ModelAndView unjoins(HttpSession session){
	        String userid = (String)session.getAttribute("logId");
	
	       service.unjoins(userid);
	        
	        ModelAndView mav = new ModelAndView();
	        //ȸ��Ż���� ���ǿ� �ִ� �α��� ������ ����� Ȩ���� �̵��Ѵ�.
	        mav.setViewName("redirect:logout");
	        return mav;
	    }
		
	 // ���̵� ã�� GET ��û ó��
	    @GetMapping("/findId")
	    public String getFindId(HttpSession session, Model model) {
	        String logId = (String) session.getAttribute("logId");
	        String logName = (String) session.getAttribute("logName");

	        if (logId != null && logName != null) {
	            model.addAttribute("logId", logId);
	            model.addAttribute("logName", logName);
	        }

	        // ���ǿ��� foundId�� �����Ͽ� ���� ���� �������� �ʵ��� �մϴ�.
	        session.removeAttribute("foundId");

	        return "mypage/findId";
	    }


	    // ���̵� ã�� POST ��û ó��
	    @PostMapping("/findId")
	    public String postFindId(@RequestParam("email") String email, @RequestParam("username") String username, HttpSession session, Model model) {
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("email", email);
	        params.put("username", username);
	        
	        MemberVO member = service.findMemberByEmailAndName(params);

	        if (member != null) {
	            // ���̵� ã�� ����
	            String foundId = member.getUserid();
	            session.setAttribute("foundId", foundId);
	            model.addAttribute("foundId", foundId);
	        } else {
	            // ���̵� ã�� ����
	            model.addAttribute("error", "�ش� �̸��ϰ� �̸����� ��ϵ� ���̵� ã�� �� �����ϴ�.");
	            session.removeAttribute("foundId");  // ���� �� ���ǿ��� foundId ����
	        }

	        return "mypage/findId";
	    }
	    
	   

	    // ��й�ȣ ã�� ���� �����ݴϴ�.
	    @GetMapping("/findPwd")
	    public String showFindPasswordForm(Model model) {
	        model.addAttribute("pwd", 0);  // ù ��° �ܰ�� ����
	        return "mypage/findPwd";
	    }

	    // ù ��° �ܰ�: ���̵� ���� �� ���� �ܰ�� �̵�
	    @PostMapping("/findPwdStep1")
	    public String findPwdStep1(@RequestParam("userid") String userid, Model model) {
	        boolean userExists = service.checkUserIdExists(userid);
	        if (!userExists) {
	            model.addAttribute("useridErrorMessage", "�ش� ���̵� �������� �ʽ��ϴ�.");
	            model.addAttribute("pwd", 0);  // ù ��° �ܰ�� ����
	            return "mypage/findPwd";
	        }
	        model.addAttribute("pwd", 1);  // �� ��° �ܰ�� �̵�
	        return "mypage/findPwd";
	    }

	    // �� ��° �ܰ�: �̸��� ��ȭ��ȣ ���� �� ���� �ܰ�� �̵�
	    @PostMapping("/findPwdStep2")
	    public String findPwdStep2(@RequestParam("username") String username, @RequestParam("tel") String tel, Model model) {
	        // Map�� username�� tel�� �߰�
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("username", username);
	        params.put("tel", tel);

	        // ���񽺿��� ����� ����
	        boolean validUser = service.checkUsernameAndTel(params);
	        String a = service.findUserid(params);
	        
	        // ���� ����� ���� ó��
	        if (!validUser) {
	            model.addAttribute("usernameErrorMessage", "�̸��� ��ġ���� �ʽ��ϴ�.");
	            model.addAttribute("telErrorMessage", "��ȭ��ȣ�� ��ġ���� �ʽ��ϴ�.");
	            model.addAttribute("pwd", 1);  // �� ��° �ܰ�� ����
	            return "mypage/findPwd";
	        }
	        model.addAttribute("id", a);
	        // ��ȿ�� ����ڸ� ��й�ȣ ���� �ܰ�� �̵�
	        model.addAttribute("pwd", 2);
	        return "mypage/findPwd";
	    }


	    // ��й�ȣ ����
	    @PostMapping("/changePwd")
	    public String changePwd(@RequestParam("userpwd") String userpwd, @RequestParam("userpwd2") String userpwd2,@RequestParam("userid") String userid, Model model) {
	    	
	        if (!userpwd.equals(userpwd2)) {
	            model.addAttribute("pwdErrorMessage", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
	            model.addAttribute("pwd2ErrorMessage", "��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.");
	            model.addAttribute("pwd", 2);  // ��й�ȣ ���� �ܰ�� ����
	            return "mypage/findPwd";
	        }
	        // ��й�ȣ ���� ����
	        
	        service.updatePassword(userpwd,userid);
	        
	        model.addAttribute("successMessage", "��й�ȣ�� ���������� ����Ǿ����ϴ�.");
	        return "mypage/findPwd";  // ��й�ȣ ���� �� �α��� �������� �̵�
	    }
		
}