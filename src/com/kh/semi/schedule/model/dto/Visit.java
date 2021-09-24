package com.kh.semi.schedule.model.dto;

import java.sql.Date;

public class Visit {

	private String visitNoticeCode;
	private String scheduleId;
	private String noticeName;
	private String hospCode;
	private Date noticeDate;
	private String isNoticed;
	private Date regDate;
	
	public Visit() {
		// TODO Auto-generated constructor stub
	}

	public String getVisitNoticeCode() {
		return visitNoticeCode;
	}

	public void setVisitNoticeCode(String visitNoticeCode) {
		this.visitNoticeCode = visitNoticeCode;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getNoticeName() {
		return noticeName;
	}

	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}

	public String getHospCode() {
		return hospCode;
	}

	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getIsNoticed() {
		return isNoticed;
	}

	public void setIsNoticed(String isNoticed) {
		this.isNoticed = isNoticed;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Visit [visitNoticeCode=" + visitNoticeCode + ", scheduleId=" + scheduleId + ", noticeName=" + noticeName
				+ ", hospCode=" + hospCode + ", noticeDate=" + noticeDate + ", isNoticed=" + isNoticed + ", regDate="
				+ regDate + "]";
	}
	
}
