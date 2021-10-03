package com.kh.semi.schedule.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.kh.semi.hospitalInfo.model.service.SearchHospitalService;

public class MedicalForm {

	private String scheduleDate;
	private String hospital;
	private String hospCode;
	private HttpServletRequest request;
	private SearchHospitalService hospitalService = new SearchHospitalService();
	private Map<String, String> failedAttribute = new HashMap<String, String>();
	
	public MedicalForm(HttpServletRequest request) {
		this.request = request;
		this.scheduleDate = request.getParameter("schedule_date");
		this.hospital = request.getParameter("hospital");
		this.hospCode = request.getParameter("hospCode");
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
		if(hospital.length() == 0) {
			failedAttribute.put("noHospital", "noHospital");
			res = false;
		}else if(hospital.length() != 0 && hospCode.length() == 0) {
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
		if(!res) {
			request.getSession().setAttribute("scheduleFailed", failedAttribute);
			request.getSession().setAttribute("scheduleForm", this);
		}else {
			request.getSession().removeAttribute("scheduleFailed");
			request.getSession().removeAttribute("scheduleForm");
		}
		return res;
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
	
}
