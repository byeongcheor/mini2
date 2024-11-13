package com.ict.mini.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ict.mini.service.RestService;
import com.ict.mini.vo.LikeVO;
import com.ict.mini.vo.MemberVO;
import com.ict.mini.vo.RestReviewVO;
import com.ict.mini.vo.RestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/rest")
public class RestController {
      @Autowired
      RestService service;
      
      ModelAndView mav = null;
      
      // ���� ����Ʈ ������ 
      @GetMapping("/restList")
      public String restList(Model model) {
         
         List<RestVO> food = service.getRestByCategory("�ѽ�");
         List<RestVO> Japanesefood = service.getRestByCategory("�Ͻ�");
         List<RestVO> westernstyle = service.getRestByCategory("�����");
         List<RestVO> cafe = service.getRestByCategory("ī��");
         List<RestVO> Chinesefood = service.getRestByCategory("�߽�");
         List<RestVO> Uniquefood = service.getRestByCategory("�̻�������");
         
         
         model.addAttribute("food",food);
         model.addAttribute("Japanesefood",Japanesefood);
         model.addAttribute("westernstyle",westernstyle);
         model.addAttribute("cafe",cafe);
         model.addAttribute("Chinesefood",Chinesefood);
         model.addAttribute("Uniquefood",Uniquefood);
         return "/board/rest/RestList";
      }
      private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

      @GetMapping("/restView/{rest_code}")
      public String getRestView(@PathVariable("rest_code") int rest_code, Model model) {
          try {
              // �Ĵ� ���� ��ȸ
              RestVO restView = service.getRestViewByCode(rest_code);
              LocalTime now = LocalTime.now();

              // �����ͺ��̽� opentime ����
              String opentimeString = restView.getOpentime();
              LocalTime opentime = null;
              LocalTime closeTime = null;
              LocalTime breakStartTime = null;
              LocalTime breakEndTime = null;
              LocalTime lastOrderTime = null;

              // �ð� ���� ó��
              String[] parts = opentimeString.split("<br>");
              for (String part : parts) {
                  part = part.trim();

                  // �����ð� ����
                  Matcher regularHoursMatcher = Pattern.compile("(\\d{2}:\\d{2})[-~](\\d{2}:\\d{2})").matcher(part);
                  if (regularHoursMatcher.find()) {
                      opentime = LocalTime.parse(regularHoursMatcher.group(1), TIME_FORMATTER);
                      closeTime = LocalTime.parse(regularHoursMatcher.group(2), TIME_FORMATTER);
                  }

                  // �극��ũ Ÿ�� ����
                  Matcher breakTimeMatcher = Pattern.compile("(�극��ũŸ��|���� �극��ũŸ��|�ָ� �극��ũŸ��) (\\d{2}:\\d{2})[-~](\\d{2}:\\d{2})").matcher(part);
                  if (breakTimeMatcher.find()) {
                      breakStartTime = LocalTime.parse(breakTimeMatcher.group(2), TIME_FORMATTER);
                      breakEndTime = LocalTime.parse(breakTimeMatcher.group(3), TIME_FORMATTER);
                  }

                  // ��Ʈ ���� ����
                  Matcher lastOrderMatcher = Pattern.compile("(��Ʈ����|��Ʈ ����) (\\d{2}:\\d{2})").matcher(part);
                  if (lastOrderMatcher.find()) {
                      lastOrderTime = LocalTime.parse(lastOrderMatcher.group(2), TIME_FORMATTER);
                  }
              }

              // �α� �߰�: ����� ��
              System.out.println("Current Time: " + now);
              System.out.println("Open Time: " + opentime);
              System.out.println("Close Time: " + closeTime);
              System.out.println("Break Start Time: " + breakStartTime);
              System.out.println("Break End Time: " + breakEndTime);
              System.out.println("Last Order Time: " + lastOrderTime);

              // ���� �ð� ���ϱ�
              boolean isWithinOperatingHours = opentime != null && closeTime != null &&
                      now.isAfter(opentime) && now.isBefore(closeTime);
              boolean isInBreakTime = breakStartTime != null && breakEndTime != null &&
                      now.isAfter(breakStartTime) && now.isBefore(breakEndTime);
              boolean isBeforeLastOrder = lastOrderTime == null || now.isBefore(lastOrderTime);

              // ���� ���� �� ���� ����
              boolean isOpen = isWithinOperatingHours && !isInBreakTime && isBeforeLastOrder;

              // �𵨿� ���� �߰�
              model.addAttribute("restView", restView);
              model.addAttribute("isOpen", isOpen ? "������" : "��������");

              // īī���� ����ϱ�
              List<RestVO> kakaomap = service.getKakaoMap(rest_code);
              String kakaomapJson = new ObjectMapper().writeValueAsString(kakaomap);
              model.addAttribute("kakaomapJson", kakaomapJson);

              // �ش� rest_code �� ī�װ��� ���� ����� ���� �����ֱ�
              List<RestVO> similarRestaurant = service.getSimilarRestaurant(rest_code);
              model.addAttribute("similarRestaurant", similarRestaurant);

              // ���� �� ��� ���� ��ȸ
              List<RestReviewVO> reviews = service.getReviewSelect(rest_code);
              model.addAttribute("reviews", reviews);

              Double averageRating = service.getAverageRating(rest_code);
              model.addAttribute("averageRating", averageRating);

              return "/board/rest/RestView";
          } catch (Exception e) {
              // ���� ó��
              e.printStackTrace();
              model.addAttribute("isOpen", "��������");
              return "/board/rest/RestView";
          }
      }
      
      // ���� ��� �����ֱ�
      @GetMapping("/restView/{rest_code}/reviewList")
      @ResponseBody
      public List<RestReviewVO> ReviewList(@PathVariable("rest_code") int rest_code, HttpSession session , Model model){
    	  	
    	  	// ���� ����� ��ȸ
    	    List<RestReviewVO> reviews = service.getReviewSelect(rest_code);

    	    // ���� ����� ��ȯ�մϴ�.
    	    return reviews;
      }
      
      // ���ƿ�
      @PostMapping("/restView/{rest_code}/Togglelikes")
      @ResponseBody
      public Map<String, Object> toggleLikes(@PathVariable("rest_code") int rest_code,
                                               @RequestParam("userid") String userid) {
          boolean currentStatus = service.checkIfUserLiked(rest_code, userid);

          if (currentStatus) {
              service.removeLike(rest_code, userid); // �̹� ���ƿ� ������ ��� ���ƿ� ���
          } else {
              service.addLike(rest_code, userid); // ���ƿ� �߰�
          }

          int updatedLikeCount = service.getLikeCount(rest_code); // ������Ʈ�� ���ƿ� �� ��������

          Map<String, Object> response = new HashMap();
          response.put("likes", !currentStatus); // ���� ���ƿ� ���� ��ȯ
          response.put("likeCount", updatedLikeCount); // �ֽ� ���ƿ� �� ��ȯ
          return response;
      }
      
      // Ư�� ������� ���ƿ� �ҷ�����(���ƿ� Ŭ�� ��)
      @GetMapping("/restView/{rest_code}/mylikes")
      @ResponseBody
      public Map<String, Object> getUserLikes(@PathVariable("rest_code") int rest_code, HttpSession session) {
          Map<String, Object> response = new HashMap();
          String userid = (String) session.getAttribute("logId");

          if (userid == null) {
              response.put("error", "�α����� �ʿ��� ����Դϴ�.");
              return response;
          }

          try {
              List<Integer> likedRestCodes = service.getUserLikedRestCodes(userid);
              response.put("likes", likedRestCodes.contains(rest_code) ? Collections.singletonList(rest_code) : Collections.emptyList());
          } catch (Exception e) {
              response.put("error", "���� �߻�.");
              e.printStackTrace();
          }

          return response;
      }
  
          
      // ���� ���
      @PostMapping("/restView/{rest_code}/ReviewOk")
      @ResponseBody
      public String submitRating(@PathVariable("rest_code") int rest_code, 
                                 @RequestParam("rating") int rating, 
                                 @RequestParam("contents") String contents,
                                 HttpSession session, Model model) {
          String userId = (String) session.getAttribute("logId");
          RestReviewVO restreviewVO = new RestReviewVO();
          restreviewVO.setRest_code(rest_code);
          restreviewVO.setRating(rating);
          restreviewVO.setContents(contents);
          restreviewVO.setUserid(userId);

          System.out.println("rest_code: " + rest_code);
          System.out.println("rating: " + rating);
          System.out.println("contents: " + contents);
          System.out.println("userId: " + userId);
          
          try {
              service.addReview(restreviewVO);
              return "1";
          } catch(Exception e) {
              e.printStackTrace();
              return "0"; // ���� ����
          }
      }
      
      // ���� ����
      @PostMapping("/restView/{rest_code}/edit")
      @ResponseBody
      public String reviewEdit(RestReviewVO vo, HttpSession session) {
          vo.setUserid((String) session.getAttribute("logId"));
          int result = 0;
          try {
              result = service.reviewEdit(vo);
              log.info(vo.toString());
              return String.valueOf(result); 
          } catch (Exception e) {
              e.printStackTrace();
              return "0"; // ���� �� "0" ��ȯ
          }
      }
      
      // ���� ����
      @GetMapping("/restView/{rest_code}/del")
      @ResponseBody
  	public String del(int review_no, HttpSession session) {
  		String userid = (String)session.getAttribute("logId");
  		
  		// ���� ���� ��������
  		RestReviewVO reviews = service.getReviewByNo(review_no);
  		
  		if(reviews.getUserid().equals(userid) || "root".equals(userid)) {
  				int result = service.reviewDel(review_no);
  				
  				if(result == 1 ) {
  		  			return "1"; // ���� ����
  		  		}else {
  		  			return "0"; // ���� ����
  		  		}
  		}else {
  				return "0"; // ���� ����
  		}
  	}
}
