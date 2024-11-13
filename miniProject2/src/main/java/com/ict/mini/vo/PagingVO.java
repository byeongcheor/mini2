package com.ict.mini.vo;

public class PagingVO {
	private int nowPage = 1; //���������� -> ��������ȣ�� ������ ������ 1�������� �ȴ�.
	private int onePageRecord = 12; //�ѹ��� ǥ���� ���ڵ� �� : limit�� ��
	private int offset; //�������� �ش��ϴ� ���ڵ� ������ �� ������ġ
	private int totalRecord; //�� ���ڵ�� DB���� count()�Լ�
	private int totalPage; //�� �������� = �� ���ڵ���� �������� ����� ���ڵ�� ���
	private int onePageNum = 5; // �ѹ��� ǥ���� ��������
	private int startPageNum = 1; //��������ȣ�� ���������� ��ȣ
	private String searchKey;
	private String searchWord;
	private String sort;
	private String schedule;
	
	private int page;
	private int size =10;
	private int totalFestival;
	private int totalFood;
	private int totalCourse;
	private int offset2;

	
	public int getOffset2() {
		offset2 = (page-1)*size;
		return offset2;
	}
	public void setOffset2(int offset2) {
		this.offset2 = offset2;
	}
	
	@Override
	public String toString() {
		return "PagingVO [nowPage=" + nowPage + ", onePageRecord=" + onePageRecord + ", offset=" + offset
				+ ", totalRecord=" + totalRecord + ", totalPage=" + totalPage + ", onePageNum=" + onePageNum
				+ ", startPageNum=" + startPageNum + ", searchKey=" + searchKey + ", searchWord=" + searchWord
				+ ", sort=" + sort + ", schedule=" + schedule + ", page=" + page + ", size=" + size + ", totalFestival="
				+ totalFestival + ", totalFood=" + totalFood + ", offset2=" + offset2 + "]";
	}
	public int getTotalFestival() {
		return totalFestival;
	}
	public void setTotalFestival(int totalFestival) {
		this.totalFestival = totalFestival;
	}
	public int getTotalFood() {
		return totalFood;
	}
	public void setTotalFood(int totalFood) {
		this.totalFood = totalFood;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
		//��������ȣ�� ���۰��� ���
		// ((����������-1)/�ѹ��� ǥ���� ��������)*�ѹ��� ǥ���� �������� + 1
		startPageNum = (nowPage-1)/onePageNum*onePageNum + 1; //1,6,11,16...
	}
	public int getOnePageRecord() {
		return onePageRecord;
	}
	public void setOnePageRecord(int onePageRecord) {
		this.onePageRecord = onePageRecord;
	}
	public int getOffset() {
		//���ڵ弱�� ������ġ ����
		offset = (nowPage-1)*onePageRecord;
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public int getOnePageNum() {
		return onePageNum;
	}
	public void setOnePageNum(int onePageNum) {
		this.onePageNum = onePageNum;
	}
	public int getStartPageNum() {
		return startPageNum;
	}
	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		//�� �������� -> totalPage
		totalPage = totalRecord / onePageRecord;
		if(totalRecord%onePageRecord > 0) {
		//5�� ����� �ƴϸ� 1page�߰�	
			totalPage++;
		}
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public int getTotalCourse() {
		return totalCourse;
	}
	public void setTotalCourse(int totalCourse) {
		this.totalCourse = totalCourse;
	}

}