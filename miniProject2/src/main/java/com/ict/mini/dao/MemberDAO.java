package com.ict.mini.dao;

import java.util.List;
import java.util.Map;

import com.ict.mini.vo.MemberVO;
import com.ict.mini.vo.PagingVO;

public interface MemberDAO {
	//�α���
		public MemberVO loginOk(MemberVO vo); 
	//���̵� �ߺ��˻�
		public int idDoubleCheck(String userid);
		//ȸ�����
		public int memberInsert(MemberVO vo);
		//ȸ������
		public MemberVO memberSelect(String userid);
		//ȸ����������(DB)
		public int memberUpdate(MemberVO vo);
	
		//ȸ��Ż��
		public int unjoins(String userid ); 
			 	
		//���̵�ã��
		public MemberVO findMemberByEmailAndName(Map<String, Object> params);
		
		// ����� ID�� �����ϴ��� Ȯ��
		public boolean checkUserIdExists(String userid);  
		
		// ����� �̸��� ��ȭ��ȣ ����
		public boolean checkUsernameAndTel(Map<String, Object> params);
		
		 // ��й�ȣ ������Ʈ
		public int updatePassword(String userpwd, String userid);   
		public String findUserid(Map<String, Object> params);
		// ȸ����������
		public List<MemberVO> memSelectAll(PagingVO pVO);
		// ��ȸ���� ���ϱ�
		public int totalmem(PagingVO pVO);
		// �����ڰ� ���� �Ѹ��� Ż���Ű��
		public int delOneUser(String userid);
		
		// �����ڰ� �����ѹ��� ������ Ż���Ű��
		
		public int delUsers(List<String> delList);
		public String getUserimg(String userid);
		
		
		
}