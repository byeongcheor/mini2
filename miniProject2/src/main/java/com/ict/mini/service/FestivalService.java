package com.ict.mini.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ict.mini.vo.FestivalReviewVO;
import com.ict.mini.vo.FestivalVO;
import com.ict.mini.vo.PagingVO;

public interface FestivalService {
	// ��� ���� ����Ʈ�� �������� �޼���
	public List<FestivalVO> dataSelectAll(String sortBy);
	// ���ο� ���� ������ �߰��ϴ� �޼���
	public int dataInsert(FestivalVO vo);
	// Ư�� ���� ������ �������� �޼���
	public FestivalVO getFestivalById(int no);
	// ���� �޷�(��������O)
	public List<FestivalVO> calDataSelect(String date,String environment);
	// ���� �޷�(��������X)
	public List<FestivalVO> calDataSelect2(String date);
	// ������ ��������Ʈ
	public List<FestivalVO> festivalAddrSelect(String addrdetails);
	public List<FestivalVO> SelectAll(PagingVO pVO);
	//���� top4
	public List<FestivalVO> festivalTop4();
	// ��¥�� ���� ���� ����
    public List<FestivalVO> getOngoingFestivals();
    // ���ϱ� �� ������Ʈ
    public void incrementLikeCount(int no);
    public void decrementLikeCount(int no);
    // ��ȸ��
    public void incrementHitCount(int no);
	//09050505������
    //������ ���� ����
    public int delOneList(int no);
    //������ ���� �ѹ��� ����
    public void delLists(List<String> delList);
    public int totalfestival(PagingVO pVO);
    public List<FestivalVO> SelectAllnopaging();
    public List<FestivalVO> getLikedFestivals(String userid);
    

	public int getFestivalCount(PagingVO pVO);
	public List<FestivalVO> getPagedFestivalList(PagingVO pVO);

	 // ����ڰ� Ư�� ��������� ���ƿ並 �߰�
	    void addLike(@Param("no") int no, @Param("userid") String userid);

	    // ����ڰ� Ư�� ��������� ���ƿ並 ����
	    void removeLike(@Param("no") int no, @Param("userid") String userid);

	    // ����ڰ� Ư�� ��������� ���ƿ並 �������� Ȯ��
	    boolean checkIfUserLiked(@Param("no") int no, @Param("userid") String userid);

	    // Ư�� ��������� ���ƿ� �� ��ȸ
	    int getLikeCount(@Param("no") int no);

	    // ����ڰ� ���ƿ並 ���� ������� �ڵ� ����Ʈ ��ȸ
	    List<Integer> getUserLikedRestCodes(@Param("userid") String userid);
	    public int reviewInsert(FestivalReviewVO vo);
	    //��۸��
	    public List<FestivalReviewVO> reviewSelectList(int festival_no);
	    //��� ����
	    public int reviewUpdate(FestivalReviewVO vo);
	    //��� ����
	    public int reviewDelete (int review_no, String userid);

 	

	
	
}
