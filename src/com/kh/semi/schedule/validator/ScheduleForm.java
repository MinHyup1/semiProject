package com.kh.semi.schedule.validator;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class ScheduleForm {

	private Date scheduleDate;
	private String hospital;
	private String pharmacy;
	private String[] medicine;
	private Date doseStart;
	private Date doseEnd;
	private int doseTimes;
	private Date[] visitNotice;
	private String[] medicineNoticeTime;
	private HttpServletRequest request;
	
	public ScheduleForm(HttpServletRequest request) {
		
		
	}
	
	public boolean test() {
		return true;
	}
	
}
