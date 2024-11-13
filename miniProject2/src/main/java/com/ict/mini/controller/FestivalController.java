package com.ict.mini.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ict.mini.service.FestivalService;
import com.ict.mini.service.RestService;
import com.ict.mini.vo.FestivalReviewVO;
import com.ict.mini.vo.FestivalVO;
import com.ict.mini.vo.PagingVO;
import com.ict.mini.vo.RestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/festival")
public class FestivalController {
	@Inject
	FestivalService service;
	
	@Inject
    RestService restService;
	
	@GetMapping("/festivalList")
	public ModelAndView dataList(
	    @RequestParam(value = "sortBy", defaultValue = "hit") String sortBy,
	    @RequestParam(value = "page", defaultValue = "1") int page,  // ���� ������ ��ȣ
	    PagingVO pVO
	) {
	    ModelAndView mav = new ModelAndView();

	    // �������� ���� ���� ����
	    pVO.setSort(sortBy);
	    pVO.setNowPage(page);  // ���� ������ ��ȣ ����

	    // �� �������� ������ ���ڵ� �� ����
	    int pageSize = pVO.getOnePageRecord(); 
	    pVO.setOffset((page - 1) * pageSize);  // offset ���

	    // �� ������ ���� ����
	    pVO.setTotalRecord(service.getFestivalCount(pVO));
	    log.info(pVO.toString());  // PagingVO Ȯ��

	    // ����¡�� ����Ʈ ��������
	    List<FestivalVO> festivalList = service.getPagedFestivalList(pVO);

	    // ������ ���� ���� �� �����͸� ��� ����
	    mav.addObject("festivalList", festivalList);
	    mav.addObject("sortBy", sortBy); // ���� ���� ����
	    mav.addObject("pVO", pVO); // ����¡ ���� ����

	    // �� ����
	    mav.setViewName("/board/festival/festivalList");
	    return mav;
	}

	
	
	@GetMapping("/festivalView/{no}")
    public String viewFestival(@PathVariable("no") int no, Model model) {
		// ��ȸ�� ����
	    service.incrementHitCount(no);
        // no ���� �̿��� DB���� �ش� ������ �� ������ ��ȸ
        FestivalVO festival = service.getFestivalById(no);
        
        if (festival == null) {
            // ���� null�̸� ���� ó���� �⺻ �������� �����̷�Ʈ ���� ó���� �� �ֽ��ϴ�.
            System.out.println("���� " + no);
            return "redirect:/board/festival/festivalList"; // �⺻ ����Ʈ �������� �����̷�Ʈ
        }

        // �α� �ִ� ������ 4�� ��ȸ
        List<RestVO> popularRestaurants = restService.getPopularRestaurants();
        model.addAttribute("popularRestaurants", popularRestaurants);
        
        // ���� ���� �𵨿� �߰�
        model.addAttribute("festival", festival);
        
        // ���� ���� ���� ��� ��ȸ
        List<FestivalVO> ongoingFestivals = service.getOngoingFestivals(); // �������� ���� ��� ��ȸ
        model.addAttribute("ongoingFestivals", ongoingFestivals);
        
        return "/board/festival/festivalView"; 
 
	}
	// ���ƿ� ���
    @PostMapping("/festivalView/{no}/Togglelikes")
    @ResponseBody
    public Map<String, Object> toggleLikes(@PathVariable("no") int no,
                                            @RequestParam("userid") String userid) {
        boolean currentStatus = service.checkIfUserLiked(no, userid);

        if (currentStatus) {
            service.removeLike(no, userid);
        } else {
            service.addLike(no, userid);
        }

        int updatedLikeCount = service.getLikeCount(no);

        Map<String, Object> response = new HashMap();
        response.put("likes", !currentStatus);
        response.put("likeCount", updatedLikeCount);
        return response;
    }

    // Ư�� ������� ���ƿ� ���� ��ȸ
    @GetMapping("/festivalView/{no}/mylikes")
    @ResponseBody
    public Map<String, Object> getUserLikes(@PathVariable("no") int no, HttpSession session) {
        Map<String, Object> response = new HashMap();
        String userid = (String) session.getAttribute("logId");

        if (userid == null) {
            response.put("error", "�α����� �ʿ��� ����Դϴ�.");
            return response;
        }

        try {
            List<Integer> likedRestCodes = service.getUserLikedRestCodes(userid);
            response.put("likes", likedRestCodes.contains(no) ? Collections.singletonList(no) : Collections.emptyList());
        } catch (Exception e) {
            response.put("error", "���� �߻�.");
            e.printStackTrace();
        }

        return response;
    }
 // ���� �ۼ�
    @PostMapping("/festivalView/review/write")
    public String writeOk(FestivalReviewVO vo, HttpSession session){
      vo.setUserid((String)session.getAttribute("logId"));
      log.info(vo.toString());

      int result = 0;
      try {
         result = service.reviewInsert(vo);
      }catch(Exception e) {
         result = 0;
      }
      return result+"";
   }
    //��۸�� ����
    @GetMapping("/festivalView/{festival_no}/festivalList") 
    @ResponseBody
    public List<FestivalReviewVO> list(@PathVariable("festival_no") Integer festival_no) { 
       if (festival_no == null) {
              throw new IllegalArgumentException("Festival number is required.");
          }
       return service.reviewSelectList(festival_no);
    } 
    //��ۼ���(DB)
    @PostMapping("/festivalView/{festival_no}/edit") 
    public String edit(FestivalReviewVO vo, HttpSession session) {
          vo.setUserid((String) session.getAttribute("logId"));
          
          // ���� ���� ���θ� ���ڿ��� ��ȯ
          return String.valueOf(service.reviewUpdate(vo));
      }
    //��ۻ���
    @PostMapping("/festivalView/{festival_no}/delete") 
    public String del(@RequestParam(required = false) Integer review_no, HttpSession session) { 
       if (review_no == null) {
              throw new IllegalArgumentException("���� ��ȣ�� �����ϴ�.");
          }
      String userid = (String)session.getAttribute("logId");
       return service.reviewDelete(review_no, userid)+""; }

    
}


