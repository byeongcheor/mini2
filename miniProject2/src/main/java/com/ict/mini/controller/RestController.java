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
      
      // 음식 리스트 페이지 
      @GetMapping("/restList")
      public String restList(Model model) {
         
         List<RestVO> food = service.getRestByCategory("한식");
         List<RestVO> Japanesefood = service.getRestByCategory("일식");
         List<RestVO> westernstyle = service.getRestByCategory("서양식");
         List<RestVO> cafe = service.getRestByCategory("카페");
         List<RestVO> Chinesefood = service.getRestByCategory("중식");
         List<RestVO> Uniquefood = service.getRestByCategory("이색음식점");
         
         
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
              // 식당 정보 조회
              RestVO restView = service.getRestViewByCode(rest_code);
              LocalTime now = LocalTime.now();

              // 데이터베이스 opentime 추출
              String opentimeString = restView.getOpentime();
              LocalTime opentime = null;
              LocalTime closeTime = null;
              LocalTime breakStartTime = null;
              LocalTime breakEndTime = null;
              LocalTime lastOrderTime = null;

              // 시간 정보 처리
              String[] parts = opentimeString.split("<br>");
              for (String part : parts) {
                  part = part.trim();

                  // 영업시간 패턴
                  Matcher regularHoursMatcher = Pattern.compile("(\\d{2}:\\d{2})[-~](\\d{2}:\\d{2})").matcher(part);
                  if (regularHoursMatcher.find()) {
                      opentime = LocalTime.parse(regularHoursMatcher.group(1), TIME_FORMATTER);
                      closeTime = LocalTime.parse(regularHoursMatcher.group(2), TIME_FORMATTER);
                  }

                  // 브레이크 타임 패턴
                  Matcher breakTimeMatcher = Pattern.compile("(브레이크타임|평일 브레이크타임|주말 브레이크타임) (\\d{2}:\\d{2})[-~](\\d{2}:\\d{2})").matcher(part);
                  if (breakTimeMatcher.find()) {
                      breakStartTime = LocalTime.parse(breakTimeMatcher.group(2), TIME_FORMATTER);
                      breakEndTime = LocalTime.parse(breakTimeMatcher.group(3), TIME_FORMATTER);
                  }

                  // 라스트 오더 패턴
                  Matcher lastOrderMatcher = Pattern.compile("(라스트오더|라스트 오더) (\\d{2}:\\d{2})").matcher(part);
                  if (lastOrderMatcher.find()) {
                      lastOrderTime = LocalTime.parse(lastOrderMatcher.group(2), TIME_FORMATTER);
                  }
              }

              // 로그 추가: 디버깅 용
              System.out.println("Current Time: " + now);
              System.out.println("Open Time: " + opentime);
              System.out.println("Close Time: " + closeTime);
              System.out.println("Break Start Time: " + breakStartTime);
              System.out.println("Break End Time: " + breakEndTime);
              System.out.println("Last Order Time: " + lastOrderTime);

              // 현재 시간 구하기
              boolean isWithinOperatingHours = opentime != null && closeTime != null &&
                      now.isAfter(opentime) && now.isBefore(closeTime);
              boolean isInBreakTime = breakStartTime != null && breakEndTime != null &&
                      now.isAfter(breakStartTime) && now.isBefore(breakEndTime);
              boolean isBeforeLastOrder = lastOrderTime == null || now.isBefore(lastOrderTime);

              // 최종 영업 중 여부 결정
              boolean isOpen = isWithinOperatingHours && !isInBreakTime && isBeforeLastOrder;

              // 모델에 정보 추가
              model.addAttribute("restView", restView);
              model.addAttribute("isOpen", isOpen ? "영업중" : "영업종료");

              // 카카오맵 사용하기
              List<RestVO> kakaomap = service.getKakaoMap(rest_code);
              String kakaomapJson = new ObjectMapper().writeValueAsString(kakaomap);
              model.addAttribute("kakaomapJson", kakaomapJson);

              // 해당 rest_code 와 카테고리가 같은 비슷한 맛집 보여주기
              List<RestVO> similarRestaurant = service.getSimilarRestaurant(rest_code);
              model.addAttribute("similarRestaurant", similarRestaurant);

              // 리뷰 및 평균 평점 조회
              List<RestReviewVO> reviews = service.getReviewSelect(rest_code);
              model.addAttribute("reviews", reviews);

              Double averageRating = service.getAverageRating(rest_code);
              model.addAttribute("averageRating", averageRating);

              return "/board/rest/RestView";
          } catch (Exception e) {
              // 예외 처리
              e.printStackTrace();
              model.addAttribute("isOpen", "영업종료");
              return "/board/rest/RestView";
          }
      }
      
      // 리뷰 목록 보여주기
      @GetMapping("/restView/{rest_code}/reviewList")
      @ResponseBody
      public List<RestReviewVO> ReviewList(@PathVariable("rest_code") int rest_code, HttpSession session , Model model){
    	  	
    	  	// 리뷰 목록을 조회
    	    List<RestReviewVO> reviews = service.getReviewSelect(rest_code);

    	    // 리뷰 목록을 반환합니다.
    	    return reviews;
      }
      
      // 좋아요
      @PostMapping("/restView/{rest_code}/Togglelikes")
      @ResponseBody
      public Map<String, Object> toggleLikes(@PathVariable("rest_code") int rest_code,
                                               @RequestParam("userid") String userid) {
          boolean currentStatus = service.checkIfUserLiked(rest_code, userid);

          if (currentStatus) {
              service.removeLike(rest_code, userid); // 이미 좋아요 상태일 경우 좋아요 취소
          } else {
              service.addLike(rest_code, userid); // 좋아요 추가
          }

          int updatedLikeCount = service.getLikeCount(rest_code); // 업데이트된 좋아요 수 가져오기

          Map<String, Object> response = new HashMap();
          response.put("likes", !currentStatus); // 현재 좋아요 상태 반환
          response.put("likeCount", updatedLikeCount); // 최신 좋아요 수 반환
          return response;
      }
      
      // 특정 사용자의 좋아요 불러오기(좋아요 클릭 시)
      @GetMapping("/restView/{rest_code}/mylikes")
      @ResponseBody
      public Map<String, Object> getUserLikes(@PathVariable("rest_code") int rest_code, HttpSession session) {
          Map<String, Object> response = new HashMap();
          String userid = (String) session.getAttribute("logId");

          if (userid == null) {
              response.put("error", "로그인이 필요한 기능입니다.");
              return response;
          }

          try {
              List<Integer> likedRestCodes = service.getUserLikedRestCodes(userid);
              response.put("likes", likedRestCodes.contains(rest_code) ? Collections.singletonList(rest_code) : Collections.emptyList());
          } catch (Exception e) {
              response.put("error", "오류 발생.");
              e.printStackTrace();
          }

          return response;
      }
  
          
      // 리뷰 등록
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
              return "0"; // 실패 응답
          }
      }
      
      // 리뷰 수정
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
              return "0"; // 실패 시 "0" 반환
          }
      }
      
      // 리뷰 삭제
      @GetMapping("/restView/{rest_code}/del")
      @ResponseBody
  	public String del(int review_no, HttpSession session) {
  		String userid = (String)session.getAttribute("logId");
  		
  		// 리뷰 정보 가져오기
  		RestReviewVO reviews = service.getReviewByNo(review_no);
  		
  		if(reviews.getUserid().equals(userid) || "root".equals(userid)) {
  				int result = service.reviewDel(review_no);
  				
  				if(result == 1 ) {
  		  			return "1"; // 삭제 성공
  		  		}else {
  		  			return "0"; // 삭제 실패
  		  		}
  		}else {
  				return "0"; // 권한 없음
  		}
  	}
}
