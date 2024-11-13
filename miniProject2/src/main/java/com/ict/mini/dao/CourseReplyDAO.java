package com.ict.mini.dao;

import java.util.List;

import com.ict.mini.vo.CourseReplyVO;

public interface CourseReplyDAO {
	//��۵��
	public int replyInsert(CourseReplyVO vo);
	//��۸��
	public List<CourseReplyVO> replySelectList(int news_no);
	//��ۼ���
	public int replyUpdate(CourseReplyVO vo);
	//��ۻ���
	public int replyDelete(int reply_no, String userid);
}
