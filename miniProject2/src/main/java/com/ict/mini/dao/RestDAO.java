package com.ict.mini.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ict.mini.vo.LikeVO;
import com.ict.mini.vo.PagingVO;
import com.ict.mini.vo.RestReviewVO;
import com.ict.mini.vo.RestVO;

public interface RestDAO {

	// ���� ����Ʈ ������
	public List<RestVO> getRestByCategory(String category);
	
	// �󼼺��� ������
	public RestVO getRestViewByCode(int rest_code);
	
	//���� ��ü���
	public List<RestVO> restAllSelect();
	//���� ���������
	public List<RestVO> restAddrSelect(String addrdetails);
	//����TOP3
	public List<RestVO> restTop3();
	
	// īī���� ���� ��ġ ���
	List<RestVO> getKakaoMap(int rest_code);
	
	// ����� ���� ���
	List<RestVO> getSimilarRestaurant(int rest_code);
	
	// ���� ��� �����ֱ�
	List<RestReviewVO> getReviewSelect(int rest_code);
	
	// ����ڰ� Ư�� ��������� ���ƿ並 �߰�
    void addLike(@Param("rest_code") int rest_code, @Param("userid") String userid);

    // ����ڰ� Ư�� ��������� ���ƿ並 ����
    void removeLike(@Param("rest_code") int rest_code, @Param("userid") String userid);

    // ����ڰ� Ư�� ��������� ���ƿ並 �������� Ȯ��
    boolean checkIfUserLiked(@Param("rest_code") int rest_code, @Param("userid") String userid);


    // Ư�� ��������� ���ƿ� �� ��ȸ
    int getLikeCount(@Param("rest_code") int rest_code);

    // ����ڰ� ���ƿ並 ���� ������� �ڵ� ����Ʈ ��ȸ
    List<Integer> getUserLikedRestCodes(@Param("userid") String userid);
    
    void incrementLikeCount(@Param("rest_code") int rest_code);
    void decrementLikeCount(@Param("rest_code") int rest_code);
    // ���� ����ϱ�
    public int addReview(RestReviewVO restreviewVO);
    
    // ���� �����ϱ�
    public int reviewEdit(RestReviewVO vo);
    
    // ���� �����ϱ�
    public int reviewDel(int review_no);
    
    // ���� ��� �ҷ����� 2
    RestReviewVO getReviewByNo(int review_no);
    
    // ���� ��հ� ���ϱ�
    Double getAverageRating(int rest_code);

    
	// festival �����԰�
	public List<RestVO> getPopularRestaurants();
	
	//�����԰�
	 public List<RestVO> getLikedRestaurants(String userid);
	//�����ڰ� �����ϳ��� ����
    public void restDel(int rest_code);
    //�����ڰ� ���� �ѹ��� ����
    public void delRlist(List<String> delRlist);
    //���� �Ѱ��Լ�
    public int totalrest(PagingVO pVO);
    public List<RestVO> restAllSelectpaging(PagingVO pVO);
}


	
