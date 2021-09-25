package com.kh.semi.schedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.kh.semi.common.exception.PageNotFoundException;
import com.kh.semi.member.model.dto.Member;
import com.kh.semi.schedule.model.dto.Medical;
import com.kh.semi.schedule.model.dto.Prescription;
import com.kh.semi.schedule.model.service.ScheduleService;

@WebServlet("/schedule/*")
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScheduleService scheduleService = new ScheduleService();
       
    public ScheduleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[uriArr.length-1]) {
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
		default: throw new PageNotFoundException();
		}
		
	}

	//복용 알림 등록
	private void doseNoticeRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	//진료 알림 등록
	private void visitNoticeRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	//진료 일정 기록
	private void scheduleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date scheduleDate = parseStringToDate(request.getParameter("schedule_date"));
		String hospital = request.getParameter("hospital");
		String pharm = request.getParameter("pharm");
		String[] medicine = request.getParameterValues("abc");
		String doseStartStr = request.getParameter("dose_start");
		String doseEndStr = request.getParameter("dose_end");
		int doseTimes = Integer.parseInt(request.getParameter("dose_times"));
		String[] visitNotice = request.getParameterValues("visit_notice_date");
		String[] doseNotice = request.getParameterValues("dose_notice");
		Date doseStart = null;
		Date doseEnd = null;
		Timestamp[] doseNoticeDateTimes = null;
		
		if(doseStartStr.length() != 0) {
			doseStart = parseStringToDate(doseStartStr);
		}
		
		if(doseEndStr.length() != 0) {
			doseEnd = parseStringToDate(doseEndStr);
		}
		
		if(doseNotice != null) {
			doseNoticeDateTimes = createDoseNoticeDateTimes(doseStartStr, doseEndStr, doseNotice);
		}
		
		/*
		 * System.out.println(doseStart);
		 * System.out.println(doseEnd);
		 * System.out.println((Days.daysBetween(doseStart, doseEnd)).getDays());
		 * System.out.println(doseStart.withTimeAtStartOfDay());
		 * System.out.println(doseEnd.withTimeAtStartOfDay());
		 * System.out.println((Days.daysBetween(doseStart.withTimeAtStartOfDay(),
		 * doseEnd.withTimeAtStartOfDay())).getDays());
		 */
		Member member = (Member) request.getSession().getAttribute("authentication");
		String scheduleId = scheduleService.insertScheduleList(member.getUserCode());
		
		
		if(hospital != null) {
			Medical medical = new Medical();
			medical.setScheduleId(scheduleId);
			medical.setScheduleDate(scheduleDate);
			medical.setScheduleName(createScheduleName(hospital));
			//medical.setHospCode();
			scheduleService.insertMedicalHistory(medical);
		}
		
		
		
		if (pharm != null || medicine != null || doseStart != null
				|| doseEnd != null /* || doseTimes != 0 */ || doseNotice != null) {
			Prescription prescrition = new Prescription();
			prescrition.setScheduleId(scheduleId);
			prescrition.setPrescriptionName(createPrescriptionName(pharm, medicine, doseNotice));
			prescrition.setStartDate(doseStart);
			prescrition.setEndDate(doseEnd);
			//prescrition.setPharmCode();
			//prescrition.setHasMedicine(hasMedicine);
			prescrition.setTimesPerDay(doseTimes);
			if(doseNotice != null) {
				prescrition.setHasDoseNotice(1);
			}
			scheduleService.insertPrescription(prescrition, doseNoticeDateTimes);
		}
		
		
		/*
		 * System.out.println("진료 날짜 : " + scheduleDate);
		 * System.out.println("진료 병원 : " + hospital);
		 * System.out.println("처방 약국 : " + pharm);
		 * System.out.println("처방 약 : " + Arrays.toString(medicine));
		 * System.out.println("복용 시작 : " + doseStart);
		 * System.out.println("복용 종료 : " + doseEnd);
		 * System.out.println("복용 횟수 : " + doseTimes);
		 * System.out.println("진료 알림 : " + Arrays.toString(visitNotice));
		 * System.out.println("복용 알림 : " + Arrays.toString(doseNotice));
		 */
		 
		 
		 
		
	}
	
	private Timestamp[] createDoseNoticeDateTimes(String doseStart, String doseEnd, String[] doseNotice) {
		DateTime startDateTime = parseStringToDateTime(doseStart);
		DateTime endDateTime = parseStringToDateTime(doseEnd);
		List<Timestamp> timeList = new ArrayList<Timestamp>();
		
		int period = (Days.daysBetween(startDateTime, endDateTime)).getDays();
		
		for (int i = 0; i <= period; i++) {
			for (int j = 0; j < doseNotice.length; j++) {
				Calendar calendar = createFewDaysLaterCalendarFromString(doseStart, i);
				String[] timeArr = doseNotice[j].toString().split(":");
				calendar.set(Calendar.HOUR, Integer.parseInt(timeArr[0]));
				calendar.set(Calendar.MINUTE, Integer.parseInt(timeArr[1]));
				timeList.add(new Timestamp(calendar.getTime().getTime()));
			}
		}
		return timeList.toArray(new Timestamp[0]);
	}

	private String createPrescriptionName(String pharm, String[] medicine, String[] doseNotice) {
		String name = "";
		
		if(medicine != null) {
			if(medicine.length > 1) {
				name = medicine[0] + "외" + medicine.length + " ";
			}else {
				name = medicine[0] + " ";
			}
		}else if(pharm != null) {
			name = pharm + " ";
		}else {
			name = "뵥용약";
		}
		
		if(doseNotice != null) {
			name += "처방";
		}else {
			name += "복용 얄림";
		}
		return name;
	}
	
	private String createScheduleName(String hospital) {
		if(hospital == null) {
			return "병원 진료";
		}
		return hospital + "진료";
	}
	
	private DateTime parseDateToDateTime(Date date) {
		return new DateTime(date.getYear(), date.getMonth()+1, date.getDate(), 0, 0, 0);
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
