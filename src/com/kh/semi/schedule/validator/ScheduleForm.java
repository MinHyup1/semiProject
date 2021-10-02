package com.kh.semi.schedule.validator;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.kh.semi.hospitalInfo.model.service.HospitalService;
import com.kh.semi.medicine.model.service.MedicineService;
import com.kh.semi.pharmacy.model.service.PharmacyService;

public class ScheduleForm {

	private String scheduleDate;
	private String hospital;
	private String hospCode;
	private String pharmacy;
	private String pharmCode;
	private String[] medicine;
	private String[] mediCode;
	private String doseStart;
	private String doseEnd;
	private String doseTimes;
	private String[] visitNotice;
	private String[] doseNoticeTime;
	private HttpServletRequest request;
	private HospitalService hospitalService = new HospitalService();
	private PharmacyService pharmacyService = new PharmacyService();
	private MedicineService medicineService = new MedicineService();
	private Map<String, String> failedAttribute = new HashMap<String, String>();
	
	public ScheduleForm(HttpServletRequest request) {
		this.request = request;
		this.scheduleDate = request.getParameter("schedule_date");
		this.hospital = request.getParameter("hospital");
		this.hospCode = request.getParameter("hospCode");
		this.pharmacy = request.getParameter("pharm");
		this.pharmCode = request.getParameter("pharmCode");
		this.medicine = request.getParameterValues("medicine");
		this.mediCode = request.getParameterValues("mediCode");
		this.doseStart = request.getParameter("dose_start");
		this.doseEnd = request.getParameter("dose_end");
		this.doseTimes = request.getParameter("dose_times");
		this.visitNotice = request.getParameterValues("visit_notice_date");
		this.doseNoticeTime = request.getParameterValues("dose_notice");
	}
	
	public boolean testHasValue() {
		if(hospital.length() != 0) {return true;}
		if(pharmacy.length() != 0) {return true;}
		if(medicine != null) {return true;}
		if(doseStart.length() != 0) {return true;}
		if(doseEnd.length() != 0){return true;}
		if(!doseTimes.equals("0")) {return true;}
		if(visitNotice != null) {return true;}
		if(doseNoticeTime != null) {return true;}
		return false;
	}
	
	public boolean standardDateTest() {
		boolean res = true;
		String dateReg = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
		
		//scheduleDate가 정규식 표현에 맞는지
		res = Pattern.matches(dateReg, scheduleDate);
		System.out.println("scheduleDate vaild : " + res);
		return res;
	}
	
	public boolean test() {
		boolean res = true;
		boolean valid = true;
		
		String dateReg = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
		String timeReg = "^([0-1]\\d|2[0-3]):([0-5]\\d)";
		
		//hospCode가 없고 hospital만 있을 경우(강제로 값을 넣었을때)
		if(hospital.length() != 0 && hospCode.length() == 0) {
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
		
		//pharmCode가 없고 pharmacy만 있을 경우(강제로 값을 넣었을때)
		if(pharmacy.length() != 0 && pharmCode.length() == 0) {
			failedAttribute.put("pharmCode", "pharmCode");
			res = false;
		}else if(pharmacy.length() != 0) {
			//pharmacy가 있을 때, pharmCode로 조회한 약국이름과 pharmacy가 동일한지
			valid = pharmacy.equals(pharmacyService.getPharmNameByCode(pharmCode));
			if(!valid) {
				failedAttribute.put("pharmacy", "pharmacy");
				res = false;
			}
		}
		
		//medicine이 있을 때, mediCode로 조회한 약 이름과 medicine이 동일한지
		if(medicine != null) {
			valid = true;
			for (int i = 0; i < medicine.length; i++) {
				boolean flg = medicine[i].equals(medicineService.selectMedNameByNum(Integer.parseInt(mediCode[i])));
				if(!flg) valid = false;
			}
			if(!valid) {
				failedAttribute.put("medicine", "medicine");
				res = false;
			}
		}
		
		//복용 시작일일 있을 때, 복용 종료일이 있는지
		//복용 시작일과 복용 종료일이 정규식 표현이 맞는지
		//복용 종료일이 복용 시작일 이후인지
		if(doseStart.length() != 0) {
			valid = Pattern.matches(dateReg, doseStart);
			if(!valid) {
				failedAttribute.put("doseStart", "doseStart");
				res = false;
			}
			
			if(doseEnd.length() == 0) {
				failedAttribute.put("isNotDoseEnd", "doseEnd");
				res = false;
			}else if(doseEnd.length() != 0) {
				valid = Pattern.matches(dateReg, doseEnd);
				if(!valid) {
					failedAttribute.put("doseEnd", "doseEnd");
					res = false;
				}else {
					Calendar startDate = createCalendarFromString(doseStart);
					Calendar endDate = createCalendarFromString(doseEnd);
					if(startDate.compareTo(endDate) > 0) {
						failedAttribute.put("doseEndNotLater", "doseEnd");
						res = false;
					}
				}
			}
		}
		
		//복용획수 값이 0보다 크거나 같고 24보다 같거나 작은지
		int times = Integer.parseInt(doseTimes);
		if(times < 0 || times > 24) {
			failedAttribute.put("doseTimes", "doseTimes");
			res = false;
		}
		
		//다음 진료 알림이 정규식 표현이 맞는지
		if(visitNotice != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)+1, 0, 0, 0);
			valid = true;
			for (int i = 0; i < visitNotice.length; i++) {
				String[] dateTimeArr = visitNotice[i].split("T");
				if(!Pattern.matches(dateReg, dateTimeArr[0])) {
					valid = false;
				}else if(!Pattern.matches(timeReg, dateTimeArr[1])) {
					valid = false;
				}
			}
			if(!valid) {
				failedAttribute.put("visitNotice", "visitNotice");
				res = false;
			}else {
				for (int i = 0; i < visitNotice.length; i++) {
					String[] dateTimeArr = visitNotice[i].split("T");
					Calendar noticeDate = createCalendarFromString(dateTimeArr[0]);
					noticeDate = addHourMinute(noticeDate, dateTimeArr[1]);
					if(calendar.compareTo(noticeDate) >= 0) {
						valid = false;
					}
				}
				if(!valid) {
					failedAttribute.put("visitNoticeNotLater", "visitNotice");
					res = false;
				}
			}
			failedAttribute.put("tomorrow", calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE)+"T00:00");
		}
		
		//복용알림 시간은 정규식 표현이 맞는지
		if(doseNoticeTime != null) {
			valid = true;
			for (int i = 0; i < doseNoticeTime.length; i++) {
				if(!Pattern.matches(timeReg, doseNoticeTime[i])) {
					valid = false;
				}
			}
			if(!valid) {
				failedAttribute.put("doseNoticeTime", "doseNoticeTime");
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
	
	private Calendar addHourMinute(Calendar calendar, String timeStr) {
		String[] timeArr = timeStr.split(":");
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArr[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(timeArr[1]));
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

	public String getPharmacy() {
		return pharmacy;
	}

	public String getPharmCode() {
		return pharmCode;
	}

	public String[] getMedicine() {
		return medicine;
	}

	public String[] getMediCode() {
		return mediCode;
	}

	public String getDoseStart() {
		return doseStart;
	}

	public String getDoseEnd() {
		return doseEnd;
	}

	public String getDoseTimes() {
		return doseTimes;
	}

	public String[] getVisitNotice() {
		return visitNotice;
	}

	public String[] getDoseNoticeTime() {
		return doseNoticeTime;
	}
	
}
