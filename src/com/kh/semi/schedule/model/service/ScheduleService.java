package com.kh.semi.schedule.model.service;

import java.sql.Connection;
import java.sql.Timestamp;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.schedule.model.dao.ScheduleDao;
import com.kh.semi.schedule.model.dto.Medical;
import com.kh.semi.schedule.model.dto.Prescription;

public class ScheduleService {

	private ScheduleDao scheduleDao = new ScheduleDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public String insertScheduleList(String userCode) {
		Connection conn = template.getConnection();
		String scheduleId = "";
		
		try {
			scheduleDao.insertScheduleList(conn, userCode);
			scheduleId = scheduleDao.getCurrentScheduleId(conn);
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return scheduleId;
	}

	public void insertMedicalHistory(Medical medical) {
		Connection conn = template.getConnection();
		
		try {
			scheduleDao.insertMedicalHistory(conn, medical);
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		
	}

	public void insertPrescription(Prescription prescrition, Timestamp[] doseNoticeDateTimes) {
		Connection conn = template.getConnection();
		
		try {
			scheduleDao.insertPrescriptionList(conn, prescrition);
			
			if(doseNoticeDateTimes != null) {
				scheduleDao.insertDoseNotice(conn, doseNoticeDateTimes);
			}
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		
		
	}
	
}
