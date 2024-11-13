package com.ict.mini.service;

import java.util.List;
import java.util.Map;


import com.ict.mini.vo.MemberVO;
import com.ict.mini.vo.PagingVO;

public interface MemberService {
	public MemberVO loginOk(MemberVO vo);
	public int idDoubleCheck(String userid);
	public int memberInsert(MemberVO vo);
	public MemberVO memberSelect(String userid);
	public int memberUpdate(MemberVO vo);
	public int unjoins(String userid); 
	public  MemberVO findMemberByEmailAndName(Map<String, Object> params);
	
	public boolean checkUserIdExists(String userid);  // ����� ID�� �����ϴ��� Ȯ��
	public boolean checkUsernameAndTel(Map<String, Object> params);
	public int updatePassword(String userpwd, String userid);  // ��й�ȣ ������Ʈ
	public String findUserid(Map<String, Object> params);
	public List<MemberVO> memSelectAll(PagingVO pVO);
	public int delOneUser(String userid);
	public int delUsers(List<String> delList);
	// ��ȸ���� ���ϱ�
	public int totalmem(PagingVO pVO);
	public String getUserimg(String userid);
   
}