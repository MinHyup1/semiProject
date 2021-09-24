package com.kh.semi.schedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String scheduleDate = request.getParameter("schedule_date");
		String hospital = request.getParameter("hospital");
		String pharm = request.getParameter("pharm");
		String[] medicine = request.getParameterValues("abc");
		Date doseStart = null;
		Date doseEnd = null;
		int doseTimes = Integer.parseInt(request.getParameter("dose_times"));
		String[] visitNotice = request.getParameterValues("visit_notice_date");
		String[] doseNotice = request.getParameterValues("dose_notice");
		
		if(request.getParameter("dose_start").length() != 0) {
			doseStart = parseStringToDate(request.getParameter("dose_start"));
		}
		
		if(request.getParameter("dose_end").length() != 0) {
			doseEnd = parseStringToDate(request.getParameter("dose_end"));
		}
		
		System.out.println(doseStart.compareTo(doseEnd));
		System.out.println(doseEnd.compareTo(doseStart));
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		String scheduleId = scheduleService.insertScheduleList(member.getUserCode());
		
		
		if(hospital != null) {
			Medical medical = new Medical();
			medical.setScheduleId(scheduleId);
			//medical.setScheduleName();
			//medical.setHospCode();
		}
		
		
		
		if(pharm != null || medicine != null || doseStart != null || doseEnd != null ) {
			
		}
		
		
		
		
		/*
		 * System.out.println("진료 날짜 : " + scheduleDate);
		 * System.out.println("진료 병원 : " + hospital);
		 * System.out.println("처방 약국 : " + pharm);
		 * System.out.println("처방 약 : " + Arrays.toString(medicine));
		 * System.out.println("복용 시작 : " + doseStart);
		 * System.out.println(doseStart.length() == 0);
		 * System.out.println("복용 종료 : " + doseEnd);
		 * System.out.println("복용 횟수 : " + doseTimes);
		 * System.out.println("진료 알림 : " + Arrays.toString(visitNotice));
		 * System.out.println("복용 알림 : " + Arrays.toString(doseNotice));
		 */
		 
		
	}
	
	private Date parseStringToDate(String dateStr) {
		String[] dateArr = dateStr.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1])-1, Integer.parseInt(dateArr[2]));
		Date date = new Date(calendar.getTime().getTime());
		return date;
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
