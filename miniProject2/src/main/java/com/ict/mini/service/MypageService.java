package com.ict.mini.service;

import java.util.List;

import com.ict.mini.vo.AnswerVO;
import com.ict.mini.vo.FestivalVO;
import com.ict.mini.vo.InquiryVO;
import com.ict.mini.vo.PagingVO;
import com.ict.mini.vo.RestVO;

public interface MypageService {
	public void updateUserProfileImage(String userId, String fileName);
	
	 public List<FestivalVO> getLikedFestivals(String userid);
	 public List<RestVO> getLikedRestaurants(String userid);
	
	
	public List<InquiryVO> myinquiry(String userid,PagingVO pVO);
	public int totalmyinquiry(PagingVO pVO,String userid);
	public List<AnswerVO> answer(String userid);
	public int qnaWrite(InquiryVO vo);
	public InquiryVO selectoneqna(int index);
	public int answerWrite(AnswerVO vo);
	public AnswerVO selectAnswer(int index);
	public int editResult(AnswerVO vo);
	public void answerset(AnswerVO vo);
	
}
