package com.kh.semi.schedule.validator;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.kh.semi.medicine.model.service.MedicineService;
import com.kh.semi.pharmacy.model.service.PharmacyService;

public class DoseNoticeForm {

	private String doseStart;
	private String doseEnd;
	private String pharmacy;
	private String pharmCode;
	private String[] medicine;
	private String[] mediCode;
	private String doseTimes;
	private String[] doseNoticeTime;
	private HttpServletRequest request;
	private PharmacyService pharmacyService = new PharmacyService();
	private MedicineService medicineService = new MedicineService();
	private Map<String, String> failedAttribute = new HashMap<String, String>();
	
	public DoseNoticeForm(HttpServletRequest request) {
		this.request = request;
		this.doseStart = request.getParameter("dose_start");
		this.doseEnd = request.getParameter("dose_end");
		this.pharmacy = request.getParameter("pharm");
		this.pharmCode = request.getParameter("pharmCode");
		this.medicine = request.getParameterValues("medicine");
		this.mediCode = request.getParameterValues("mediCode");
		this.doseTimes = request.getParameter("dose_times");
		this.doseNoticeTime = request.getParameterValues("dose_notice");
	}
	
	public boolean testHasValue() {
		if(doseStart.length() != 0) {return true;}
		if(doseEnd.length() != 0){return true;}
		if(pharmacy.length() != 0) {return true;}
		if(medicine != null) {return true;}
		if(!doseTimes.equals("0")) {return true;}
		if(doseNoticeTime != null) {return true;}
		return false;
	}
	
	public boolean standardDateTest() {
		boolean res = true;
		String dateReg = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
		
		res = Pattern.matches(dateReg, doseStart);
		return res;
	}
	
	public boolean test() {
		boolean res = true;
		boolean valid = true;
		
		String dateReg = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
		String timeReg = "^([0-1]\\d|2[0-3])(:([0-5]\\d))*";
		
		//복용 시작일일 있을 때, 복용 종료일이 있는지
		//복용 시작일과 복용 종료일이 정규식 표현이 맞는지
		//복용 종료일이 복용 시작일 이후인지
		if(doseStart.length() != 0) {
			if(doseEnd.length() == 0) {
				failedAttribute.put("isNotDoseEnd", "doseEnd");
				res = false;
				System.out.println("시작일자은 있는데 종료일자가 없음");
			}else if(doseEnd.length() != 0) {
				valid = Pattern.matches(dateReg, doseEnd);
				if(!valid) {
					failedAttribute.put("doseEnd", "doseEnd");
					res = false;
					System.out.println("종료일자 정규표현식 오류");
				}else {
					Calendar startDate = createCalendarFromString(doseStart);
					Calendar endDate = createCalendarFromString(doseEnd);
					if(startDate.compareTo(endDate) > 0) {
						failedAttribute.put("doseEndNotLater", "doseEnd");
						res = false;
						System.out.println("종료일자가 시작일자보다 빠름");
					}
				}
			}
		}
		
		//pharmCode가 없고 pharmacy만 있을 경우(강제로 값을 넣었을때)
		if(pharmacy.length() != 0 && pharmCode.length() == 0) {
			failedAttribute.put("pharmCode", "pharmCode");
			res = false;
			System.out.println("pharmCode valid : 없음");
		}else if(pharmacy.length() != 0) {
			//pharmacy가 있을 때, pharmCode로 조회한 약국이름과 pharmacy가 동일한지
			valid = pharmacy.equals(pharmacyService.getPharmNameByCode(pharmCode));
			if(!valid) {
				failedAttribute.put("pharmacy", "pharmacy");
				res = false;
			}
			System.out.println("pharmacy valid : " + valid);
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
			System.out.println("medicine valid" + valid);
		}
		
		//복용획수 값이 0보다 크거나 같고 24보다 같거나 작은지
		int times = Integer.parseInt(doseTimes);
		if(times < 0 || times > 24) {
			failedAttribute.put("doseTimes", "doseTimes");
			res = false;
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
	
	public String getDoseStart() {
		return doseStart;
	}

	public String getDoseEnd() {
		return doseEnd;
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

	public String getDoseTimes() {
		return doseTimes;
	}

	public String[] getDoseNoticeTime() {
		return doseNoticeTime;
	}
	
}
