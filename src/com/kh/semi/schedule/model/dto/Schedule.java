package com.kh.semi.schedule.model.dto;

import java.sql.Date;

public class Schedule {

	private String scheduleId;
	private String userCode;
	private String hasMedicalRecord;
	private String hasPrescription;
	private String hasVisitNotice;
	private Date regDate;
	
	public Schedule() {
		// TODO Auto-generated constructor stub
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getHasMedicalRecord() {
		return hasMedicalRecord;
	}

	public void setHasMedicalRecord(String hasMedicalRecord) {
		this.hasMedicalRecord = hasMedicalRecord;
	}

	public String getHasPrescription() {
		return hasPrescription;
	}

	public void setHasPrescription(String hasPrescription) {
		this.hasPrescription = hasPrescription;
	}

	public String getHasVisitNotice() {
		return hasVisitNotice;
	}

	public void setHasVisitNotice(String hasVisitNotice) {
		this.hasVisitNotice = hasVisitNotice;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", userCode=" + userCode + ", hasMedicalRecord="
				+ hasMedicalRecord + ", hasPrescription=" + hasPrescription + ", hasVisitNotice=" + hasVisitNotice
				+ ", regDate=" + regDate + "]";
	}
	
}
