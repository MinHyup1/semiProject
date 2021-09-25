package com.kh.semi.schedule.model.service;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.schedule.model.dao.ScheduleDao;
import com.kh.semi.schedule.model.dto.Medical;
import com.kh.semi.schedule.model.dto.Prescription;
import com.kh.semi.schedule.model.dto.Visit;

public class ScheduleService {

	private ScheduleDao scheduleDao = new ScheduleDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public void insertMainSchedule(String userCode, Map<String, Object> dtoMap) {
		Connection conn = template.getConnection();
		
		try {
			String scheduleId = insertScheduleList(conn, userCode);
			
			if(dtoMap.get("medical") != null) {
				Medical medical = (Medical) dtoMap.get("medical");
				insertMedicalHistory(conn, medical, scheduleId);
			}
			
			if(dtoMap.get("prescription") != null) {
				Prescription prescription = (Prescription) dtoMap.get("prescription");
				String prescriptionId = insertPrescription(conn, prescription, scheduleId);
				
				if(dtoMap.get("doseNoticeDateTimes") != null) {
					Timestamp[] doseNoticeDateTimes = (Timestamp[]) dtoMap.get("doseNoticeDateTimes");
					insertDoseNoticeList(conn, doseNoticeDateTimes, prescriptionId);
				}
				
				// 처방 약이 있다면, medicine_record에 insert 하고, prescription_list의 has_medicine Y로 바꾸기
				if(dtoMap.get("medicine") != null) {
					
				}
				
			}
			
			if(dtoMap.get("visitList") != null) {
				List<Visit> visitList = (List<Visit>) dtoMap.get("visitList");
				for (int i = 0; i < visitList.size(); i++) {
					scheduleDao.insertVisitNotice(conn, visitList.get(i));
				}
				scheduleDao.updateHasVisitNotice(conn, scheduleId);
			}
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		
	}
	
	public String insertScheduleList(Connection conn, String userCode) {
		scheduleDao.insertScheduleList(conn, userCode);
		return scheduleDao.getCurrentScheduleId(conn);
	}

	private void insertMedicalHistory(Connection conn, Medical medical, String scheduleId) {
		scheduleDao.insertMedicalHistory(conn, medical);
		scheduleDao.updateHasMedicalRecord(conn, scheduleId);
	}

	private String insertPrescription(Connection conn, Prescription prescription, String scheduleId) {
		scheduleDao.insertPrescriptionList(conn, prescription);
		scheduleDao.updateHasPrescription(conn, scheduleId);
		return scheduleDao.getCurrentPrescriptionId(conn);
	}
	
	private void insertDoseNoticeList(Connection conn, Timestamp[] doseNoticeDateTimes, String prescriptionId) {
		scheduleDao.insertDoseNotice(conn, doseNoticeDateTimes);
		scheduleDao.updateHasDoseNotice(conn, prescriptionId);
	}
	
}
