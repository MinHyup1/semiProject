package com.kh.semi.schedule.validator;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.kh.semi.hospitalInfo.model.service.SearchHospitalService;

public class VisitNoticeForm {

	private String scheduleDate;
	private String hospital;
	private String hospCode;
	private String noticeTime;
	private HttpServletRequest request;
	private SearchHospitalService hospitalService = new SearchHospitalService();
	private Map<String, String> failedAttribute = new HashMap<String, String>();
	
	public VisitNoticeForm(HttpServletRequest request) {
		this.request = request;
		this.scheduleDate = request.getParameter("schedule_date");
		this.hospital = request.getParameter("hospital");
		this.hospCode = request.getParameter("hospCode");
		this.noticeTime = request.getParameter("visit_notice_time");
	}
	
	public boolean testHasValue() {
		if(hospital.length() != 0) {return true;}
		if(noticeTime.length() != 0) {return true;}
		return false;
	}
	
	public boolean standardDateTest() {
		boolean res = true;
		String dateReg = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
		
		//scheduleDate가 정규식 표현에 맞는지
		res = Pattern.matches(dateReg, scheduleDate);
		return res;
	}
	
	public boolean test() {
		boolean res = true;
		boolean valid = true;
		
		String timeReg = "^([0-1]\\d|2[0-3])(:([0-5]\\d))*";
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)+1, 0, 0, 0);
		Calendar noticeDate = createCalendarFromString(scheduleDate);
		if(calendar.compareTo(noticeDate) >= 0) {
			failedAttribute.put("visitNoticeNotLater", "visitNotice");
			res = false;
		}
		failedAttribute.put("tomorrow", calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
		
		if(hospital.length() != 0 && hospCode.length() == 0) {
			//hospCode가 없고 hospital만 있을 경우(강제로 값을 넣었을때)
			failedAttribute.put("hospCode", "hospCode");
			res = false;
		}else if(hospital.length() != 0) {
			//hospital이 있을 때, hospCode로 조회한 병원이름과 hospital이 동일한지
			valid = hospital.equals(hospitalService.searchByHospitalCode(hospCode).getHospName());
			if(!valid) {
				failedAttribute.put("hospital", "hospital");
				res = false;
			}
		}
		
		if(noticeTime.length() == 0) {
			failedAttribute.put("noNoticeTime", "noNoticeTime");
			res = false;
		}else{
			valid = Pattern.matches(timeReg, noticeTime);
			if(!valid) {
				failedAttribute.put("noticeTime", "noticeTime");
				res = false;
			}
		}
		
		if(!res) {
			request.getSession().setAttribute("scheduleFailed", failedAttribute);
			request.getSession().setAttribute("scheduleForm", this);
		}else {
			request.getSession().removeAttribute("scheduleFailed");
			request.getSession().removeAttribute("scheduleForm");
		}
		return res;
	}
	
	private Calendar createCalendarFromString(String dateStr) {
		String[] dateArr = dateStr.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1])-1, Integer.parseInt(dateArr[2]));
		return calendar;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public String getHospital() {
		return hospital;
	}

	public String getHospCode() {
		return hospCode;
	}

	public String getNoticeTime() {
		return noticeTime;
	}
	
}
