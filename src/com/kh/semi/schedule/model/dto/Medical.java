package com.kh.semi.schedule.model.dto;

import java.sql.Date;

public class Medical {

	private String historyId;
	private String scheduleId;
	private Date scheduleDate;
	private String scheduleName;
	private String hospCode;
	private Date regDate;
	
	public Medical() {
		// TODO Auto-generated constructor stub
	}

	public String getHistoryId() {
		return historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public String getHospCode() {
		return hospCode;
	}

	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Medical [historyId=" + historyId + ", scheduleId=" + scheduleId + ", scheduleDate=" + scheduleDate
				+ ", scheduleName=" + scheduleName + ", hospCode=" + hospCode + ", regDate=" + regDate + "]";
	}

}
