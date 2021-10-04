package com.kh.semi.notice.dto;

import java.sql.Timestamp;

public class Notice {

	private String email;
	private String noticeCode;
	private String noticeName;
	private Timestamp noticeTime;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNoticeCode() {
		return noticeCode;
	}
	
	public void setNoticeCode(String noticeCode) {
		this.noticeCode = noticeCode;
	}
	
	public String getNoticeName() {
		return noticeName;
	}
	
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	
	public Timestamp getNoticeTime() {
		return noticeTime;
	}
	
	public void setNoticeTime(Timestamp noticeTime) {
		this.noticeTime = noticeTime;
	}
	
	@Override
	public String toString() {
		return "Notice [email=" + email + ", noticeCode=" + noticeCode + ", noticeName=" + noticeName + ", noticeTime="
				+ noticeTime + "]";
	}
	
}
