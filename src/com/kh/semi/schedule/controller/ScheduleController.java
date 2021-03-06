package com.kh.semi.schedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.kh.semi.hospitalInfo.model.dto.HospitalInfo;
import com.kh.semi.hospitalInfo.model.service.SearchHospitalService;
import com.kh.semi.medicine.model.service.MedicineService;
import com.kh.semi.member.model.dto.Member;
import com.kh.semi.pharmacy.model.service.PharmacyService;
import com.kh.semi.schedule.model.dto.Medical;
import com.kh.semi.schedule.model.dto.Prescription;
import com.kh.semi.schedule.model.dto.Visit;
import com.kh.semi.schedule.model.service.ScheduleService;

@WebServlet("/schedule/*")
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScheduleService scheduleService = new ScheduleService();
	private SearchHospitalService hospitalService = new SearchHospitalService();
	private MedicineService medicineService = new MedicineService();
	private PharmacyService pharmacyService = new PharmacyService();
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
		case "schedule-main": //??????????????? ??????
			scheduleMain(request, response);
			break;
		case "schedule-record-form": //?????? ?????? ????????? ??????
			scheduleRecordForm(request, response);
			break;
		case "schedule-register": //?????? ??????
			scheduleRegister(request, response);
			break;
		case "visit-notice-form": //?????? ?????? ??????????????? ??????
			visitNoticeForm(request, response);
			break;
		case "visit-notice-register": //?????? ?????? ??????
			visitNoticeRegister(request, response);
			break;
		case "medicine-notice-form": //?????? ?????? ??????????????? ??????
			medicineNoticeForm(request, response);
			break;
		case "dose-notice-register": //?????? ?????? ??????
			doseNoticeRegister(request, response);
			break;
		case "get-schedule": //???????????? ????????? ????????? DB?????? ????????????
			getSchedule(request, response);
			break;
		case "get-medical": //medical DB?????? ????????????
			getMedical(request, response);
			break;
		case "get-prescription": //prescription DB?????? ????????????
			getPrescription(request, response);
			break;
		case "get-visit": //visit DB?????? ????????????
			getVisit(request, response);
			break;
		case "schedule-edit": //????????? ????????????
			editSchedule(request, response, uriArr);
			break;
		case "schedule-delete": //????????? ????????????
			deleteSchedule(request, response);
			break;
		case "renew-medical": //?????? ?????? ??????
			renewMedical(request, response);
			break;
		case "renew-prescription": //?????? ?????? ??????
			renewPrescription(request, response);
			break;
		case "renew-visit": //?????? ?????? ??????
			renewVisit(request, response);
			break;
		default: throw new PageNotFoundException();
		}
		
	}

	

	

	private void renewVisit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute("currentSchedule");
		Visit originVisit = (Visit) map.get("visit");
		Visit newVisit = getVisitDto(request);
		newVisit.setVisitNoticeCode(originVisit.getVisitNoticeCode());
		newVisit.setScheduleId(originVisit.getScheduleId());
		scheduleService.updateVisit(newVisit);
		request.setAttribute("msg", "???????????? ??????????????? ?????????????????????.");
		request.setAttribute("url", "/schedule/schedule-main");
		request.getRequestDispatcher("/error/result").forward(request, response);
	}

	private void renewPrescription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute("currentSchedule");
		Prescription originPrescription = (Prescription) map.get("prescription");
		
		Map<String, Object> dtoMap = getPrescriptionDtoMap(request);
		Prescription newPrescription = (Prescription) dtoMap.get("prescription");
		newPrescription.setPrescriptionId(originPrescription.getPrescriptionId());
		newPrescription.setScheduleId(originPrescription.getScheduleId());
		
		scheduleService.updatePrescription(dtoMap);
		request.setAttribute("msg", "???????????? ??????????????? ?????????????????????.");
		request.setAttribute("url", "/schedule/schedule-main");
		request.getRequestDispatcher("/error/result").forward(request, response);
	}
	
	

	private void renewMedical(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute("currentSchedule");
		Medical originMedical = (Medical) map.get("medical");
		Date scheduleDate = parseStringToDate(request.getParameter("schedule_date"));
		String hospital = request.getParameter("hospital");
		String hospCode = request.getParameter("hospCode");
		
		Medical newMedical = new Medical();
		newMedical.setHistoryId(originMedical.getHistoryId());
		newMedical.setScheduleId(originMedical.getScheduleId());
		newMedical.setScheduleDate(scheduleDate);
		newMedical.setScheduleName(createScheduleName(hospital));
		newMedical.setHospCode(hospCode);
		scheduleService.updateMedical(newMedical);
		
		request.setAttribute("msg", "???????????? ??????????????? ?????????????????????.");
		request.setAttribute("url", "/schedule/schedule-main");
		request.getRequestDispatcher("/error/result").forward(request, response);
	}

	private void deleteSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object object = request.getSession().getAttribute("currentSchedule");
		Map<String, Object> map = (Map<String, Object>) object;
		if(map.get("medical") != null) {
			Medical medical = (Medical) map.get("medical");
			scheduleService.deleteMedical(medical);
		}else if(map.get("prescription") != null) {
			Prescription prescription = (Prescription) map.get("prescription");
			scheduleService.deletePrescription(prescription);
		}else {
			Visit visit = (Visit) map.get("visit");
			scheduleService.deleteVisit(visit);
		}
		request.setAttribute("msg", "???????????? ??????????????? ?????????????????????.");
		request.setAttribute("url", "/schedule/schedule-main");
		request.getRequestDispatcher("/error/result").forward(request, response);
	}

	private void editSchedule(HttpServletRequest request, HttpServletResponse response, String[] uriArr) throws ServletException, IOException {
		switch(uriArr[3]) {
		case "medical":
			request.getRequestDispatcher("/schedule/medical_input").forward(request, response);
			break;
		case "prescription":
			request.getRequestDispatcher("/schedule/medicine_notice_input").forward(request, response);
			break;
		case "visit":
			request.getRequestDispatcher("/schedule/visit_notice_input").forward(request, response);
			break;
		default: throw new PageNotFoundException();
		}
	}
	
	private void getVisit(HttpServletRequest request, HttpServletResponse response) {
		String visitNoticeCode = request.getParameter("visitNoticeCode");
		Visit visit = scheduleService.selectVisitByCode(visitNoticeCode);
		HospitalInfo hospital = null;
		String hospName = null;
		if(visit.getHospCode() != null) {
			hospital = hospitalService.searchByHospitalCode(visit.getHospCode());
			hospName = hospital.getHospName();
		}
		
		Map<String, Object> visitMap = new HashMap<String, Object>();
		visitMap.put("visit", visit);
		visitMap.put("hospName", hospName);
		visitMap.put("notice_date", getDateStringFromTimestamp(visit.getNoticeDate()));
		visitMap.put("notice_time", getTimeStringFromTimestamp(visit.getNoticeDate()));
		request.getSession().setAttribute("currentSchedule", visitMap);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notice_date", getDateStringFromTimestamp(visit.getNoticeDate()));
		map.put("hospital", hospName);
		map.put("hospCode", visit.getHospCode());
		map.put("notice_time", getTimeStringFromTimestamp(visit.getNoticeDate()));
		
		String responseBody = gson.toJson(map);
		try {
			response.getWriter().print(responseBody);
		} catch (IOException e) {
			throw new HandlableException(ErrorCode.FAILED_GET_SCHEDULE);
		}
	}
	
	private void getPrescription(HttpServletRequest request, HttpServletResponse response) {
		String prescriptionId = request.getParameter("prescriptionId");
		Map<String, Object> prescMap = scheduleService.selectPrescriptionById(prescriptionId);
		List<String> medicineList = null;
		Prescription prescription = (Prescription) prescMap.get("prescription");
		String pharmName = null;
		
		if(prescription.getPharmCode() != null) {
			pharmName = pharmacyService.getPharmNameByCode(prescription.getPharmCode());
			prescMap.put("pharmName", pharmName);
		}
		
		if(prescMap.get("medNumList") != null) {
			List<Integer> medNumList = (List<Integer>) prescMap.get("medNumList");
			medicineList = medicineService.selectMedNameByNum(medNumList);
			prescMap.put("medicineList", medicineList);
		}
		
		request.getSession().setAttribute("currentSchedule", prescMap);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", prescription.getStartDate().toString());
		map.put("end", prescription.getEndDate().toString());
		map.put("pharmName", pharmName);
		map.put("pharmCode", prescription.getPharmCode());
		map.put("timesPerDay", prescription.getTimesPerDay());
		
		if(prescMap.get("timeSet") != null) map.put("doseTime", (Set<String>) prescMap.get("timeSet"));
		/* if(medicineList != null) */map.put("medicineList", medicineList);
		
		String responseBody = gson.toJson(map);
		try {
			response.getWriter().print(responseBody);
		} catch (IOException e) {
			throw new HandlableException(ErrorCode.FAILED_GET_SCHEDULE);
		}
	}

	private void getMedical(HttpServletRequest request, HttpServletResponse response) {
		String historyId = request.getParameter("historyId");
		Map<String, Object> mediMap = new HashMap<String, Object>();
		Medical medical = scheduleService.selectMedicalById(historyId);
		HospitalInfo hospital = hospitalService.searchByHospitalCode(medical.getHospCode());
		mediMap.put("medical", medical);
		mediMap.put("hospName", hospital.getHospName());
		request.getSession().setAttribute("currentSchedule", mediMap);
		
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("schedule_date", medical.getScheduleDate().toString());
		datas.put("hospital", hospital.getHospName());
		datas.put("hospCode", medical.getHospCode()); //?????? ?????? ?????? ??? ?????? ?????? ??? ??????
		
		//key??? ?????? value??? null?????? ???????????? json?????? ????????? ??????.
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

	//?????? ?????? ??????
	private void doseNoticeRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		
		Map<String, Object> dtoMap = getPrescriptionDtoMap(request);
		
		scheduleService.insertDoseNoticeOnly(member.getUserCode(), dtoMap);
		request.setAttribute("msg", "???????????? ??????????????? ?????????????????????.");
		request.setAttribute("url", "/schedule/schedule-main");
		request.getRequestDispatcher("/error/result").forward(request, response);
	}

	//?????? ?????? ??????
	private void visitNoticeRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		Visit visit = getVisitDto(request);
		
		scheduleService.insertVisitNoticeOnly(member.getUserCode(), visit);
		request.setAttribute("msg", "???????????? ??????????????? ?????????????????????.");
		request.setAttribute("url", "/schedule/schedule-main");
		request.getRequestDispatcher("/error/result").forward(request, response);
	}

	//?????? ?????? ??????
	private void scheduleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date scheduleDate = parseStringToDate(request.getParameter("schedule_date"));
		
		String hospCode = request.getParameter("hospCode");
		String hospital = request.getParameter("hospital");
		
		String pharmCode = request.getParameter("pharmCode");
		String pharm = request.getParameter("pharm");
		
		String[] mediCode = request.getParameterValues("mediCode");
		String[] medicine = request.getParameterValues("medicine");
		
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
			medical.setHospCode(hospCode);
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
			prescription.setPharmCode(pharmCode);
			prescription.setTimesPerDay(doseTimes);
			dtoMap.put("prescription", prescription);
			
			// dose_notice_list
			if(doseNotice != null) {
				Timestamp[] doseNoticeDateTimes = createDoseNoticeDateTimes(doseStartStr, doseEndStr, doseNotice);
				dtoMap.put("doseNoticeDateTimes", doseNoticeDateTimes);
			}
			
			// medicine_list
			if(medicine != null && mediCode != null) {
				Integer[] mediCodeNum = new Integer[mediCode.length];
				for (int i = 0; i < mediCodeNum.length; i++) {
					mediCodeNum[i] = Integer.parseInt(mediCode[i]);
				}
				dtoMap.put("mediCodeArr", mediCodeNum);
			}
		}
		
		// visit_notice
		if(visitNotice != null) {
			List<Visit> visitList = new ArrayList<Visit>();
			Timestamp[] visitNoticeDateTimes = createVisitNoticeDateTimes(visitNotice);
			
			for (int i = 0; i < visitNoticeDateTimes.length; i++) {
				Visit visit = new Visit();
				visit.setNoticeName(createVisitName(hospital));
				visit.setHospCode(hospCode);
				visit.setNoticeDate(visitNoticeDateTimes[i]);
				visitList.add(visit);
			}
			dtoMap.put("visitList", visitList);
		}
		
		scheduleService.insertMainSchedule(member.getUserCode(), dtoMap);
		request.setAttribute("msg", "???????????? ??????????????? ?????????????????????.");
		request.setAttribute("url", "/schedule/schedule-main");
		request.getRequestDispatcher("/error/result").forward(request, response);
	}
	
	private Visit getVisitDto(HttpServletRequest request) {
		String scheduleDate = request.getParameter("schedule_date");
		String hospCode = request.getParameter("hospCode");
		String hospital = request.getParameter("hospital");
		String visitTime = request.getParameter("visit_notice_time");
		Calendar noticeCalendar = addHourMinute(createCalendarFromString(scheduleDate), visitTime);
		Timestamp noticeDateTime = new Timestamp(noticeCalendar.getTime().getTime());
		
		Visit visit = new Visit();
		visit.setNoticeName(createVisitName(hospital));
		visit.setHospCode(hospCode);
		visit.setNoticeDate(noticeDateTime);
		
		return visit;
	}
	
	private Map<String, Object> getPrescriptionDtoMap(HttpServletRequest request) {
		String doseStartStr = request.getParameter("dose_start");
		String doseEndStr = request.getParameter("dose_end");
		
		String pharmCode = request.getParameter("pharmCode");
		String pharm = request.getParameter("pharm");
		
		String[] mediCode = request.getParameterValues("mediCode");
		String[] medicine = request.getParameterValues("medicine");
		
		int doseTimes = Integer.parseInt(request.getParameter("dose_times"));
		String[] doseNotice = request.getParameterValues("dose_notice");

		Date doseStart = parseStringToDate(doseStartStr);
		Date doseEnd = parseStringToDate(doseEndStr);
		Map<String, Object> dtoMap = new HashMap<String, Object>();
		
		Prescription prescription = new Prescription();
		prescription.setPrescriptionName(createPrescriptionName(pharm, medicine, doseNotice));
		prescription.setStartDate(doseStart);
		prescription.setEndDate(doseEnd);
		prescription.setPharmCode(pharmCode);
		prescription.setTimesPerDay(doseTimes);
		dtoMap.put("prescription", prescription);
		
		// dose_notice_list
		if(doseNotice != null) {
			Timestamp[] doseNoticeDateTimes = createDoseNoticeDateTimes(doseStartStr, doseEndStr, doseNotice);
			dtoMap.put("doseNoticeDateTimes", doseNoticeDateTimes);
		}
		
		// medicine_list
		if(medicine != null && mediCode != null) {
			Integer[] mediCodeNum = new Integer[mediCode.length];
			for (int i = 0; i < mediCodeNum.length; i++) {
				mediCodeNum[i] = Integer.parseInt(mediCode[i]);
			}
			dtoMap.put("mediCodeArr", mediCodeNum);
		}
		return dtoMap;
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
		if(hospital.length() == 0) {
			return "?????? ?????? ??????";
		}
		return hospital + "?????? ??????";
	}
	
	private String createPrescriptionName(String pharm, String[] medicine, String[] doseNotice) {
		String name = "";
		
		if(medicine != null) {
			String firstMed = medicine[0];
			if(firstMed.length() > 9) {
				firstMed = firstMed.substring(0, 10) + "...";
			}
			if(medicine.length > 1) {
				name = firstMed + " ???" + (medicine.length - 1) + " ";
			}else {
				name = firstMed + " ";
			}
		}else if(pharm.length() != 0) {
			name = pharm + "?????? ??? ";
		}else {
			name = "??? ";
		}
		
		if(doseNotice == null) {
			name += "?????? ??? ??????";
		}else {
			name += "?????? ??????";
		}
		return name;
	}
	
	private String createScheduleName(String hospital) {
		if(hospital.length() == 0) {
			return "?????? ??????";
		}
		return hospital + "??????";
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
