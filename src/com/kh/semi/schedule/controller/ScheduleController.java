package com.kh.semi.schedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.google.gson.Gson;
import com.kh.semi.common.code.ErrorCode;
import com.kh.semi.common.exception.HandlableException;
import com.kh.semi.common.exception.PageNotFoundException;
import com.kh.semi.member.model.dto.Member;
import com.kh.semi.schedule.model.dto.Medical;
import com.kh.semi.schedule.model.dto.Prescription;
import com.kh.semi.schedule.model.dto.Visit;
import com.kh.semi.schedule.model.service.ScheduleService;

@WebServlet("/schedule/*")
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScheduleService scheduleService = new ScheduleService();
	private static Gson gson = new Gson();
	//private Member member = null;
	
    public ScheduleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Content-Type", "text/html; charset=utf-8");
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[2]) {
		case "schedule-main": //메인페이지 이동
			scheduleMain(request, response);
			break;
		case "schedule-record-form": //일정 기록 페이지 이동
			scheduleRecordForm(request, response);
			break;
		case "schedule-register": //일정 등록
			scheduleRegister(request, response);
			break;
		case "visit-notice-form": //진료 알림 작성페이지 이동
			visitNoticeForm(request, response);
			break;
		case "visit-notice-register": //진료 알림 등록
			visitNoticeRegister(request, response);
			break;
		case "medicine-notice-form": //복용 알림 작성페이지 이동
			medicineNoticeForm(request, response);
			break;
		case "dose-notice-register": //복용 알림 등록
			doseNoticeRegister(request, response);
			break;
		case "get-schedule": //사용자별 등록된 스케줄 DB에서 가져오기
			getSchedule(request, response);
			break;
		case "get-medical": //medical DB에서 가져오기
			getMedical(request, response);
			break;
		case "get-prescription": //prescription DB에서 가져오기
			getPrescription(request, response);
			break;
		case "get-visit": //visit DB에서 가져오기
			//getVisit(request, response);
			break;
		case "schedule-edit": //스케줄 수정하기
			editSchedule(request, response, uriArr);
			break;
		default: throw new PageNotFoundException();
		}
		
	}

	

	private void editSchedule(HttpServletRequest request, HttpServletResponse response, String[] uriArr) throws ServletException, IOException {
		switch(uriArr[3]) {
		case "medical":
			request.getRequestDispatcher("/schedule/medical_input").forward(request, response);
		}
		
		
		
	}
	
	private void getPrescription(HttpServletRequest request, HttpServletResponse response) {
		String prescriptionId = request.getParameter("prescriptionId");
		Map<String, Object> prescMap = scheduleService.selectPrescriptionById(prescriptionId);
		
	}

	private void getMedical(HttpServletRequest request, HttpServletResponse response) {
		String historyId = request.getParameter("historyId");
		Medical medical = scheduleService.selectMedicalById(historyId);
		request.getSession().setAttribute("currentSchedule", medical);
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("schedule_date", medical.getScheduleDate().toString());
		map.put("hospital", medical.getHospCode()); //병원 검색 완성 시 병원 조회 후 넣기
		datas.add(map);
		
		//key에 대한 value가 null이면 자동으로 json에서 빠지게 된다.
		String responseBody = gson.toJson(datas);
		try {
			response.getWriter().print(responseBody);
		} catch (IOException e) {
			throw new HandlableException(ErrorCode.FAILED_GET_SCHEDULE);
		}
	}

	private void getSchedule(HttpServletRequest request, HttpServletResponse response) {
		Member member = (Member) request.getSession().getAttribute("authentication");
		Map<String, Object> scheduleMap =  scheduleService.selectScheduleByUser(member.getUserCode());
		
		if(scheduleMap.size() == 0) {
			try {
				response.getWriter().print(false);
			} catch (IOException e) {
				throw new HandlableException(ErrorCode.FAILED_GET_SCHEDULE);
			}
		}
		
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		
		List<Medical> medicalList = (List<Medical>) scheduleMap.get("medicalList");
		List<Prescription> prescriptionList = (List<Prescription>) scheduleMap.get("prescriptionList");
		List<Visit> visitList = (List<Visit>) scheduleMap.get("visitList");
		
		if(medicalList.size() != 0) {
			for (int i = 0; i < medicalList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Medical medical = medicalList.get(i);
				//map.put("id", medical.getHistoryId());
				map.put("id", medical.getHistoryId());
				map.put("start", medical.getScheduleDate().toString());
				map.put("allDay", true);
				map.put("title", medical.getScheduleName());
				map.put("backgroundColor", "purple");
				map.put("color", "white");
				map.put("kind", "medical");
				datas.add(map);
			}
		}
		
		if(prescriptionList.size() != 0) {
			for (int i = 0; i < prescriptionList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Prescription prescription = prescriptionList.get(i);
				//map.put("id", prescription.getPrescriptionId());
				map.put("id", prescription.getPrescriptionId());
				map.put("start", prescription.getStartDate().toString());
				map.put("end", prescription.getEndDate().toString() + "T23:59:59");
				if(prescription.getStartDate().toString().equals(prescription.getEndDate().toString())) map.put("allDay", true);
				map.put("title", prescription.getPrescriptionName());
				map.put("backgroundColor", "orange");
				map.put("color", "white");
				map.put("kind", "prescription");
				datas.add(map);
			}
		}
		
		if(visitList.size() != 0) {
			for (int i = 0; i < visitList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Visit visit = visitList.get(i);
				//map.put("id", visit.getVisitNoticeCode());
				map.put("id", visit.getVisitNoticeCode());
				map.put("start", getDateStringFromTimestamp(visit.getNoticeDate()));
				map.put("allDay", true);
				map.put("title", visit.getNoticeName());
				map.put("backgroundColor", "green");
				map.put("color", "white");
				map.put("kind", "visit");
				datas.add(map);
			}
		}
		
		String responseBody = gson.toJson(datas);
		try {
			response.getWriter().print(responseBody);
		} catch (IOException e) {
			throw new HandlableException(ErrorCode.FAILED_GET_SCHEDULE);
		}
	}
	
	private String getDateStringFromTimestamp(Timestamp dateTime) {
		int year = dateTime.getYear() + 1900;
		int month = dateTime.getMonth() + 1;
		int date = dateTime.getDate();
		
		String mm = month < 10 ? "0" + month : String.valueOf(month);
		String dd = date < 10 ? "0" + date : String.valueOf(date);
		
		return year + "-" + mm + "-" + dd;
 	}
	
	private String getTimeStringFromTimestamp(Timestamp dateTime) {
		int hours = dateTime.getHours();
		int minutes = dateTime.getMinutes();
		
		String hh = hours < 10 ? "0" + hours : String.valueOf(hours);
		String mi = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
		
		return hh + ":" + mi + ":00";
	}

	//복용 알림 등록
	private void doseNoticeRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String doseStartStr = request.getParameter("dose_start");
		String doseEndStr = request.getParameter("dose_end");
		String pharm = request.getParameter("pharm");
		String[] medicine = request.getParameterValues("");
		int doseTimes = Integer.parseInt(request.getParameter("dose_times"));
		String[] doseNotice = request.getParameterValues("dose_notice");

		Date doseStart = parseStringToDate(doseStartStr);
		Date doseEnd = parseStringToDate(doseEndStr);
		Map<String, Object> dtoMap = new HashMap<String, Object>();
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		
		Prescription prescription = new Prescription();
		prescription.setPrescriptionName(createPrescriptionName(pharm, medicine, doseNotice));
		prescription.setStartDate(doseStart);
		prescription.setEndDate(doseEnd);
		//prescrition.setPharmCode();
		prescription.setTimesPerDay(doseTimes);
		dtoMap.put("prescription", prescription);
		
		// dose_notice_list
		Timestamp[] doseNoticeDateTimes = createDoseNoticeDateTimes(doseStartStr, doseEndStr, doseNotice);
		dtoMap.put("doseNoticeDateTimes", doseNoticeDateTimes);
		
		// medicine_list
		if(medicine != null) {
			
		}
		
		scheduleService.insertDoseNoticeOnly(member.getUserCode(), dtoMap);
	}

	//진료 알림 등록
	private void visitNoticeRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scheduleDate = request.getParameter("schedule_date");
		String hospital = request.getParameter("hospital");
		String visitTime = request.getParameter("visit_notice_time");
		Calendar noticeCalendar = addHourMinute(createCalendarFromString(scheduleDate), visitTime);
		Timestamp noticeDateTime = new Timestamp(noticeCalendar.getTime().getTime());
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		
		Visit visit = new Visit();
		visit.setNoticeName(createVisitName(hospital));
		//visit.setHospCode(hospCode);
		visit.setNoticeDate(noticeDateTime);
		
		scheduleService.insertVisitNoticeOnly(member.getUserCode(), visit);
	}

	//진료 일정 기록
	private void scheduleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date scheduleDate = parseStringToDate(request.getParameter("schedule_date"));
		String hospital = request.getParameter("hospital");
		String pharm = request.getParameter("pharm");
		String[] medicine = request.getParameterValues("");
		String doseStartStr = request.getParameter("dose_start");
		String doseEndStr = request.getParameter("dose_end");
		int doseTimes = Integer.parseInt(request.getParameter("dose_times"));
		String[] visitNotice = request.getParameterValues("visit_notice_date");
		String[] doseNotice = request.getParameterValues("dose_notice");
		
		Date doseStart = null;
		Date doseEnd = null;
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		Map<String, Object> dtoMap = new HashMap<String, Object>();
		
		if(doseStartStr.length() != 0) {
			doseStart = parseStringToDate(doseStartStr);
		}
		if(doseEndStr.length() != 0) {
			doseEnd = parseStringToDate(doseEndStr);
		}
		
		// Medical_history
		if(hospital.length() != 0) {
			Medical medical = new Medical();
			medical.setScheduleDate(scheduleDate);
			medical.setScheduleName(createScheduleName(hospital));
			//medical.setHospCode();
			dtoMap.put("medical", medical);
		}
		
		// prescription_list
		if (pharm.length() != 0 || medicine != null || doseStart != null
				|| doseEnd != null || doseTimes != 0 || doseNotice != null) {
			
			if(doseStart == null && doseEnd == null) {
				doseStart = scheduleDate;
				doseEnd = scheduleDate;
			}
			
			Prescription prescription = new Prescription();
			prescription.setPrescriptionName(createPrescriptionName(pharm, medicine, doseNotice));
			prescription.setStartDate(doseStart);
			prescription.setEndDate(doseEnd);
			//prescrition.setPharmCode();
			prescription.setTimesPerDay(doseTimes);
			dtoMap.put("prescription", prescription);
			
			// dose_notice_list
			if(doseNotice != null) {
				Timestamp[] doseNoticeDateTimes = createDoseNoticeDateTimes(doseStartStr, doseEndStr, doseNotice);
				dtoMap.put("doseNoticeDateTimes", doseNoticeDateTimes);
			}
			
			// medicine_list
			
		}
		
		// visit_notice
		if(visitNotice != null) {
			List<Visit> visitList = new ArrayList<Visit>();
			Timestamp[] visitNoticeDateTimes = createVisitNoticeDateTimes(visitNotice);
			
			for (int i = 0; i < visitNoticeDateTimes.length; i++) {
				Visit visit = new Visit();
				visit.setNoticeName(createVisitName(hospital));
				//visit.setHospCode(hospCode);
				visit.setNoticeDate(visitNoticeDateTimes[i]);
				visitList.add(visit);
			}
			dtoMap.put("visitList", visitList);
		}
		
		scheduleService.insertMainSchedule(member.getUserCode(), dtoMap);
	}
	
	private Timestamp[] createVisitNoticeDateTimes(String[] visitNotice) {
		Timestamp[] noticeDateTimes = new Timestamp[visitNotice.length];
		
		for (int i = 0; i < noticeDateTimes.length; i++) {
			String[] dateTimeArr = visitNotice[i].split("T");
			Calendar calendar = createCalendarFromString(dateTimeArr[0]);
			calendar = addHourMinute(calendar, dateTimeArr[1]);
			noticeDateTimes[i] = new Timestamp(calendar.getTime().getTime());
		}
		return noticeDateTimes;
	}

	private Timestamp[] createDoseNoticeDateTimes(String doseStart, String doseEnd, String[] doseNotice) {
		DateTime startDateTime = parseStringToDateTime(doseStart);
		DateTime endDateTime = parseStringToDateTime(doseEnd);
		List<Timestamp> timeList = new ArrayList<Timestamp>();
		
		int period = (Days.daysBetween(startDateTime, endDateTime)).getDays();
		
		for (int i = 0; i <= period; i++) {
			for (int j = 0; j < doseNotice.length; j++) {
				Calendar calendar = createFewDaysLaterCalendarFromString(doseStart, i);
				calendar = addHourMinute(calendar, doseNotice[j]);
				timeList.add(new Timestamp(calendar.getTime().getTime()));
			}
		}
		return timeList.toArray(new Timestamp[0]);
	}
	
	private DateTime parseStringToDateTime(String dateStr) {
		String[] dateArr = dateStr.split("-");
		DateTime dateTime = new DateTime(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]), 0, 0, 0);
		return dateTime;
	}
	
	private Date parseStringToDate(String dateStr) {
		Date date = new Date(createCalendarFromString(dateStr).getTime().getTime());
		return date;
	}
	
	private Calendar addHourMinute(Calendar calendar, String timeStr) {
		String[] timeArr = timeStr.split(":");
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArr[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(timeArr[1]));
		return calendar;
	}
	
	private Calendar createCalendarFromString(String dateStr) {
		String[] dateArr = dateStr.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1])-1, Integer.parseInt(dateArr[2]));
		return calendar;
	}
	
	private Calendar createFewDaysLaterCalendarFromString(String dateStr, int days) {
		String[] dateArr = dateStr.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1])-1, Integer.parseInt(dateArr[2]) + days);
		return calendar;
	}
	
	private String createVisitName(String hospital) {
		if(hospital.length() != 0) {
			return "병원 진료 예약 알림";
		}
		return hospital + "진료 예약 알림";
	}
	
	private String createPrescriptionName(String pharm, String[] medicine, String[] doseNotice) {
		String name = "";
		
		if(medicine != null) {
			if(medicine.length > 1) {
				name = medicine[0] + "외" + medicine.length + " ";
			}else {
				name = medicine[0] + " ";
			}
		}else if(pharm.length() != 0) {
			name = pharm + " 약";
		}else {
			name = "약 ";
		}
		
		if(doseNotice == null) {
			name += "처방 및 복용";
		}else {
			name += "복용 알림";
		}
		return name;
	}
	
	private String createScheduleName(String hospital) {
		if(hospital.length() == 0) {
			return "병원 진료";
		}
		return hospital + "진료";
	}

	private void medicineNoticeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/medicine_notice_input").forward(request, response);
	}

	private void visitNoticeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/visit_notice_input").forward(request, response);
	}

	private void scheduleRecordForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/schedule_input").forward(request, response);
	}

	private void scheduleMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/schedule_main").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
