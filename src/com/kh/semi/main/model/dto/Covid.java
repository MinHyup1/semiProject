package com.kh.semi.main.model.dto;

public class Covid {
	
	private int Num;
	private String days; //날짜
	private int decideCnt; //확진자 수
	
	public Covid() {
		// TODO Auto-generated constructor stub
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public int getDecideCnt() {
		return decideCnt;
	}

	public void setDecideCnt(int decideCnt) {
		this.decideCnt = decideCnt;
	}

	@Override
	public String toString() {
		return "Covid [Num=" + Num + ", days=" + days + ", decideCnt=" + decideCnt + "]";
	}
	
	
	
	
	
	
	
	

}
